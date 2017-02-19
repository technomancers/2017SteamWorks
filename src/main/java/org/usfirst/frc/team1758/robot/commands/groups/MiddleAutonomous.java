package org.usfirst.frc.team1758.robot.commands.groups;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.commands.ApproachPeg;
import org.usfirst.frc.team1758.robot.commands.TurnOnCameraLight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleAutonomous extends CommandGroup{
	private Logger logger;
	public MiddleAutonomous(){
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Starting MiddleAutonomous command group");
		addSequential(new TurnOnCameraLight());
		addSequential(new ApproachPeg());
	}
}
