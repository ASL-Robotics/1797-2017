package org.usfirst.frc.team1797.robot.commands.autocommandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStation3Baseline extends CommandGroup {

    public AutoStation3Baseline() {
    	addSequential(new AutoStation3Stay());
    }
}
