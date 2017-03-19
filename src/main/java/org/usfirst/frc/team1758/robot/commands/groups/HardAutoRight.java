package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnLeft;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.utilities.Configuration.BlindConfig;

public class HardAutoRight extends CommandGroup {

  public HardAutoRight(BlindConfig configs) {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(configs.moveBack()));
    addSequential(new TurnLeft(configs.turnLeft()));
    addSequential(new MoveBack(configs.finalBack()));
  }
}
