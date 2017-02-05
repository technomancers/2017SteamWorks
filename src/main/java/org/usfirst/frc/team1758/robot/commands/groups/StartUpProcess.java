package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartUpProcess extends CommandGroup {
	public StartUpProcess() {
		addParallel(new VisionStartUp());
		addParallel(new CalibrateSensors());
	}
}
