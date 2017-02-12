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
	private DoubleSolenoid holdPiston;
	private long lastActuation;

	public SlotGear() {
		holdPiston = RobotMap.SLOTGEAR_HOLDER;
		lastActuation = Long.MAX_VALUE;
	}

	public void toggleHolder() {
		if (holdPiston.get() == DoubleSolenoid.Value.kReverse)
			holdPiston.set(DoubleSolenoid.Value.kForward);
		else
			holdPiston.set(DoubleSolenoid.Value.kReverse);
		lastActuation = System.currentTimeMillis();
	}

	public void holderOff() {
		holdPiston.set(DoubleSolenoid.Value.kOff);
		lastActuation = Long.MAX_VALUE;
	}

	public boolean isDone() {
		return System.currentTimeMillis() + 1000 > lastActuation;
	}

	public boolean isHolding() {
		return holdPiston.get() == DoubleSolenoid.Value.kReverse;
	}

	public void initDefaultCommand() {
	}
}
