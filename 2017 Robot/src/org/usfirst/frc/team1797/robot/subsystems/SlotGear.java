package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SlotGear extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private DoubleSolenoid  holdPiston;
	private long  lastActuationHolder;

	public SlotGear() {
		holdPiston = RobotMap.SLOTGEAR_HOLDER;
		lastActuationHolder = Long.MAX_VALUE;
	}

	// Holder
	public void holderDown() {
		holdPiston.set(DoubleSolenoid.Value.kForward);
		lastActuationHolder = System.currentTimeMillis();
	}

	public void holderUp() {
		holdPiston.set(DoubleSolenoid.Value.kReverse);
		lastActuationHolder = System.currentTimeMillis();
	}

	public void holderOff() {
		holdPiston.set(DoubleSolenoid.Value.kOff);
		lastActuationHolder = System.currentTimeMillis();
	}

	public boolean isDone() {
		return System.currentTimeMillis() + 1000 > lastActuationHolder;
	}

	public boolean isHolding() {
		return holdPiston.get() == DoubleSolenoid.Value.kReverse;
	}

	public void initDefaultCommand() {
	}
}
