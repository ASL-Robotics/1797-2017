package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearOuttakeCommand extends Command {

    public GearOuttakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(RobotMap.GEARINTAKE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.GEARINTAKE.outtakeGear();
    	RobotMap.GEARINTAKE.clawDown();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(RobotMap.GEARINTAKE.getLastActuation() + 1000 <= System.currentTimeMillis()){
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.GEARINTAKE.clawPistonOff();
    	RobotMap.GEARINTAKE.gearWheelsOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.GEARINTAKE.clawPistonOff();
    	RobotMap.GEARINTAKE.gearWheelsOff();
    }
}
