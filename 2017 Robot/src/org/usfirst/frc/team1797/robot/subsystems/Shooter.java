package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private VictorSP conveyor, wheels;

	public Shooter() {
		conveyor = RobotMap.SHOOTER_CONVEYOR;
		wheels = RobotMap.SHOOTER_WHEELS;
	}
	
	public void conveyorOn(){
		conveyor.set(1);
	}
	
	public void conveyorOff(){
		conveyor.set(0);
	}
	
	public void wheelsOn(){
		wheels.set(1);
	}
	
	public void wheelsOff(){
		wheels.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
