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
		pid = new TechnoPID(1.6,0,0,15.0);
		pidRot = new TechnoPID(.7, 0, 0, .4 / vision.getBigRect().width);
		pid.setReference(RobotMap.CAMERA_WIDTH/2);
		finished = false;
	}

	protected void execute() {
		if(sensors.getUltrasonicValue() > 40)
		{
			iterate();
		} 
		else if(sensors.getUltrasonicValue() > 30)
		{
			pid.setTolerance(5);
			iterate();
		} else {
			finished = true;
		}
		
	}
	public void iterate()
	{
		double pidV = pid.calculatePID(vision.getCenterX());
		pidRot.setReference(vision.getLeftMost().width);
		double rotV = pidRot.calculatePID(vision.getRightMost().width);
		if(pid.isDone() && pidRot.isDone()){
			sensors.resetGyroAngle();
			driveTrain.mecanumDriveCartesian(-.3, 0.0, 0.0, sensors.getGyroAngle());
		}else{
			double normalized = pidV / -256;
			if(Math.abs(normalized) < .15){
				normalized = .15 * Math.abs(normalized)/normalized;
			}
			double normRotV = rotV / vision.getBigRect().width;
			if(Math.abs(normRotV) < 0.2){
				normRotV = 0.2 * Math.abs(normRotV)/normRotV;
			}
			if(Math.abs(normRotV) > 0.8){
				normRotV = 0.8 * Math.abs(normRotV)/normRotV;
			}
			logger.trace("Normalized: {}", normalized);
			driveTrain.mecanumDriveCartesian(0.0, normRotV, normalized, 0.0);
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
