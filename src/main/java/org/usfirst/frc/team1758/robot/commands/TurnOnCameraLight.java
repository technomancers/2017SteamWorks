package org.usfirst.frc.team1758.robot.commands;

public class TurnOnCameraLight extends CommandBase
{
	private boolean finished;
	public TurnOnCameraLight()
	{
		requires(vision);
	}
	protected void initialize()
	{
		finished = false;
	}
	protected void execute()
	{
		vision.turnOnCameraLight();
		finished = true;
	}
	protected boolean isFinished()
	{
		return finished;
	}
}
