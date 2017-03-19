package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.OrientTarget;
import org.usfirst.frc.team1758.utilities.Configuration.AutonomousConfig;

public class Autonomous extends CommandGroup {
  public Autonomous(AutonomousConfig configs) {
    addSequential(new OrientTarget(configs.orient()));
    addSequential(new ApproachPeg(configs.approach()));
  }
}
