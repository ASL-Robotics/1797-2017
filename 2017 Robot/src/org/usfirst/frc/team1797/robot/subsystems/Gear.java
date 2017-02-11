package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.util.AnalogForceResistor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private VictorSP intake;
	private DoubleSolenoid piston;

	private AnalogForceResistor leftForce, rightForce;
	
	
	private long lastActuation;

	public Gear() {
		intake = RobotMap.GEAR_INTAKE;
		piston = RobotMap.GEAR_PISTON;

		lastActuation = Long.MAX_VALUE;
		
		leftForce = RobotMap.GEAR_FORCE_LEFT;
		rightForce = RobotMap.GEAR_FORCE_RIGHT;
	}

	public void intakeGear() {
		intake.set(.5);
	}

	public void outtakeGear() {
		intake.set(-.5);
	}

	public void intakeOff() {
		intake.set(0);
	}

	public boolean isIn(){
		return (leftForce.getVoltage() >= 2.5 || rightForce.getVoltage() >= 2.5);
	}
	
	public void clawUp() {
		piston.set(DoubleSolenoid.Value.kForward);
		lastActuation = System.currentTimeMillis();
	}

	public void clawDown() {
		piston.set(DoubleSolenoid.Value.kReverse);
		lastActuation = System.currentTimeMillis();
	}

	public void clawPistonOff() {
		piston.set(DoubleSolenoid.Value.kOff);
		lastActuation = Long.MAX_VALUE;
	}

	public long getLastActuation() {
		return lastActuation;
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
