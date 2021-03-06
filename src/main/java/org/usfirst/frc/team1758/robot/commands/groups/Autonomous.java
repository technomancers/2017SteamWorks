package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.OrientTarget;

public class Autonomous extends CommandGroup {
  public Autonomous(int distance) {
    addSequential(new OrientTarget());
    addSequential(new ApproachPeg());
    addSequential(new MoveBack(distance));
    addSequential(new GearDeploy());
  }
}
