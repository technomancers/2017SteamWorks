package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.robot.commands.TurnRight;
import org.usfirst.frc.team1758.utilities.Configuration.BlindConfig;

public class HardAutoLeft extends CommandGroup {

  public HardAutoLeft(BlindConfig configs) {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(configs.moveBack()));
    addSequential(new TurnRight(configs.turnRight()));
    addSequential(new MoveBack(configs.finalBack()));
  }
}
