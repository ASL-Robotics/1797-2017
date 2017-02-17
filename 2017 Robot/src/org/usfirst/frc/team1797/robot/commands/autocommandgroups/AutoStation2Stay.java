package org.usfirst.frc.team1797.robot.commands.autocommandgroups;

import org.usfirst.frc.team1797.robot.commands.DrivetrainStation2ProfileCommand;
import org.usfirst.frc.team1797.robot.commands.PlaceSlotGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStation2Stay extends CommandGroup {

    public AutoStation2Stay() {
    	addSequential(new DrivetrainStation2ProfileCommand());
        addSequential(new PlaceSlotGear());
    }
}
