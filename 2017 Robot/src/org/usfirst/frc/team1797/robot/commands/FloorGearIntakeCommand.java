package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FloorGearIntakeCommand extends Command {

	public FloorGearIntakeCommand() {
		requires(Robot.floorgear);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.floorgear.lifterDown();
		Robot.floorgear.blockerUp();
		Robot.floorgear.intake(); 
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.floorgear.isIn();
	}

	// Called once after isFinished returns true
	protected void end() {
		interrupted();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.floorgear.intakeOff();
		Robot.floorgear.blockerDown();
		Robot.floorgear.lifterUp();
	}
}
