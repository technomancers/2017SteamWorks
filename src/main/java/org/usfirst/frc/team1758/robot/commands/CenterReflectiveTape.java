package org.usfirst.frc.team1758.robot.commands;

public class CenterReflectiveTape extends CommandBase
{
	private boolean finished;
	public CenterReflectiveTape()
	{
		requires(vision);
	}
	
	public void initiliaze()
	{
		finished = false;
	}
	public void execute()
	{
		//Code for Finding target
		finished = true;
	}
	public boolean isFinished()
	{
		return finished;
	}
}
