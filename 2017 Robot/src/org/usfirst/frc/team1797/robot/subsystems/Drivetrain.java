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
	private NetworkTable networktable;
	private VisionProcessor processor;

	private boolean highGear;

	private int i;

	// Drive PID
	private final double drive_kp;
	private double driveSetpoint;
	private boolean driveSetpointIsSet;

	// Vision Controls
	private double cv_kp;
	private double angle;
	private ArrayList<Double> lastTurnErrors = new ArrayList<Double>();
	private double ultrasonic_kp;
	private ArrayList<Double> lastDistErrors = new ArrayList<Double>();
	private final double PEG_LENGTH = 10.5;

	// Motion Profile PD/VA
	private Trajectory leftTraj, rightTraj;

	private final double mp_kp, mp_kd, mp_kv_left, mp_kv_right, mp_kAc, mp_kAn, dt;
	private double lastLeft, lastRight;

	private double trajLength;

	public Drivetrain() {
		robotDrive = RobotMap.DRIVETRAIN_ROBOT_DRIVE;

		leftEncoder = RobotMap.DRIVETRAIN_ENCODER_LEFT;
		rightEncoder = RobotMap.DRIVETRAIN_ENCODER_RIGHT;

		gyro = RobotMap.DRIVETRAIN_GYRO;

		networktable = RobotMap.NETWORKTABLE;

		processor = RobotMap.VISION_PROCESSOR;

		highGear = true;
		SmartDashboard.putBoolean("DRIVETRAIN: High Gear", highGear);

		driveSetpointIsSet = false;
		drive_kp = 0.1;

		cv_kp = 0.1;
		ultrasonic_kp = 0.01;

		mp_kp = 0;
		mp_kd = 0;
		mp_kv_left = 0.00706;
		mp_kv_right = 0.00685;
		mp_kAc = 0;
		mp_kAn = 0;
		dt = 0.02;
		i = 0;

	}

	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainDefaultCommand());
	}

	// Driving
	public void teleopDrive(double moveValue, double rotateValue) {

		// Dead Space
		moveValue = Math.abs(moveValue) > 0.05 ? moveValue : 0;
		rotateValue = Math.abs(rotateValue) > 0.05 ? rotateValue : 0;

		// "Gear" Mode
		moveValue = highGear ? moveValue : moveValue * 0.5;
		rotateValue = highGear ? rotateValue : rotateValue * 0.5;

		if (moveValue == 0) {
			driveSetpointIsSet = false;
			rotateValue = highGear ? 0.5 * rotateValue : rotateValue;
			robotDrive.arcadeDrive(0, rotateValue);
		} else if (rotateValue == 0) {
			driveStraight(moveValue);
		} else {
			driveSetpointIsSet = false;
			rotateValue = highGear ? rotateValue * 0.71 : rotateValue;
			robotDrive.arcadeDrive(moveValue, rotateValue);
		}
	}

	private void driveStraight(double moveValue) {
		if (!driveSetpointIsSet)
			setSetpoint();
		double error = driveSetpoint - gyro.getAngle();
		double result = drive_kp * error;
		robotDrive.tankDrive(moveValue + result, moveValue - result);
	}

	private void setSetpoint() {
		driveSetpoint = gyro.getAngle();
		driveSetpointIsSet = true;
	}

	public void shiftGearMode() {
		highGear = !highGear;
	}

	public void resetDriveMotors() {
		robotDrive.stopMotor();
	}

	// Vision
	public void setAngle() {
		resetDriveMotors();
		lastTurnErrors.clear();
		angle = gyro.getAngle() + processor.getTurnAngle();
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

	public void resetDistDrive(){
		lastDistErrors.clear();
		driveSetpointIsSet = false;
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

	// Acceleration Test
	public void accel() {
		robotDrive.tankDrive(1, 1);
		networktable.putNumber("Time", Timer.getFPGATimestamp());
		networktable.putNumber("Left", leftEncoder.getDistance());
		networktable.putNumber("Right", rightEncoder.getDistance());
	}

	// Motion Profile
	public void stationTrajectory(int station) {
		File left = new File("home//lvuser//station" + station + "_left.csv");
		File right = new File("home//lvuser//station" + station + "_right.csv");
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

		lastLeft = 0;
		lastRight = 0;
		i = 0;
	}

	public void runProfile() {
		Segment leftSeg = leftTraj.get(i);
		Segment rightSeg = rightTraj.get(i);

		System.out.println(leftEncoder.getDistance() + "\t" + rightEncoder.getDistance());

		// Error Calculation
		double leftError = leftSeg.position - leftEncoder.getDistance();
		double rightError = rightSeg.position - rightEncoder.getDistance();
		double angleError = leftSeg.heading - Math.toRadians(gyro.getAngle());

		// Term Calculation
		double leftValue = mp_kv_left * leftSeg.velocity + mp_kAc * leftSeg.acceleration + mp_kp * leftError
				+ mp_kd * (leftError - lastLeft) / dt - mp_kAn * angleError;
		double rightValue = mp_kv_right * rightSeg.velocity + mp_kAc * rightSeg.acceleration + mp_kp * rightError
				+ mp_kd * (rightError - lastRight) / dt + mp_kAn * angleError;

		robotDrive.tankDrive(leftValue, rightValue);

		lastLeft = leftError;
		lastRight = rightError;

		i++;
	}

	public boolean profileIsDone() {
		return i >= trajLength;
	}
}
