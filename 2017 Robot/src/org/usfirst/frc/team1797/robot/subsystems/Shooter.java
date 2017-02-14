package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	private VictorSP conveyor;
	private PIDController pid;

	public Shooter() {
		conveyor = RobotMap.SHOOTER_CONVEYOR;
		pid = RobotMap.SHOOTER_PID;
	}
	
	public void conveyorOn(){
		conveyor.set(1);
	}
	
	public void conveyorOff(){
		conveyor.set(0);
	}
	
	public void wheelsOn(){
		pid.enable();
	}
	
	public void wheelsOff(){
		pid.disable();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
