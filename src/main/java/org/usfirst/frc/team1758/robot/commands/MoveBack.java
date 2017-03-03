package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;



public class MoveBack extends CommandBase {
	private boolean finished;

	public MoveBack() {
		requires(driveTrain);
	}

	protected void initialize() {
		finished = false;
		driveTrain.resetEncoderPosition();
		sensors.resetGyroAngle();
	}

	protected void execute() {
		if(driveTrain.getEncoderPosition(Motor.FrontRight) < -8000)
		{
			finished = true;
			driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
		} else {
			
			driveTrain.mecanumDriveCartesian(0, .3, 0, 0);
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
