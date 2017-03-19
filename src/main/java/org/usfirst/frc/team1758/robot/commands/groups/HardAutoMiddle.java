package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

public class HardAutoMiddle extends CommandGroup {

  public HardAutoMiddle() {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(9931));
  }
}
