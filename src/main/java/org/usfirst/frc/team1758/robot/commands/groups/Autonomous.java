package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.OrientTarget;

public class Autonomous extends CommandGroup {
  public Autonomous() {
    addSequential(new OrientTarget());
    addSequential(new ApproachPeg());
  }
}
