package org.usfirst.frc.team1758.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import edu.wpi.first.wpilibj.commands.StartUltrasonic;

public class StartUpProcess extends CommandGroup {
	public StartUpProcess() {
		//addSequential(new StartUltrasonic());
		addParallel(new VisionStartUp());
		addParallel(new CalibrateSensors());
	}
}
