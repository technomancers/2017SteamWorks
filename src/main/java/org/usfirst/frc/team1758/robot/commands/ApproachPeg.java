package org.usfirst.frc.team1758.robot.commands;

import javax.imageio.ImageWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;




import org.usfirst.frc.team1758.utilities.TechnoPID;



public class ApproachPeg extends CommandBase {
	private boolean finished;
	private TechnoPID pid;
	private Logger logger;

	public ApproachPeg() {
		logger = LoggerFactory.getLogger(this.getClass());
		requires(vision);
		requires(driveTrain);
		requires(sensors);
	}

	protected void initialize() {
		//numCycle = 0;
		pid = new TechnoPID(1,0,0,15.0);
		pid.setReference(RobotMap.CAMERA_WIDTH/2);
		finished = false;
	}

	protected void execute() {
		if(sensors.getUltrasonicValue() > 40)
		{
			iterate();
		} else if(sensors.getUltrasonicValue() > 35)
		{
			pid.setTolerance(5);
			iterate();
		} else if(pid.isDone())
		{
			finished = true;
		}
	}
	public void iterate()
	{
		double pidV = pid.calculatePID(vision.getCenterX());
		double normalized = pidV / -900;
		logger.trace("Normalized: {}", normalized);
		driveTrain.mecanumDriveCartesian(-.3, 0, normalized, 0.0);
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
