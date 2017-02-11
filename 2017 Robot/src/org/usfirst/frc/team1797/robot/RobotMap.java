package org.usfirst.frc.team1797.robot;

import org.usfirst.frc.team1797.robot.subsystems.BallIntakeSystem;
import org.usfirst.frc.team1797.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team1797.robot.subsystems.GearIntake;
import org.usfirst.frc.team1797.robot.subsystems.PassiveGearSystem;
import org.usfirst.frc.team1797.util.Gamepad;

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
	public static final PassiveGearSystem PASSIVEGEARSYSTEM = new PassiveGearSystem();
	public static final BallIntakeSystem BALLINTAKESYSTEM = new BallIntakeSystem();
	public static Gamepad driverStick, operatorStick;
	
	public static void init(){
		driverStick = new Gamepad(0);
		operatorStick = new Gamepad(1);
	}

}
