package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallIntakeSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP intake, cam;
	
	public BallIntakeSystem(){
		intake = RobotMap.BALLINTAKE_INTAKE;
		cam = RobotMap.BALLINTAKE_CAM;
	}
	public void intake(){
		intake.set(1);
	}
	public void outtake(){
		intake.set(-1);
	}
	public void moveRamp(){
		cam.set(.5);
	}
	public void stopIntake(){
		intake.set(0);
		cam.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

