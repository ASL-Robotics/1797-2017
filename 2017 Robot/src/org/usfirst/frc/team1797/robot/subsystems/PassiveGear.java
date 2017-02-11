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
	private DoubleSolenoid blockPiston1, blockPiston2, holdPiston1, holdPiston2;
	private long lastActuationBlock, lastActuationHold;
	private boolean blocked, held;
	
	public PassiveGear(){
		blockPiston1 = RobotMap.PASSIVEGEAR_BLOCK_1;
		blockPiston2 = RobotMap.PASSIVEGEAR_BLOCK_2;
		holdPiston1 = RobotMap.PASSIVEGEAR_HOLD_1;
		holdPiston2 = RobotMap.PASSIVEGEAR_HOLD_2;
		lastActuationBlock = Long.MAX_VALUE;
		lastActuationHold = Long.MAX_VALUE;
		blocked = false;
		held = false;
	}
	public void block(){
		blockPiston1.set(DoubleSolenoid.Value.kForward);
		blockPiston2.set(DoubleSolenoid.Value.kForward);
		lastActuationBlock = System.currentTimeMillis();
		blocked = true;
	}
	public void unblock(){
		blockPiston1.set(DoubleSolenoid.Value.kReverse);
		blockPiston2.set(DoubleSolenoid.Value.kReverse);
		lastActuationBlock = System.currentTimeMillis();
		blocked = false;
	}
	public void stopBlocker(){
		blockPiston1.set(DoubleSolenoid.Value.kOff);
		blockPiston2.set(DoubleSolenoid.Value.kOff);
		lastActuationBlock = Long.MAX_VALUE;
	}
	public void holdGear(){
		holdPiston1.set(DoubleSolenoid.Value.kForward);
		holdPiston2.set(DoubleSolenoid.Value.kForward);
		lastActuationHold = System.currentTimeMillis();
		held = true;
	}
	public void releaseGear(){
		holdPiston1.set(DoubleSolenoid.Value.kReverse);
		holdPiston2.set(DoubleSolenoid.Value.kReverse);
		lastActuationHold = System.currentTimeMillis();
		held = false;
	}
	public void stopHolder(){
		holdPiston1.set(DoubleSolenoid.Value.kOff);
		holdPiston1.set(DoubleSolenoid.Value.kOff);
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

