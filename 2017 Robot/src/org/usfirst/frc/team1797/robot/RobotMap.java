package org.usfirst.frc.team1797.robot;

import org.usfirst.frc.team1797.robot.commands.AutoDefaultCommand;
import org.usfirst.frc.team1797.util.AnalogForceResistor;
import org.usfirst.frc.team1797.util.AnalogUltrasonicSensor;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	 * CLIMBER, 4 - BALLINTAKE Intake, 5 - BALLINTAKE Cam;
	 * 
	 * DIO: 0 - DT Encoder Left; 1 - DT Encoder Left; 2 - DT Encoder Right; 3 -
	 * DT Encoder Right;
	 * 
	 * PCM: 0 - GEAR Piston, 1 - GEAR Piston, 2 - PASSIVEGEAR Block, 3 -
	 * PASSIVEGEAR Block, 4 - PASSIVEGEAR Hold, 5 - PASSIVEGEAR Hold;
	 * 
	 */

	// Componenets necessary for Auto Chooser
	public static SendableChooser<Command> AUTO_CHOOSER;

	// Components necessary for Network Table
	public static NetworkTable NETWORKTABLE;

	// Components necessary for Drivetrain
	public static RobotDrive DRIVETRAIN_ROBOT_DRIVE;

	public static Encoder DRIVETRAIN_ENCODER_LEFT, DRIVETRAIN_ENCODER_RIGHT;

	public static ADXRS450_Gyro DRIVETRAIN_GYRO;
	public static ADXL362 DRIVETRAIN_ACCEL;
	public static AnalogUltrasonicSensor DRIVETRAIN_ULTRASONIC;

	// Components necessary for Floor Gear
	public static VictorSP FLOORGEAR_INTAKE;
	public static DoubleSolenoid FLOORGEAR_LIFTER, FLOORGEAR_BLOCKER;
	public static AnalogForceResistor FLOORGEAR_FORCE_LEFT, FLOORGEAR_FORCE_RIGHT;

	// Components necessary for Climber
	public static VictorSP CLIMBER;

	// Components necessary for Passive Gear
	public static DoubleSolenoid SLOTGEAR_HOLDER;

	// Components necessary for Storage
	public static VictorSP STORAGE_AGITATOR, STORAGE_INTAKE;

	// Components necessary for Shooter

	public static void init() {

		// Auto Chooser
		AUTO_CHOOSER.addDefault("Default Auto", new AutoDefaultCommand());
		SmartDashboard.putData("Auto mode", AUTO_CHOOSER);

		// Network
		NETWORKTABLE = NetworkTable.getTable("Network Table");

		// Drivetrain
		DRIVETRAIN_ROBOT_DRIVE = new RobotDrive(0, 1);

		DRIVETRAIN_ENCODER_LEFT = new Encoder(0, 1);
		DRIVETRAIN_ENCODER_LEFT.setDistancePerPulse(0.0481);
		SmartDashboard.putData("Drivetrain Left Encoder", DRIVETRAIN_ENCODER_LEFT);

		DRIVETRAIN_ENCODER_RIGHT = new Encoder(2, 3);
		DRIVETRAIN_ENCODER_RIGHT.setDistancePerPulse(0.0481);
		SmartDashboard.putData("Drivetrain Right Encoder", DRIVETRAIN_ENCODER_RIGHT);

		DRIVETRAIN_GYRO = new ADXRS450_Gyro();
		SmartDashboard.putData("Drivetrain Gyro", DRIVETRAIN_GYRO);

		DRIVETRAIN_ACCEL = new ADXL362(Range.k8G);
		SmartDashboard.putData("Drivetrain Accelerometer", DRIVETRAIN_ACCEL);

		DRIVETRAIN_ULTRASONIC = new AnalogUltrasonicSensor(0);

		// Active Gear
		FLOORGEAR_INTAKE = new VictorSP(2);
		FLOORGEAR_LIFTER = new DoubleSolenoid(0, 1);

		FLOORGEAR_FORCE_LEFT = new AnalogForceResistor(1);
		FLOORGEAR_FORCE_RIGHT = new AnalogForceResistor(2);

		// Climber
		CLIMBER = new VictorSP(3);

		// Passive Gear
		FLOORGEAR_BLOCKER = new DoubleSolenoid(2, 3);
		SLOTGEAR_HOLDER = new DoubleSolenoid(4, 5);

		// Shooter

		// Ball Intake
		STORAGE_INTAKE = new VictorSP(4);
		STORAGE_AGITATOR = new VictorSP(5);
	}

}
