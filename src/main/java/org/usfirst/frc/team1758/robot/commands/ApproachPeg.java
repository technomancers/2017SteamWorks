package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.utilities.TechnoPID;



public class ApproachPeg extends CommandBase
{
	private boolean finished;
	private TechnoPID pid;
	private boolean firstTime;
	private Logger logger;
	public ApproachPeg()
	{
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("Created ApproachPeg command");
		requires(vision);
		requires(sensors);
		requires(driveTrain);
	}
	protected void initialize()
	{
		finished = false;
		firstTime = true;
		pid = new TechnoPID(1, 0, 0, 20.0);
		pid.setReference(80.0);
		pid.setContinuous(0, RobotMap.CAMERA_WIDTH);
	}
	protected void execute()
	{
		if(firstTime){
			logger.debug("Starting ApproachPeg command");
			firstTime = false;
		}
		if(vision.getNumberOfRectangles() >= 3 || vision.getNumberOfRectangles() < 2)
		{
			logger.warn("Too many rectangles or too few rectangles to know what to do. Moving on");
			driveTrain.mecanumDriveCartesian(0, -.3, 0.0, 0.0);
		} else if(sensors.getUltrasonicValue() > 20){
			logger.trace("Far from target");
			iterate();
		}else if(sensors.getUltrasonicValue() > 12){
			logger.trace("Close to target");
			pid.setTolerance(5);
			iterate();
		}else if(pid.isDone()){
			logger.debug("ApproachPeg command finished");
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
		logger.trace("Turning towards target: {}", normalized);
		driveTrain.mecanumDriveCartesian(0.0, 0.0, normalized, 0.0);;
		if (pid.isDone()){
				logger.trace("No furthur centring needed. Moving towards it.");
				driveTrain.mecanumDriveCartesian(-.4, 0, 0.0, 0.0);
		}
	}
}
