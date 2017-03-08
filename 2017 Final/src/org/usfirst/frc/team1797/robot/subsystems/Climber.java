package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.ClimberDefaultCommand;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private VictorSP climber;

	public Climber() {
		climber = RobotMap.CLIMBER;
	}

	public void climb(double speed) {
		if (Math.abs(speed) > 0.05) 
			climber.set(speed);
		else
			stopClimb();
	}

	public void stopClimb() {
		climber.set(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ClimberDefaultCommand());
	}
}
