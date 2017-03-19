package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.OrientTarget;
import org.usfirst.frc.team1758.utilities.Configuration.AutonomousConfig;
import org.usfirst.frc.team1758.utilities.Configuration.VisionCameraConfig;

public class Autonomous extends CommandGroup {
  public Autonomous(AutonomousConfig configs, VisionCameraConfig cameraConfigs) {
    addSequential(new OrientTarget(configs.orient(), cameraConfigs));
    addSequential(new ApproachPeg(configs.approach(), cameraConfigs));
  }
}
