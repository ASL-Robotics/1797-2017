package org.usfirst.frc.team1797.robot;

import org.usfirst.frc.team1797.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team1797.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static final GearIntake GEARINTAKE = new GearIntake();
	public static final ClimbSystem CLIMBSYSTEM = new ClimbSystem();
	
	
	//Components necessary for Drivetrain
	public static VictorSP DRIVETRAIN_LEFT, DRIVETRAIN_RIGHT;
	
	//Components necessary for Active Gear
	public static VictorSP GEAR_LEFT_INTAKE, GEAR_RIGHT_INTAKE;
	public static DoubleSolenoid GEAR_LIFT_PISTON;
	
	//Components necessary for Climber
	public static VictorSP CLIMBER;
	
	public static void init(){
		
		//Drivetrain
		DRIVETRAIN_LEFT = new VictorSP(0);
		DRIVETRAIN_RIGHT = new VictorSP(1);
		
		//Active Gear
		GEAR_LEFT_INTAKE = new VictorSP(2);
		GEAR_RIGHT_INTAKE = new VictorSP(3);
		GEAR_LIFT_PISTON = new DoubleSolenoid(0,1);
		
		//Climber
		CLIMBER = new VictorSP(4);
		
	}

}
