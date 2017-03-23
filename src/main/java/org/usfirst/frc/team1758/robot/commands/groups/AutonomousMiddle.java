package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

public class AutonomousMiddle extends CommandGroup {
  public AutonomousMiddle() {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(3200));
    addSequential(new Autonomous(4000));
  }
}
