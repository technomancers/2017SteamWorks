package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.subsystems.DriveTrain.Motor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallPickup extends Subsystem {
	private DoubleSolenoid ballSolenoid;
	private boolean engaged;
	//private CANTalon motor;

	public BallPickup() {
		ballSolenoid = new DoubleSolenoid(RobotMap.BALL_SOLENOID_IN, RobotMap.BALL_SOLENOID_OUT);
		engaged = false;
		//motor = new CANTalon(RobotMap.BALL_PICKUP_MOTOR_PORT);
	}

	protected void initDefaultCommand() {
	}

	public void engage() {
		ballSolenoid.set(Value.kReverse);
		//motor.set(.7);
		engaged = true;
	}

	public void disengage() {
		//motor.set(0);
		engaged = false;
	}

	public void reset() {
		ballSolenoid.set(Value.kForward);
	}

	public boolean isEngaged() {
		return engaged;
	}
}
