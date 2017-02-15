package org.usfirst.frc.team1758.robot.commands.groups;

import org.usfirst.frc.team1758.robot.commands.StartAutomaticCapture;
import org.usfirst.frc.team1758.robot.commands.TurnOnCameraLight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionStartUp extends CommandGroup {
	public VisionStartUp() {
		addSequential(new TurnOnCameraLight());
		addSequential(new StartAutomaticCapture());
	}
}
