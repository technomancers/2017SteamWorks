package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.TurnRight;

public class AutonomousLeft extends CommandGroup {

  public AutonomousLeft() {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(8000));
    addSequential(new TurnRight(30.0));
    addSequential(new Autonomous());
  }
}
