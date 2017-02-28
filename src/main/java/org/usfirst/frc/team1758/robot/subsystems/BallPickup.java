package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallPickup extends Subsystem {
	private DoubleSolenoid ballSolenoid;
	private CANTalon motor;

	public BallPickup() {
		ballSolenoid = new DoubleSolenoid(RobotMap.BALL_SOLENOID_IN, RobotMap.BALL_SOLENOID_OUT);
		motor = new CANTalon(RobotMap.BALL_PICKUP_MOTOR_PORT);
	}

	protected void initDefaultCommand() {
	}

	public void pushPiston() {
		ballSolenoid.set(Value.kForward);
	}

	public void pullPiston() {
		ballSolenoid.set(Value.kReverse);
	}

	public void setPickupSpeed(double power) {
		motor.set(power);
	}
}
