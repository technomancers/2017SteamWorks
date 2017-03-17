package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;



public class MoveBackTillTarget extends CommandBase {
	private boolean finished;

	public MoveBackTillTarget() {
		requires(driveTrain);
		requires(sensors);
	}

	protected void initialize() {
		finished = false;
		driveTrain.resetEncoderPosition();
		sensors.resetGyroAngle();
	}

	protected void execute() {
		if(sensors.getUltrasonicValue() < 60)
		{
			finished = true;
			driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
		} else {
			
			driveTrain.mecanumDriveCartesian(0, -.3, 0, 0);
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
