package org.usfirst.frc.team1797.robot.subsystems;

import java.io.File;
import java.util.ArrayList;

import org.usfirst.frc.team1797.robot.Robot;
import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DrivetrainDefaultCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
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

	private boolean highGear;

	private int i;

	// Drive P
	private double drive_kp;

	// Vision Controls
	private double phi_kp, r_kp;
	private double phi, r;
	private ArrayList<Double> lastPhiErrors = new ArrayList<Double>(), lastRErrors = new ArrayList<Double>();
	private final double PEG_LENGTH = 10.5;

	// Motion Profile P/V
	private Trajectory leftTraj, rightTraj;

	private final double mp_kp, mp_kv_left, mp_kv_right;

	private double trajLength;

	public Drivetrain() {
		robotDrive = RobotMap.DRIVETRAIN_ROBOT_DRIVE;

		leftEncoder = RobotMap.DRIVETRAIN_ENCODER_LEFT;
		rightEncoder = RobotMap.DRIVETRAIN_ENCODER_RIGHT;

		highGear = true;

		drive_kp = 0.01;

		phi_kp = 0.166;
		r_kp = 0.01;

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
		double left = leftEncoder.getRate();
		double right = rightEncoder.getRate();
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
	public void setPhi() {
		resetDriveMotors();
		resetSensors();
		lastPhiErrors.clear();
		phi = Robot.processor.getVector().getPhi() * 0.201;
	}

	public void turn() {
		double leftError = phi - leftEncoder.getDistance();
		double rightError = -phi - rightEncoder.getDistance();
		updateLastPhiErrors((leftError - rightError) / 2);
		robotDrive.tankDrive(phi_kp * leftError, phi_kp * rightError);
	}

	private void updateLastPhiErrors(double error) {
		if (lastPhiErrors.size() > 10)
			lastPhiErrors.remove(0);
		lastPhiErrors.add(Math.abs(error));
	}

	public boolean turnIsDone() {
		double average = 0;
		for (int i = 0; i < lastPhiErrors.size(); i++) {
			average += lastPhiErrors.get(i);
		}
		average /= lastPhiErrors.size();
		return Math.abs(average) < 1;
	}

	public void setR(double r) {
		resetSensors();
		lastRErrors.clear();
		this.r = r;
	}
	
	public void setR(){
		setR(Robot.processor.getVector().getR());
	}
	
	public void rDrive() {
		double error = PEG_LENGTH - r;
		updateLastRErrors(error);
		double moveValue = r_kp * error;
		driveStraight(moveValue);
	}

	private void updateLastRErrors(double error) {
		if (lastRErrors.size() > 10)
			lastRErrors.remove(0);
		lastRErrors.add(Math.abs(error));
	}

	public boolean rDriveIsDone() {
		double average = 0;
		for (int i = 0; i < lastRErrors.size(); i++) {
			average += lastRErrors.get(i);
		}
		average /= lastRErrors.size();
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
