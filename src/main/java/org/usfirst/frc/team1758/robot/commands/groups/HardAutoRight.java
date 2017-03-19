package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnLeft;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;

public class HardAutoRight extends CommandGroup {

  public HardAutoRight() {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(8000));
    addSequential(new TurnLeft(30));
    addSequential(new MoveBack(9931));
  }
}
