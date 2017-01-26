package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private VictorSP climber;
	
	public Climber(){
		climber = RobotMap.CLIMBER;
	}
	public void climb(){
		climber.set(.1);
	}
	public void descend(){
		climber.set(-.1);
	}
	public void stopClimb(){
		climber.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

