package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DriveDefaultCommand;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	private VictorSP leftControl, rightControl;
	private RobotDrive robotDrive;
	
	private DriveType driveType;
	
	
	public Drivetrain() {
		leftControl = RobotMap.DRIVETRAIN_LEFT;
		rightControl = RobotMap.DRIVETRAIN_RIGHT;
		robotDrive = new RobotDrive(leftControl,rightControl);
		
		driveType = DriveType.TANK;
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        this.setDefaultCommand(new DriveDefaultCommand());
    }


	public void teleopDrive(double leftValue, double rightValue) {
		switch (driveType) {
		case TANK:
			robotDrive.tankDrive(leftValue, rightValue);
		case ARCADE:
			robotDrive.arcadeDrive(leftValue,rightValue);
		default: System.out.println("Error: no drive type selected");
			break;
		}
	}


	public void resetDriveMotors() {
		robotDrive.drive(0, 0);
	}
	
	public void setMotors(double leftValue, double rightValue) {
		robotDrive.drive(leftValue, rightValue);
	}
	
	private enum DriveType {
		TANK, ARCADE;
	}
}

