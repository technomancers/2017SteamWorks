package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gear extends Subsystem
{
	private DoubleSolenoid gearSolenoid;
	public Gear()
	{
		gearSolenoid = new DoubleSolenoid(RobotMap.GEAR_SOLENOID_IN, RobotMap.GEAR_SOLENOID_OUT);
	}
	protected void initDefaultCommand()
	{
	}
	public void pushPiston()
	{
		gearSolenoid.set(Value.kForward);
	}
	public void pullPiston()
	{
		gearSolenoid.set(Value.kReverse);
	}
	
}
