package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnLeft;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

public class AutonomousRight extends CommandGroup {

  public AutonomousRight() {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(12000));
    addSequential(new TurnLeft(35));
    addSequential(new Autonomous());
  }
}
