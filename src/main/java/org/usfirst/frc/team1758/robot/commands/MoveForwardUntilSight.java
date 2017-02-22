package org.usfirst.frc.team1758.robot.commands;

import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;



public class MoveForwardUntilSight extends CommandBase {
	private boolean finished;

	public MoveForwardUntilSight() {
		requires(driveTrain);
	}

	protected void initialize() {
		finished = false;
		driveTrain.resetEncoderPosition();
	}

	protected void execute() {
		if(driveTrain.getEncoderPosition(Motor.FrontRight) < 18046){
			driveTrain.mecanumDriveCartesian(0, -0.3, 0, 0);
		}
		else{
			driveTrain.mecanumDriveCartesian(0, 0, 0, 0);
			finished = true;
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
