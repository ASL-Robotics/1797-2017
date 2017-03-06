package org.usfirst.frc.team1797.robot.commands.autocommandgroups;

import org.usfirst.frc.team1797.robot.commands.DrivetrainRedLeftCommand;
import org.usfirst.frc.team1797.robot.commands.PlaceSlotGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftGear extends CommandGroup {

    public AutoLeftGear() {
        addSequential(new DrivetrainRedLeftCommand());
        addSequential(new PlaceSlotGear());
    }
}
