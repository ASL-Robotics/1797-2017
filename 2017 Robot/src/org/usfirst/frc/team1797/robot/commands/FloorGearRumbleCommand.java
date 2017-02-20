package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FloorGearRumbleCommand extends Command {
	
    public FloorGearRumbleCommand() {
        requires(Robot.floorgear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.oi.operatorController.setRumble(RumbleType.kRightRumble, 1.0);
    	this.setTimeout(.5);
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
    	Robot.oi.operatorController.setRumble(RumbleType.kRightRumble, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.oi.operatorController.setRumble(RumbleType.kRightRumble, 0.0);
    }
}
