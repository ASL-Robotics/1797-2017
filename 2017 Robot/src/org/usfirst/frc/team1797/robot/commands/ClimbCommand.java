package org.usfirst.frc.team1797.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The CommandCommand is designed to climb the rope at the end of the match.
 * 
 * @author will_vauclain@asl.org
 */
public class ClimbCommand extends CommandGroup {

    public ClimbCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
