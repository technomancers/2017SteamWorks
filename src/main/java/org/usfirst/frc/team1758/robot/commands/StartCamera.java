package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.Vision.CameraMode;



public class StartCamera extends CommandBase
{
	private boolean finished;
	private CameraMode modeInUse;
	public StartCamera()
	{
		this(CameraMode.FRONT);
	}
	public StartCamera(CameraMode cameraMode)
	{
		modeInUse = cameraMode;
	}

	protected void intitizialize()
	{
		finished = false;
		requires(vision);
	}
	protected void execute()
	{
		vision.switchToCamera(modeInUse);
		finished = true;
	}
	protected boolean isFinished()
	{
		return finished;	
	}
}
