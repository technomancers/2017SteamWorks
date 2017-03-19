package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnLeft;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.utilities.Configuration.AutonomousConfig;

public class AutonomousRight extends CommandGroup {

  public AutonomousRight(AutonomousConfig configs) {
    addSequential(new TurnOnLight());
    addSequential(new MoveBack(configs.blind().moveBack()));
    addSequential(new TurnLeft(configs.blind().turnLeft()));
    addSequential(new Autonomous(configs));
  }
}
