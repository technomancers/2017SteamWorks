package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.TurnRight;

public class AutonomousLeft extends CommandGroup {

  public AutonomousLeft() {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(15735));
    addSequential(new TurnRight(40));
    addSequential(new Autonomous(4000));
  }
}
