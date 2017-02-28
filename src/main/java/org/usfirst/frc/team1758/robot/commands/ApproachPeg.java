package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.utilities.TechnoPID;

public class ApproachPeg extends CommandBase {
	private boolean finished;
	private TechnoPID pid, pidRot;
	private Logger logger;
	private int counter;

	public ApproachPeg() {
		logger = LoggerFactory.getLogger(this.getClass());
		requires(vision);
		requires(driveTrain);
		requires(sensors);
	}

	protected void initialize() {
		pid = new TechnoPID(1.6,0,0,5.0);
		pid.setReference(RobotMap.CAMERA_WIDTH/2);
		sensors.resetGyroAngle();
		finished = false;
		counter = 0;
	}

	protected void execute() {
		logger.trace("Gyro: {} Center: {}", sensors.getUltrasonicValue(), isCentered());
		if(isDone())
		{
			finished = true;
			driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
		} else {
			iterate();
		}
		
	}
	public void iterate()
	{
		double x, y, rotate;
		x = 0;
		y = 0;
		rotate = 0;
		if(sensors.getUltrasonicValue() > 10){
			x = -0.3;
		}
		if(!isCentered()){
			y =  -.001 * (vision.getCenterX() - RobotMap.CAMERA_WIDTH/2);  
		}
		//logger.trace("Normalized: {}", normalized);
		logger.trace("Angle: {}",.3 * sensors.getGyroAngle());
		driveTrain.mecanumDriveCartesian(x,  y, rotate, sensors.getGyroAngle());
	}
	

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}
	private boolean isCentered(){
		return (vision.getCenterX() < (RobotMap.CAMERA_WIDTH/2) +5) && (vision.getCenterX() > (RobotMap.CAMERA_WIDTH/2)-5);
	}
	private boolean isDone(){
		if(sensors.getUltrasonicValue() <  35 && isCentered() || vision.getNumRectangles() < 2){
			counter++;
		}
		else{
			counter = 0;
		}
		return counter > 2;
	}
	protected void interrupted() {
	}
}
