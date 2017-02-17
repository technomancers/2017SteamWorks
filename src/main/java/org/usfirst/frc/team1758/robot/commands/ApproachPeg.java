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
		pid = new TechnoPID(1, 0, 0, 15.0);
		pid.setReference(80.0);
	}
	protected void execute()
	{
		if(sensors.getUltrasonicValue() > 20){
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
		double normalized = pidV / -120;
		if (Math.abs(normalized) < .4){
			normalized = .4 * Math.abs(normalized)/normalized;
		}
		driveTrain.tankDrive(normalized, normalized);
		if (pid.isDone()){
				driveTrain.tankDrive(.65, -.65);
		}
	}
}
