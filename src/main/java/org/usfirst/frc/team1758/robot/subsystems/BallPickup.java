package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallPickup extends Subsystem
{
	private DoubleSolenoid ballSolenoid;
	public BallPickup()
	{
		ballSolenoid = new DoubleSolenoid(RobotMap.BALL_SOLENOID_IN, RobotMap.BALL_SOLENOID_OUT);
	}
	protected void initDefaultCommand()
	{
	}
	public void pushPiston()
	{
		ballSolenoid.set(Value.kForward);
	}
	public void pullPiston()
	{
		ballSolenoid.set(Value.kReverse);
	}
	
}
