package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.MoveBack;
import org.usfirst.frc.team1758.robot.commands.TurnLeft;
import org.usfirst.frc.team1758.robot.commands.TurnOnLight;
import org.usfirst.frc.team1758.utilities.Configuration.AutonomousConfig;
import org.usfirst.frc.team1758.utilities.Configuration.VisionCameraConfig;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousRight extends CommandGroup {

	public AutonomousRight(AutonomousConfig configs, VisionCameraConfig cameraConfigs) {
		addSequential(new TurnOnLight());
		addSequential(new MoveBack(configs.blind().moveBack()));
		addSequential(new TurnLeft(configs.blind().turnLeft()));
		addSequential(new Autonomous(configs, cameraConfigs));
	}
}
