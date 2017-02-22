package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.utilities.TechnoPID;



public class CenterRobotTarget extends CommandBase {
	private boolean finished;
	private TechnoPID pidRot, pidCent;
	private Logger logger;
	private boolean firstTime;

	public CenterRobotTarget() {
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("CenterRobotTarget command created.");
		requires(vision);
		requires(driveTrain);
		
	}

	protected void initialize() {
		pidRot = new TechnoPID(.7, 0, 0, .5 / vision.getAreaOfBiggestRectangle());
		pidRot.setMinCycleCount(5);
		pidCent = new TechnoPID(1.1, 0 ,0, 20.0);
		pidCent.setReference(RobotMap.CAMERA_WIDTH / 2);
		finished = false;
		firstTime = true;
	}

	protected void execute() 
	{
		if (firstTime){
			logger.debug("CenterRobotTarget command started");
			firstTime = false;
		}
		pidRot.setReference(vision.getLeftMost().area());
		double rotV = pidRot.calculatePID(vision.getRightMost().area());
		double centerV = pidCent.calculatePID(vision.getCenterX());
		if(pidRot.isDone() && pidCent.isDone()){
			finished = true;
		}else{
			logger.trace("rotV: {} centerV: {}", rotV, centerV);
			double normRotV = rotV / vision.getAreaOfBiggestRectangle();
			if(Math.abs(normRotV) < 0.2){
				normRotV = 0.2 * Math.abs(normRotV)/normRotV;
			}
			if(Math.abs(normRotV) > 0.8){
				normRotV = 0.8 * Math.abs(normRotV)/normRotV;
			}
			double normCenterV = centerV / -300.0;
			driveTrain.mecanumDriveCartesian(0, -normRotV, normCenterV, 0.0);
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
