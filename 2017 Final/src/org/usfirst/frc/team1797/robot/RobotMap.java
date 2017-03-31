package org.usfirst.frc.team1797.robot;

import org.usfirst.frc.team1797.robot.commands.autocommands.AutoBlueLeftGear;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoBlueLeftGearHC;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoBlueLeftHCReverse;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoBlueRightGear;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoBlueRightGearHC;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoCenterGear;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoDoNothing;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoRedLeftGear;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoRedLeftGearHC;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoRedRightGear;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoRedRightGearHC;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoRedRightHCReverse;
import org.usfirst.frc.team1797.robot.commands.autocommands.AutoStraightHCReverse;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
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
	 * - FLOORGEAR Intake; 3 - CLIMBER (x2);
	 * 
	 * DIO: 0,1 - DRIVETRAIN Encoder Left; 2,3 - DRIVETRAIN Encoder Right;
	 * 
	 * PCM: 0,1 - FLOORGEAR Lifter; 2,3 - FLOORGEAR Blocker; 4,5 - SLOTGEAR
	 * Holder
	 */

	// Componenets necessary for Auto Chooser
	public static SendableChooser<Command> AUTO_CHOOSER;

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

	// Components necessary for Vision
	public static CameraServer VISION_SERVER;
	public static UsbCamera VISION_CAMERA;

	public static void auto() {

		// Auto Chooser
		AUTO_CHOOSER = new SendableChooser<Command>();
		
		AUTO_CHOOSER.addDefault("Do Nothing", new AutoDoNothing());
		
		AUTO_CHOOSER.addObject("Center Gear", new AutoCenterGear());
		
		AUTO_CHOOSER.addObject("Red Alliance Left Gear", new AutoRedLeftGear());
		AUTO_CHOOSER.addObject("Red Alliance Right Gear", new AutoRedRightGear());
		
		AUTO_CHOOSER.addObject("Blue Alliance Left Gear", new AutoBlueLeftGear());
		AUTO_CHOOSER.addObject("Blue Alliance Right Gear", new AutoBlueRightGear());
		
		AUTO_CHOOSER.addObject("Red Alliance Left/Blue Alliance Right Half Court Reverse", new AutoStraightHCReverse());
		
		AUTO_CHOOSER.addObject("Red Alliance Right Half Court Reverse", new AutoRedRightHCReverse());
		AUTO_CHOOSER.addObject("Blue Alliance Left Half Court Reverse", new AutoBlueLeftHCReverse());
		
		AUTO_CHOOSER.addObject("Red Alliance Left Gear + Half Court", new AutoRedLeftGearHC());
		AUTO_CHOOSER.addObject("Red Alliance Right Gear + Half Court", new AutoRedRightGearHC());
		AUTO_CHOOSER.addObject("Blue Alliance Left Gear + Half Court", new AutoBlueLeftGearHC());
		AUTO_CHOOSER.addObject("Blue Alliance Right Gear + Half Court", new AutoBlueRightGearHC());
		
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

		// Vision
		VISION_SERVER = CameraServer.getInstance();

		VISION_CAMERA = VISION_SERVER.startAutomaticCapture("FRONT", 0);

		VISION_CAMERA.getProperty("saturation").set(20);
		VISION_CAMERA.getProperty("gain").set(50);
		VISION_CAMERA.getProperty("exposure_auto").set(1);
		VISION_CAMERA.getProperty("brightness").set(50);
		VISION_CAMERA.getProperty("exposure_absolute").set(1);
		VISION_CAMERA.getProperty("exposure_auto_priority").set(0);

	}

}
