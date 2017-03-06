package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.SlotGearDefaultCommand;

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
	private DoubleSolenoid.Value lastAction;

	public SlotGear() {
		holdPiston = RobotMap.SLOTGEAR_HOLDER;
		lastActuation = Long.MAX_VALUE;
	}

	public void toggleHolder() {
		if (lastAction == DoubleSolenoid.Value.kReverse) {
			holdPiston.set(DoubleSolenoid.Value.kForward);
			lastAction = DoubleSolenoid.Value.kForward;
		}
		else {
			holdPiston.set(DoubleSolenoid.Value.kReverse);
			lastAction = DoubleSolenoid.Value.kReverse;
		}
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
		return lastAction == DoubleSolenoid.Value.kReverse;
	}

	public void initDefaultCommand() {
		this.setDefaultCommand(new SlotGearDefaultCommand());
	}
}
