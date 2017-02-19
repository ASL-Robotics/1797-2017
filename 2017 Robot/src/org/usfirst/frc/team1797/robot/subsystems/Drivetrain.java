package org.usfirst.frc.team1797.robot.subsystems;

import java.io.File;
import java.util.ArrayList;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DrivetrainDefaultCommand;
import org.usfirst.frc.team1797.util.Ultrasonic;
import org.usfirst.frc.team1797.vision.VisionProcessor;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

/**
 *
 */
public class Drivetrain extends Subsystem {
	private RobotDrive robotDrive;
	private Encoder leftEncoder, rightEncoder;
	private Gyro gyro;
	private Ultrasonic ultrasonic;
	private VisionProcessor processor;

	private boolean highGear;

	private int i;

	// Drive PID
	private double drive_kp;

	// Vision Controls
	private double cv_kp;
	private double angle;
	private ArrayList<Double> lastTurnErrors = new ArrayList<Double>();
	private double ultrasonic_kp;
	private ArrayList<Double> lastDistErrors = new ArrayList<Double>();
	private final double PEG_LENGTH = 10.5;

	// Motion Profile PD/VA
	private Trajectory leftTraj, rightTraj;

	private final double mp_kp, mp_kv_left, mp_kv_right;

	private double trajLength;

	public Drivetrain() {
		robotDrive = RobotMap.DRIVETRAIN_ROBOT_DRIVE;

		leftEncoder = RobotMap.DRIVETRAIN_ENCODER_LEFT;
		rightEncoder = RobotMap.DRIVETRAIN_ENCODER_RIGHT;

		gyro = RobotMap.DRIVETRAIN_GYRO;

		processor = RobotMap.VISION_PROCESSOR;

		highGear = true;
		SmartDashboard.putBoolean("DRIVETRAIN: High Gear", highGear);

		drive_kp = 0.01;

		cv_kp = 0.1;
		ultrasonic_kp = 0.01;

		mp_kp = 0.2;
		mp_kv_left = 0.00706;
		mp_kv_right = 0.00685;
		i = 0;

	}

	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainDefaultCommand());
	}

	// Driving
	public void teleopDrive(double moveValue, double rotateValue) {

		System.out.println(gyro.getAngle());

		// Dead Space
		moveValue = Math.abs(moveValue) > 0.05 ? moveValue : 0;
		rotateValue = Math.abs(rotateValue) > 0.05 ? rotateValue : 0;

		// "Gear" Mode
		moveValue = highGear ? moveValue : moveValue * 0.5;
		rotateValue = highGear ? rotateValue : rotateValue * 0.5;

		if (moveValue == 0 && rotateValue == 0) {
			robotDrive.arcadeDrive(0, 0);
		} else if (moveValue == 0) {
			rotateValue = highGear ? 0.5 * rotateValue : rotateValue;
			robotDrive.arcadeDrive(0, rotateValue);
		} else if (rotateValue == 0) {
			driveStraight(moveValue);
		} else {
			rotateValue = highGear ? rotateValue * 0.71 : rotateValue;
			robotDrive.arcadeDrive(moveValue, rotateValue);
		}
	}

	private void driveStraight(double moveValue) {
		double left = RobotMap.DRIVETRAIN_ENCODER_LEFT.getRate();
		double right = RobotMap.DRIVETRAIN_ENCODER_RIGHT.getRate();
		double error = right - left;
		double result = drive_kp * error;
		robotDrive.tankDrive(moveValue + result, moveValue - result);
	}

	public void shiftGearMode() {
		highGear = !highGear;
		SmartDashboard.putBoolean("High Gear Mode", highGear);
	}

	public void resetDriveMotors() {
		robotDrive.stopMotor();
	}

	// Vision
	public void setAngle() {
		resetDriveMotors();
		RobotMap.VISION_CAMERA.setBrightness(50);
		lastTurnErrors.clear();
		angle = (gyro.getAngle() + processor.getTurnAngle()) % 360;
	}

	public void turn() {
		double error = angle - gyro.getAngle();
		updateLastTurnErrors(error);
		double result = cv_kp * error;
		robotDrive.tankDrive(result, -result);
	}

	private void updateLastTurnErrors(double error) {
		if (lastTurnErrors.size() > 10)
			lastTurnErrors.remove(0);
		lastTurnErrors.add(error);
	}

	public boolean turnIsDone() {
		double average = 0;
		for (int i = 0; i < lastTurnErrors.size(); i++) {
			average += lastTurnErrors.get(i);
		}
		average /= lastTurnErrors.size();
		return Math.abs(average) < 1;
	}

	public void resetDistDrive() {
		lastDistErrors.clear();
	}

	public void distDrive() {
		double error = PEG_LENGTH - ultrasonic.getDistance();
		updateLastDistErrors(error);
		double moveValue = ultrasonic_kp * error;
		driveStraight(moveValue);
	}

	private void updateLastDistErrors(double error) {
		if (lastDistErrors.size() > 10)
			lastDistErrors.remove(0);
		lastDistErrors.add(error);
	}

	public boolean distDriveIsDone() {
		double average = 0;
		for (int i = 0; i < lastDistErrors.size(); i++) {
			average += lastDistErrors.get(i);
		}
		average /= lastDistErrors.size();
		return Math.abs(average) < 1;
	}

	// Motion Profile
	public void stationTrajectory(int station) {
		File left = new File("home//lvuser//left_" + (station - 1) + ".csv");
		File right = new File("home//lvuser//right_" + (station - 1) + ".csv");
		Trajectory leftTraj = Pathfinder.readFromCSV(left);
		Trajectory rightTraj = Pathfinder.readFromCSV(right);
		setTrajectories(leftTraj, rightTraj);
	}

	public void setTrajectories(Trajectory leftTraj, Trajectory rightTraj) {
		this.leftTraj = leftTraj;
		this.rightTraj = rightTraj;
		trajLength = leftTraj.length();
	}

	public void resetSensors() {
		leftEncoder.reset();
		rightEncoder.reset();
		gyro.reset();

		i = 0;
	}

	public void runProfile() {
		Segment leftSeg = leftTraj.get(i);
		Segment rightSeg = rightTraj.get(i);

		// Error Calculation
		double leftError = leftSeg.position - leftEncoder.getDistance();
		double rightError = rightSeg.position - rightEncoder.getDistance();

		// Term Calculation
		double leftValue = mp_kv_left * leftSeg.velocity + mp_kp * leftError;
		double rightValue = mp_kv_right * rightSeg.velocity + mp_kp * rightError;

		robotDrive.tankDrive(leftValue, rightValue);

		i++;
	}

	public boolean profileIsDone() {
		return i >= trajLength;
	}
}
