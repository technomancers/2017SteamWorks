package org.usfirst.frc.team1758.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class GetGyroAngle extends CommandBase
{
	private static boolean finished;
	public GetGyroAngle(){
		requires(sensors);
	}
	protected void initialize()
	{
		finished = false;
	}

	protected void execute()
	{
		SmartDashboard.putNumber("Gyro Angle", sensors.getGyroAngle());
		finished = true;
	}
	protected boolean isFinished()
	{
		return finished;
	}
}
