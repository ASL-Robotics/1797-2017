package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.RobotMap;
import org.usfirst.frc.team1797.robot.commands.DriveDefaultCommand;
import org.usfirst.frc.team1797.util.Gamepad;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public static final Drivetrain INSTANCE;
	static {
		INSTANCE = new Drivetrain(RobotMap.driverStick, 0, 1, 2, 3);
	}
	
	// Polymorphism, yay! We may need to switch to TalonSRXs later, in which case we should 
	private SpeedController left1, left2, right1, right2;
	private RobotDrive drive;
	private DriveType driveType;
	private Gamepad drivePad;
	
	private Drivetrain(Gamepad gamepad, int portLeft1, int portLeft2, int portRight1, int portRight2) {
		left1 = new VictorSP(portLeft1);
		left2 = new VictorSP(portLeft2);
		right1 = new VictorSP(portRight1);
		right2 = new VictorSP(portRight2);
		drive = new RobotDrive(left1, left2, right1, right2);
		driveType = DriveType.TANK;
		drivePad = gamepad;
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        this.setDefaultCommand(new DriveDefaultCommand());
    }


	public void teleopDrive() {
		switch (driveType) {
		case TANK:
			drive.tankDrive(drivePad.getLeftY(), drivePad.getRightY());
		case ARCADE:
			drive.arcadeDrive(drivePad.getLeftY(), drivePad.getLeftX());
		default: System.out.println("Error: no drive type selected");
			break;
		}
	}


	public void resetDriveMotors() {
		drive.drive(0, 0);
	}
	
	private enum DriveType {
		TANK, ARCADE;
	}
}

