package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DrivetrainDefaultCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

/**
 *
 */
public class Drivetrain extends Subsystem {
	private RobotDrive robotDrive;
	private Encoder leftEncoder, rightEncoder;
	private Gyro gyro;
	private NetworkTable networktable;

	private boolean highGear;

	// Motion Profile
	private Trajectory leftTraj, rightTraj;
	private final double kp, kd, kv, kAc, kAn, dt;
	private double lastLeftError, lastRightError;

	private int i;
	private double trajLength;

	public Drivetrain() {
		robotDrive = RobotMap.DRIVETRAIN_ROBOT_DRIVE;

		leftEncoder = RobotMap.DRIVETRAIN_ENCODER_LEFT;
		rightEncoder = RobotMap.DRIVETRAIN_ENCODER_RIGHT;

		gyro = RobotMap.DRIVETRAIN_GYRO;
		
		networktable = RobotMap.NETWORKTABLE;

		highGear = true;
		SmartDashboard.putBoolean("DRIVETRAIN: High Gear", highGear);

		kp = 0;
		kd = 0;
		kv = 0;
		kAc = 0;
		kAn = 0;
		dt = 0.02;
		i = 0;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainDefaultCommand());
	}

	public void teleopDrive(double moveValue, double rotateValue) {
		rotateValue *= 0.5;

		// Dead Space
		moveValue = Math.abs(moveValue) > 0.05 ? moveValue : 0;
		rotateValue = Math.abs(rotateValue) > 0.05 ? rotateValue : 0;

		// "Gear" Mode
		moveValue = highGear ? moveValue : moveValue * 0.5;
		rotateValue = highGear ? rotateValue : rotateValue * 0.5;

		robotDrive.arcadeDrive(moveValue, rotateValue);
	}

	public void shiftGearMode() {
		highGear = !highGear;
	}

	public void resetDriveMotors() {
		robotDrive.drive(0, 0);
	}

	// Acceleration Test

	public void accel() {
		robotDrive.tankDrive(1, 1);
		networktable.putNumber("Time", Timer.getFPGATimestamp());
		networktable.putNumber("Left", leftEncoder.getDistance());
		networktable.putNumber("Right", rightEncoder.getDistance());
	}

	// Motion Profile
	public void setTrajectories(Trajectory leftTraj, Trajectory rightTraj) {
		this.leftTraj = leftTraj;
		this.rightTraj = rightTraj;

		trajLength = leftTraj.length();
	}

	public void resetSensors() {
		leftEncoder.reset();
		rightEncoder.reset();
		gyro.reset();

		lastLeftError = 0;
		lastRightError = 0;
		i = 0;
	}

	public void runProfile() {
		Segment leftSeg = leftTraj.get(i);
		Segment rightSeg = rightTraj.get(i);

		// Error Calculation
		double leftError = leftSeg.position - leftEncoder.getDistance();
		double rightError = rightSeg.position - rightEncoder.getDistance();
		double angleError = leftSeg.heading - Math.toRadians(gyro.getAngle());

		// Term Calculation
		double leftValue = kv * leftSeg.velocity + kAc * leftSeg.acceleration + kp * leftError
				+ kd * (leftError - lastLeftError) / dt + kAn * angleError;
		double rightValue = kv * rightSeg.velocity + kAc * rightSeg.acceleration + kp * rightError
				+ kd * (rightError - lastRightError) / dt - kAn * angleError;

		robotDrive.tankDrive(leftValue, rightValue);

		lastLeftError = leftError;
		lastRightError = rightError;

		i++;
	}

	public boolean isDone() {
		return i > trajLength;
	}
}
