package org.usfirst.frc.team1797.robot.subsystems;

import java.io.File;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DrivetrainDefaultCommand;

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
	private NetworkTable networktable;

	private boolean highGear;

	private int i;

	// Gyro
	private final double gyro_kp, gyro_ki, gyro_kd;
	private double gyroIntegral, lastGyro, setpoint;
	private boolean setpointIsSet;

	// Motion Profile
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

		highGear = true;
		SmartDashboard.putBoolean("DRIVETRAIN: High Gear", highGear);

		setpointIsSet = false;
		gyro_kp = 1 / 10;
		gyro_ki = 0;
		gyro_kd = 0;

		mp_kp = 1;
		mp_kd = 0;
		mp_kv_left = 0.00754;
		mp_kv_right = 0.00657;
		mp_kAc = 0;
		mp_kAn = 0;
		dt = 0.02;
		i = 0;

	}

	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainDefaultCommand());
	}

	public void teleopDrive(double moveValue, double rotateValue) {

		// Dead Space
		moveValue = Math.abs(moveValue) > 0.05 ? moveValue : 0;
		rotateValue = Math.abs(rotateValue) > 0.05 ? rotateValue : 0;

		// "Gear" Mode
		moveValue = highGear ? moveValue : moveValue * 0.5;
		rotateValue = highGear ? rotateValue : rotateValue * 0.5;

		if (moveValue == 0) {
			setpointIsSet = false;
			rotateValue = highGear ? 0.5 * rotateValue : rotateValue;
			robotDrive.arcadeDrive(0, rotateValue);
		} else if (rotateValue == 0) {
			driveStraight(moveValue);
		} else {
			setpointIsSet = false;
			rotateValue = highGear ? rotateValue * 0.71 : rotateValue;
			robotDrive.arcadeDrive(moveValue, rotateValue);
		}
	}

	public void driveStraight(double moveValue) {
		if (!setpointIsSet)
			setSetpoint();
		double error = setpoint - gyro.getAngle();
		gyroIntegral += error * dt;
		double result = gyro_kp * error + gyro_ki * gyroIntegral + gyro_kd * (error - lastGyro);
		robotDrive.tankDrive(moveValue + result, moveValue - result);
	}

	public void setSetpoint() {
		setpoint = gyro.getAngle();
		setpointIsSet = true;
	}

	public void shiftGearMode() {
		highGear = !highGear;
	}

	public void resetDriveMotors() {
		robotDrive.stopMotor();
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
		System.out.println(left.exists());
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

	public boolean isDone() {
		return i >= trajLength;
	}
}
