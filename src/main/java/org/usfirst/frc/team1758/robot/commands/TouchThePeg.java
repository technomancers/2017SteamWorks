package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;




public class TouchThePeg extends CommandBase {
	private boolean finished;
	private double averageEncoderPosition;

	public TouchThePeg() {
		requires(driveTrain);
	}

	protected void initialize() {
		finished = false;
		driveTrain.resetEncoderPosition();
	}

	protected void execute() {
		if(driveTrain.getEncoderPosition(Motor.FrontRight) < -400)
		{
			finished = true;
			driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
		} else {
			driveTrain.mecanumDriveCartesian(0,	-.25, 0, 0);
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
