package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PassiveGear extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private DoubleSolenoid blockPiston, holdPiston;
	private long lastActuationBlock, lastActuationHold;
	private boolean blocked, held;
	
	public PassiveGear(){
		blockPiston = RobotMap.PASSIVEGEAR_BLOCK;
		holdPiston = RobotMap.PASSIVEGEAR_HOLD;
		lastActuationBlock = Long.MAX_VALUE;
		lastActuationHold = Long.MAX_VALUE;
		blocked = false;
		held = false;
	}
	public void block(){
		blockPiston.set(DoubleSolenoid.Value.kForward);
		lastActuationBlock = System.currentTimeMillis();
		blocked = true;
	}
	public void unblock(){
		blockPiston.set(DoubleSolenoid.Value.kReverse);
		lastActuationBlock = System.currentTimeMillis();
		blocked = false;
	}
	public void stopBlocker(){
		blockPiston.set(DoubleSolenoid.Value.kOff);
		lastActuationBlock = Long.MAX_VALUE;
	}
	public void holdGear(){
		holdPiston.set(DoubleSolenoid.Value.kForward);
		lastActuationHold = System.currentTimeMillis();
		held = true;
	}
	public void releaseGear(){
		holdPiston.set(DoubleSolenoid.Value.kReverse);
		lastActuationHold = System.currentTimeMillis();
		held = false;
	}
	public void stopHolder(){
		holdPiston.set(DoubleSolenoid.Value.kOff);
		lastActuationHold = System.currentTimeMillis();
	}
	public long getLastActuationHold(){
		return lastActuationHold;
	}
	public long getLastActuationBlock(){
		return lastActuationBlock;
	}
	public boolean isBlocked(){
		return blocked;
	}
	public boolean isHeld(){
		return held;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

