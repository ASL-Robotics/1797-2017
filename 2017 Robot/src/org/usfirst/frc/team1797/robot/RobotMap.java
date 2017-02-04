package org.usfirst.frc.team1797.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/*
	 * PWM: 0 - DT Victor Left; 1 - DT Victor Right; 2 - GEAR Intake; 3 -
	 * CLIMBER;
	 * 
	 * DIO: 0 - DT Encoder Left; 1 - DT Encoder Left; 2 - DT Encoder Right; 3 -
	 * DT Encoder Right;
	 * 
	 * PCM: 0 - GEAR Piston, 1 - GEAR Piston;
	 * 
	 */

	// Components necessary for Drivetrain
	public static VictorSP DRIVETRAIN_VICTOR_LEFT, DRIVETRAIN_VICTOR_RIGHT;
	public static Encoder DRIVETRAIN_ENCODER_LEFT, DRIVETRAIN_ENCODER_RIGHT;
	public static ADXRS450_Gyro DRIVETRAIN_GYRO;

	// Components necessary for Active Gear
	public static VictorSP GEAR_INTAKE;
	public static DoubleSolenoid GEAR_PISTON;

	// Components necessary for Climber
	public static VictorSP CLIMBER;

	// Components necessary for Vision
	public static CameraServer CAMERA_SERVER;
	public static final int FRONT_CAMERA_PORT = 0;
	public static UsbCamera FRONT_CAMERA;

	public static void init() {

		// Drivetrain
		DRIVETRAIN_VICTOR_LEFT = new VictorSP(0);
		DRIVETRAIN_VICTOR_RIGHT = new VictorSP(1);

		DRIVETRAIN_ENCODER_LEFT = new Encoder(0, 1);
		SmartDashboard.putData("Drivetrain Left Encoder", DRIVETRAIN_ENCODER_LEFT);
		DRIVETRAIN_ENCODER_RIGHT = new Encoder(2, 3);
		SmartDashboard.putData("Drivetrain Right Encoder", DRIVETRAIN_ENCODER_RIGHT);

		DRIVETRAIN_GYRO = new ADXRS450_Gyro();
		SmartDashboard.putData("Drivetrain Gyro", DRIVETRAIN_GYRO);

		// Active Gear
		GEAR_INTAKE = new VictorSP(2);
		GEAR_PISTON = new DoubleSolenoid(0, 1);

		// Climber
		CLIMBER = new VictorSP(3);

		// Vision
		CAMERA_SERVER = CameraServer.getInstance();
		FRONT_CAMERA = CAMERA_SERVER.startAutomaticCapture("Front Camera", FRONT_CAMERA_PORT);
	}

}
