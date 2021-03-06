package org.usfirst.frc.team1797.robot;

import org.usfirst.frc.team1797.robot.commands.autocommandgroups.AutoBaseline;
import org.usfirst.frc.team1797.robot.commands.autocommandgroups.AutoCenterGear;
import org.usfirst.frc.team1797.robot.commands.autocommandgroups.AutoDoNothing;
import org.usfirst.frc.team1797.robot.commands.autocommandgroups.AutoLeftGear;
import org.usfirst.frc.team1797.robot.commands.autocommandgroups.AutoRightGear;
import org.usfirst.frc.team1797.util.Vector;
import org.usfirst.frc.team1797.vision.GripPipeline;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
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
	 * PWM: 0 - DRIVETRAIN Victor Left (x2); 1 - DRIVETRAIN Victor Right (x2); 2
	 * - FLOORGEAR Intake; 3 - CLIMBER (x2); 4 - STORAGE Intake (x2); 5 -
	 * STORAGE Agitator; 6 - SHOOTER Conveyor; 7 - SHOOTER Wheels (x2)
	 * 
	 * DIO: 0,1 - DRIVETRAIN Encoder Left; 2,3 - DRIVETRAIN Encoder Right;
	 * 
	 * PCM: 0,1 - FLOORGEAR Lifter; 2,3 - FLOORGEAR Blocker; 4,5 - SLOTGEAR
	 * Holder
	 */

	// Componenets necessary for Auto Chooser
	public static SendableChooser<Command> AUTO_CHOOSER;

	// Components necessary for Network Table
	public static NetworkTable NETWORKTABLE;

	// Components necessary for Drivetrain
	public static RobotDrive DRIVETRAIN_ROBOT_DRIVE;

	public static Encoder DRIVETRAIN_ENCODER_LEFT, DRIVETRAIN_ENCODER_RIGHT;

	// Components necessary for Floor Gear
	public static VictorSP FLOORGEAR_INTAKE;
	public static DoubleSolenoid FLOORGEAR_LIFTER, FLOORGEAR_BLOCKER;

	// Components necessary for Climber
	public static VictorSP CLIMBER;

	// Components necessary for Passive Gear
	public static DoubleSolenoid SLOTGEAR_HOLDER;

	// Components necessary for Storage
	public static VictorSP STORAGE_AGITATOR, STORAGE_INTAKE;

	// Components necessary for Shooter
	public static VictorSP SHOOTER_CONVEYOR, SHOOTER_WHEELS;
	public static Encoder SHOOTER_ENCODER;
	public static PIDController SHOOTER_PID;

	// Components necessary for Vision
	public static CameraServer VISION_SERVER;
	public static int kVISION_WIDTH = 160, kVISION_HEIGHT = 90;
	public static double kVISION_FOV = 60, kVISION_CENTER_X, kVISION_FOCAL_LENGTH;
	public static Vector VISION_CAMERA_VECTOR = new Vector(4, 0);
	public static UsbCamera VISION_CAMERA;
	public static GripPipeline VISION_PIPELINE;
	public static CvSink VISION_SINK;

	public static void auto() {

		// Auto Chooser
		AUTO_CHOOSER = new SendableChooser<Command>();
		AUTO_CHOOSER.addDefault("Do Nothing", new AutoDoNothing());
		AUTO_CHOOSER.addObject("Baseline", new AutoBaseline());
		AUTO_CHOOSER.addObject("Left Gear", new AutoLeftGear());
		AUTO_CHOOSER.addObject("Center Gear", new AutoCenterGear());
		AUTO_CHOOSER.addObject("Right Gear", new AutoRightGear());
		SmartDashboard.putData("Auto Mode:", AUTO_CHOOSER);

	}

	public static void init() {

		// Drivetrain
		DRIVETRAIN_ROBOT_DRIVE = new RobotDrive(0, 1);

		DRIVETRAIN_ENCODER_LEFT = new Encoder(0, 1);
		DRIVETRAIN_ENCODER_LEFT.setDistancePerPulse(0.0481);

		DRIVETRAIN_ENCODER_RIGHT = new Encoder(2, 3, true);
		DRIVETRAIN_ENCODER_RIGHT.setDistancePerPulse(0.0481);

		// Floor Gear
		FLOORGEAR_INTAKE = new VictorSP(2);
		FLOORGEAR_LIFTER = new DoubleSolenoid(0, 1);
		FLOORGEAR_BLOCKER = new DoubleSolenoid(2, 3);

		// Climber
		CLIMBER = new VictorSP(3);

		// Passive Gear
		SLOTGEAR_HOLDER = new DoubleSolenoid(4, 5);

		// Storage
		STORAGE_INTAKE = new VictorSP(4);
		STORAGE_AGITATOR = new VictorSP(5);

		// Shooter
		SHOOTER_CONVEYOR = new VictorSP(6);
		SHOOTER_WHEELS = new VictorSP(7);
		SHOOTER_ENCODER = new Encoder(4, 5);
		SHOOTER_ENCODER.setPIDSourceType(PIDSourceType.kRate);
		SHOOTER_PID = new PIDController(1, 0, 0, SHOOTER_ENCODER, SHOOTER_WHEELS);

		// Vision
		VISION_SERVER = CameraServer.getInstance();

		VISION_CAMERA = VISION_SERVER.startAutomaticCapture("FRONT", 0);
		VISION_CAMERA.setResolution(kVISION_WIDTH, kVISION_HEIGHT);
		kVISION_CENTER_X = kVISION_WIDTH / 2 - 0.5;
		kVISION_FOCAL_LENGTH = kVISION_WIDTH / (2 * Math.tan(Math.toRadians(kVISION_FOV / 2)));

		VISION_CAMERA.getProperty("saturation").set(20);
		VISION_CAMERA.getProperty("gain").set(0);
		VISION_CAMERA.getProperty("exposure_auto").set(1);
		VISION_CAMERA.getProperty("brightness").set(50);
		VISION_CAMERA.getProperty("exposure_absolute").set(1);
		VISION_CAMERA.getProperty("exposure_auto_priority").set(0);

		VISION_PIPELINE = new GripPipeline();

		VISION_SINK = VISION_SERVER.getVideo();

		// Network
		NETWORKTABLE = NetworkTable.getTable("Network Table");

	}

}
