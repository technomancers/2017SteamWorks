package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.utilities.TechnoPID;

public class ApproachPeg extends CommandBase {
	private boolean finished;
	private TechnoPID pid, pidRot;
	private Logger logger;

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
	}

	protected void execute() {
		if(sensors.getUltrasonicValue() > 40 && vision.getNumRectangles() >= 2)
		{
			iterate();
		} else {
			finished = true;
			driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
		}
		
	}
	public void iterate()
	{
		double pidV = pid.calculatePID(vision.getCenterX());
		if(pid.isDone()){
			sensors.resetGyroAngle();
	  	driveTrain.mecanumDriveCartesian(-.3, 0.0, 0.0, sensors.getGyroAngle());
		}else{
			double normalized = pidV / -256;
			// if(Math.abs(normalized) < .15){
			// 	normalized = .15 * Math.abs(normalized)/normalized;
			// }
			// if(Math.abs(normRotV) < 0.2){
			// 	normRotV = 0.2 * Math.abs(normRotV)/normRotV;
			// }
			// if(Math.abs(normRotV) > 0.8){
			// 	normRotV = 0.8 * Math.abs(normRotV)/normRotV;
			// }
			logger.trace("Normalized: {}", normalized);
			logger.trace("Angle: {}",.3 * sensors.getGyroAngle());
			driveTrain.mecanumDriveCartesian(-.3, -.4 * normalized,0, sensors.getGyroAngle());
		}
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
