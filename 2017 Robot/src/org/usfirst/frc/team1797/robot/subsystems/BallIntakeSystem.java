package org.usfirst.frc.team1797.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallIntakeSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP topIntake, bottomIntake, rampCam;
	
	public BallIntakeSystem(){
		topIntake = new VictorSP(4);
		bottomIntake = new VictorSP(5);
		rampCam = new VictorSP(6);
	}
	public void intake(){
		topIntake.set(1);
		bottomIntake.set(-1);
	}
	public void outtake(){
		topIntake.set(-1);
		bottomIntake.set(1);
	}
	public void moveRamp(){
		rampCam.set(.1);
	}
	public void stopIntake(){
		topIntake.set(0);
		bottomIntake.set(0);
		rampCam.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

