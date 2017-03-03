package org.usfirst.frc.team1758.robot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.utilities.TechnoPID;

public class CenterRobotTarget extends CommandBase {
	private boolean finished;
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
		lastCycle = 0.0;
		finished = false;
		firstTime = true;
	}

	protected void execute() {
		if (firstTime) {
			logger.debug("CenterRobotTarget command started");
			firstTime = false;
		}
		if (isDone()) {
			finished = true;
		} else {
			double x, y, rot;
			x = 0;
			y = 0;
			rot = 0;
			if (!isCentered()) {
				rot = (vision.getCenterX() - RobotMap.CAMERA_WIDTH / 2) / (4 * (RobotMap.CAMERA_WIDTH / 2));
			}
			if (!oriented()) {
				y = (vision.getLeftMost().area() - vision.getRightMost().area()) / -300;
			}
			logger.trace("Y: {}, ROT: {}", y, rot);
			driveTrain.mecanumDriveCartesian(x, y, rot, 0.0);
		}
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

	private boolean isCentered() {
		return vision.getCenterX() < RobotMap.CAMERA_WIDTH / 2 + 15 && vision.getCenterX() > RobotMap.CAMERA_WIDTH / 2 - 15;
	}

	private boolean oriented() {
		return Math.abs(vision.getLeftMost().area() - vision.getRightMost().area()) < 50;
	}

	private boolean isDone() {
		return oriented() && isCentered();
	}
}
