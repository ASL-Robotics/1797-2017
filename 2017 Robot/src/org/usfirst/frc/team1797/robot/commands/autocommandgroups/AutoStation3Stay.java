package org.usfirst.frc.team1797.robot.commands.autocommandgroups;

import org.usfirst.frc.team1797.robot.commands.DrivetrainStation3ProfileCommand;
import org.usfirst.frc.team1797.robot.commands.PlaceSlotGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStation3Stay extends CommandGroup {

    public AutoStation3Stay() {
    	addSequential(new DrivetrainStation3ProfileCommand());
        addSequential(new PlaceSlotGear());
    }
}
