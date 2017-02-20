package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FloorGearUpCommand extends Command {

	public FloorGearUpCommand() {
		requires(Robot.floorgear);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.floorgear.intakeOff();
		Robot.floorgear.blockerDown();
		Robot.floorgear.lifterUp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.floorgear.stopLifter();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
