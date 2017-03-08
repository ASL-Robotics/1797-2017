package org.usfirst.frc.team1797.robot;

import org.usfirst.frc.team1797.robot.commands.DrivetrainShiftGearCommand;
import org.usfirst.frc.team1797.robot.commands.FloorGearDownCommand;
import org.usfirst.frc.team1797.robot.commands.FloorGearOuttakeCommand;
import org.usfirst.frc.team1797.robot.commands.FloorGearUpCommand;
import org.usfirst.frc.team1797.util.XBox360;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public XBox360 driverController;
	public XBox360 operatorController;

	public OI() {
		// Omar
		driverController = new XBox360(0);
		driverController.getLeftStick().whenPressed(new DrivetrainShiftGearCommand());

		// Anna
		operatorController = new XBox360(1);
		operatorController.getYButton().whileHeld(new FloorGearOuttakeCommand());

		operatorController.getNorthEast().whenPressed(new FloorGearUpCommand());
		operatorController.getNorth().whenPressed(new FloorGearUpCommand());
		operatorController.getNorthWest().whenPressed(new FloorGearUpCommand());

		operatorController.getSouthEast().whenPressed(new FloorGearDownCommand());
		operatorController.getSouth().whenPressed(new FloorGearDownCommand());
		operatorController.getSouthWest().whenPressed(new FloorGearDownCommand());

	}

}
