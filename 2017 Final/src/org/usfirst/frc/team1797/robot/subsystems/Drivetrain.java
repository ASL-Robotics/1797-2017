package org.usfirst.frc.team1797.robot.subsystems;

import java.io.File;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DrivetrainDefaultCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

public class Drivetrain extends Subsystem {
	private RobotDrive robotDrive;
	private Encoder leftEncoder, rightEncoder;

	private boolean highGear;

	private int i;

	// Drive P
	private double drive_kp;

	// Motion Profile P/V
	private Trajectory leftTraj, rightTraj;

	private double mp_kp, mp_kv_left, mp_kv_right;

	private double trajLength;

	public Drivetrain() {
		robotDrive = RobotMap.DRIVETRAIN_ROBOT_DRIVE;

		leftEncoder = RobotMap.DRIVETRAIN_ENCODER_LEFT;
		rightEncoder = RobotMap.DRIVETRAIN_ENCODER_RIGHT;

		highGear = true;

		drive_kp = 0.01;

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

	// Motion Profile
	public void trajectory(int number, boolean isRed) {
		File left, right;
		if (isRed) {
			left = new File("home//lvuser//left_" + (number - 1) + ".csv");
			right = new File("home//lvuser//right_" + (number - 1) + ".csv");
		} else {
			left = new File("home//lvuser//right_" + (number - 1) + ".csv");
			right = new File("home//lvuser//left_" + (number - 1) + ".csv");
		}
		Trajectory leftTraj = Pathfinder.readFromCSV(left);
		Trajectory rightTraj = Pathfinder.readFromCSV(right);
		setTrajectories(leftTraj, rightTraj);
	}

	public void invertEncoders() {
		leftEncoder.setReverseDirection(!leftEncoder.getDirection());
		rightEncoder.setReverseDirection(!rightEncoder.getDirection());

		mp_kv_left *= -1;
		mp_kv_right *= -1;
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
