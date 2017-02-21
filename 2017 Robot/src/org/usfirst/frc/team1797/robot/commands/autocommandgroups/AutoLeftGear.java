package org.usfirst.frc.team1797.robot.commands.autocommandgroups;

import org.usfirst.frc.team1797.robot.commands.DrivetrainStation1ProfileCommand;
import org.usfirst.frc.team1797.robot.commands.PlaceSlotGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftGear extends CommandGroup {

    public AutoLeftGear() {
        addSequential(new DrivetrainStation1ProfileCommand());
        addSequential(new PlaceSlotGear());
    }
}
