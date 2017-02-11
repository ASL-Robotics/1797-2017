package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.Robot;
import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DescendCommand extends Command {

    public DescendCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.descend();
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
    	Robot.climber.stopClimb();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.stopClimb();
    }
}
