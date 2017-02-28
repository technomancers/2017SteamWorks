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
	private double lastCycle;
	public CenterRobotTarget() {
		logger = LoggerFactory.getLogger(this.getClass());
		logger.debug("CenterRobotTarget command created.");
		requires(vision);
		requires(driveTrain);
		
	}

	protected void initialize() {
		pidRot = new TechnoPID(.7, 0, 0, 100 / vision.getBigRect().area());
		pidCent = new TechnoPID(1.1, 0 ,0, 30.0);
		pidCent.setReference(RobotMap.CAMERA_WIDTH / 2);
		lastCycle = 0.0;
		finished = false;
		firstTime = true;
	}

	protected void execute() 
	{
		double time = System.currentTimeMillis();
		if(time - lastCycle > 100)
		{
			lastCycle = time;
			if (firstTime){
				logger.debug("CenterRobotTarget command started");
				firstTime = false;
			}
			// pidRot.setReference(vision.getLeftMost().area());
			// double rotV = pidRot.calculatePID(vision.getRightMost().area());
			// double centerV = pidCent.calculatePID(vision.getCenterX());
			if(((vision.getCenterX() < RobotMap.CAMERA_WIDTH/2 +5 && vision.getCenterX() > RobotMap.CAMERA_WIDTH/2-5))){
				finished = true;
			}else{
			//logger.trace("rotV: {} centerV: {}", rotV, centerV);
				double normRotV = (vision.getLeftMost().area() - vision.getRightMost().area()) / vision.getBigRect().area();
				double centerV = .05* (vision.getCenterX() - RobotMap.CAMERA_WIDTH/2);
				double normCenterV = centerV / -300.0;
				driveTrain.mecanumDriveCartesian(0, -normRotV, normCenterV, 0.0);
			}
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
