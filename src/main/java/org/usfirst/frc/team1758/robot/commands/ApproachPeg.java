package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.utilities.TechnoPID;



public class ApproachPeg extends CommandBase
{
	private boolean finished;
	private TechnoPID pid;
	public ApproachPeg()
	{
		requires(vision);
		requires(sensors);
		requires(driveTrain);
	}
	protected void initialize()
	{
		finished = false;
		pid = new TechnoPID(1, 0, 0, 20.0);
		pid.setReference(80.0);
	}
	protected void execute()
	{
		if(vision.getNumberOfRectangles() >= 3 || vision.getNumberOfRectangles() < 2)
		{
			driveTrain.mecanumDriveCartesian(0, -.3, 0.0, 0.0);
		} else if(sensors.getUltrasonicValue() > 20){
			iterate();
		}else if(sensors.getUltrasonicValue() > 12){
			pid.setTolerance(5);
			iterate();
		}else if(pid.isDone()){
			finished = true;
		}
	}
	protected boolean isFinished()
	{
		return finished;
	} 

	private void iterate(){
		double pidV = pid.calculatePID(vision.getCenterX());
		double normalized = pidV / -400;
		if (Math.abs(normalized) < .2){
			normalized = .2 * Math.abs(normalized)/normalized;
		}
		driveTrain.mecanumDriveCartesian(0.0, 0.0, normalized, 0.0);;
		if (pid.isDone()){
				driveTrain.mecanumDriveCartesian(-.4, 0, 0.0, 0.0);
		}
	}
}
