package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.MoveForward;
import org.usfirst.frc.team1758.robot.commands.ToggleGear;

public class GearPushAndPull extends CommandGroup {

  public GearPushAndPull() {
    addSequential(new ToggleGear());
    addSequential(new ToggleGear());
    addSequential(new ToggleGear());
    addSequential(new MoveForward(4000));
    addSequential(new ToggleGear());
  }
}
