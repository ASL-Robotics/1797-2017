package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.util.AnalogForceResistor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FloorGear extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private VictorSP intake;
	private DoubleSolenoid liftPiston, blockPiston;

	private AnalogForceResistor leftForce, rightForce;
	
	private long lastActuationLifter, lastActuationBlocker;

	public FloorGear() {
		intake = RobotMap.FLOORGEAR_INTAKE;
		liftPiston = RobotMap.FLOORGEAR_LIFTER;
		blockPiston = RobotMap.FLOORGEAR_BLOCKER;

		leftForce = RobotMap.FLOORGEAR_FORCE_LEFT;
		rightForce = RobotMap.FLOORGEAR_FORCE_RIGHT;
		
		lastActuationLifter = Long.MAX_VALUE;
		lastActuationBlocker = Long.MAX_VALUE;
	}

	//Intake
	public void intake() {
		intake.set(1);
	}

	public void outtake() {
		intake.set(-1);
	}

	public void intakeOff() {
		intake.set(0);
	}

	public boolean isIn(){
		return (leftForce.getVoltage() >= 2.5 || rightForce.getVoltage() >= 2.5);
	}
	
	//Claw
	public void lifterUp() {
		liftPiston.set(DoubleSolenoid.Value.kForward);
		lastActuationLifter = System.currentTimeMillis();
	}

	public void lifterDown() {
		liftPiston.set(DoubleSolenoid.Value.kReverse);
		lastActuationLifter = System.currentTimeMillis();
	}

	public void liftOff() {
		liftPiston.set(DoubleSolenoid.Value.kOff);
		lastActuationLifter = Long.MAX_VALUE;
	}

	public boolean lifterIsDone() {
		return System.currentTimeMillis() + 1000 > lastActuationLifter;
	}

	public boolean isLifting() {
		return blockPiston.get() == DoubleSolenoid.Value.kForward;
	}
	
	// Blocker
	public void blockerDown() {
		blockPiston.set(DoubleSolenoid.Value.kForward);
		lastActuationBlocker = System.currentTimeMillis();
	}

	public void blockerUp() {
		blockPiston.set(DoubleSolenoid.Value.kReverse);
		lastActuationBlocker = System.currentTimeMillis();
	}

	public void stopBlocker() {
		blockPiston.set(DoubleSolenoid.Value.kOff);
		lastActuationBlocker = Long.MAX_VALUE;
	}

	public boolean blockerIsDone() {
		return System.currentTimeMillis() + 1000 > lastActuationBlocker;
	}

	public boolean isBlocking() {
		return blockPiston.get() == DoubleSolenoid.Value.kForward;
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
