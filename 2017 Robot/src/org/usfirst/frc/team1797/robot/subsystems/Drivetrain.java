package org.usfirst.frc.team1797.robot.subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DriveDefaultCommand;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
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
	private ADXL362 accel;

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

		accel = RobotMap.DRIVETRAIN_ACCEL;

		kp = 0;
		kd = 0;
		kv = 0;
		kAc = 0;
		kAn = 0;
		dt = 0.02;
		i = 0;
	}

	public void initDefaultCommand() {
		this.setDefaultCommand(new DriveDefaultCommand());
	}

	public void teleopDrive(double leftValue, double rightValue) {
		robotDrive.arcadeDrive(leftValue, rightValue);
	}

	public void resetDriveMotors() {
		robotDrive.drive(0, 0);
	}

	// Acceleration Test

	public void accel(File file) {
		robotDrive.tankDrive(1,1);
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bw.write(Timer.getFPGATimestamp() + "\t" + accel.getX() + "\t" + accel.getY() + "\t" + accel.getZ() + "\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
