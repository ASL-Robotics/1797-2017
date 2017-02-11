package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PassiveGearHoldCommand extends Command {

	public PassiveGearHoldCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.passivegear);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Robot.passivegear.isHeld())
			Robot.passivegear.holdGear();
		else
			Robot.passivegear.releaseGear();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.passivegear.getLastActuationHold() + 1000 <= System.currentTimeMillis();
	}

	// Called once after isFinished returns true
	protected void end() {
		interrupted();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.passivegear.stopHolder();
	}
}
