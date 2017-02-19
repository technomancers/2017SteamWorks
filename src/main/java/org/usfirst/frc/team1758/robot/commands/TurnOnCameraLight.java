package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TurnOnCameraLight extends CommandBase
{
	private boolean finished;
	private Logger logger;
	public TurnOnCameraLight()
	{
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Created TurnOnCameraLight Command");
		requires(vision);
	}
	protected void initialize()
	{
		finished = false;
	}
	protected void execute()
	{
		logger.debug("Started TurnOnCameraLight Command");
		vision.turnOnCameraLight();
		logger.debug("Finished TurnOnCameraLight Command");
		finished = true;
	}
	protected boolean isFinished()
	{
		return finished;
	}
}
