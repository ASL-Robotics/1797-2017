package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Storage extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private VictorSP intake, cam;

	public Storage() {
		intake = RobotMap.STORAGE_INTAKE;
		cam = RobotMap.STORAGE_AGITATOR;
	}

	public void intake() {
		intake.set(1);
	}
	
	public void outtake(){
		intake.set(-1);
	}
	
	public void stopIntake() {
		intake.set(0);
	}

	public void agitate() {
		cam.set(.5);
	}

	public void stopAgitate(){
		cam.set(0);
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
