package org.usfirst.frc.team1758.robot.commands;

public class SwitchCamera extends CommandBase{
	private static boolean finished;
	private static cameraInUse cameraUse;
	
	public enum cameraInUse
	{
		BACK, FRONT, TOGGLE;
	}
	public SwitchCamera()
	{
		this(cameraInUse.TOGGLE);
	}
	public SwitchCamera(cameraInUse camera)
	{
		//requires(vision);
		cameraUse = camera;
	}
	protected void initialize()
	{
		finished = false;
	}
	protected void execute() {
		switch(cameraUse){
			case BACK:
			vision.switchToBackCamera();
			break;
			case FRONT:
			vision.switchToFrontCamera();
			default:
			vision.turnOnCamera();
			break;
		}
		finished = true;
	}
	protected boolean isFinished()
	{
		return finished;
	}
	protected void end()
	{	
	}
	protected void interrupted()
	{
	}
}
	