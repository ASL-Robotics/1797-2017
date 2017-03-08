package org.usfirst.frc.team1797.robot.commands.autoprofiles;

import org.usfirst.frc.team1797.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainBlueLeftHCReverseCommand extends Command {

	public DrivetrainBlueLeftHCReverseCommand() {
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.trajectory(5, true);
		Robot.drivetrain.invertEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.runProfile();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.drivetrain.profileIsDone();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.resetDriveMotors();
		Robot.drivetrain.invertEncoders();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
