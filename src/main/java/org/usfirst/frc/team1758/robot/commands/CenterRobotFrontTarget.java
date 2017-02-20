package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.utilities.TechnoPID;



public class CenterRobotFrontTarget extends CommandBase {
	private boolean finished;
	TechnoPID pid;

	public CenterRobotFrontTarget() {
		requires(vision);
		requires(driveTrain);
	}

	protected void initialize() {
		pid = new TechnoPID(1, 0, 0, 20.0); 
		finished = false;
	}

	protected void execute() 
	{
		if(vision.isOriented())
		{
			if(vision.getCenterOfBiggestRectangle() < 150)
			{
				driveTrain.mecanumDriveCartesian(0, -.3, 0, 0);
			} else if(vision.getCenterOfBiggestRectangle() > 170)
			{
				driveTrain.mecanumDriveCartesian(0, .3, 0, 0);
			}
		} else {
			double pidv = pid.calculatePID(vision.getCenterX());
			double normalized = pidv/-120;
			if (Math.abs(normalized) < .2){ 
      normalized = .2 * Math.abs(normalized)/normalized; 
    	} 
			driveTrain.mecanumDriveCartesian(0, 0, normalized, 0);
			if(vision.isOriented() && (pidv < 2.0 && pidv > -2.0))
			{
				finished = true;
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
