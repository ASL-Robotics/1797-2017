package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DriveDefaultCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

/**
 *
 */
public class Drivetrain extends Subsystem {
	private RobotDrive robotDrive;
	private Encoder leftEncoder, rightEncoder;
	private Gyro gyro;
	private DriveType driveType;

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

		driveType = DriveType.TANK;

		kp = 0;
		kd = 0;
		kv = 0;
		kAc = 0;
		kAn = 0;
		dt = 0.02;
		i = 0;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		this.setDefaultCommand(new DriveDefaultCommand());
	}

	public void teleopDrive(double leftValue, double rightValue) {
		switch (driveType) {
		case TANK:
			robotDrive.tankDrive(leftValue, rightValue);
			break;
		case ARCADE:
			robotDrive.arcadeDrive(leftValue, rightValue);
			break;
		default:
			System.out.println("Error: no drive type selected");
			break;
		}
	}

	public void resetDriveMotors() {
		robotDrive.drive(0, 0);
	}

	public void setMotors(double leftValue, double rightValue) {
		robotDrive.drive(leftValue, rightValue);
	}

	// Motion Profile
	// Help! I got stuck in the coding factory! Pls send help!

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

	private enum DriveType {
		TANK, ARCADE;
	}
}
