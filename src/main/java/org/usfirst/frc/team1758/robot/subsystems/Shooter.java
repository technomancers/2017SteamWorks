package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	private CANTalon motor;

	public Shooter() {
		motor = new CANTalon(RobotMap.SHOOTER_MOTOR_PORT);
	}

	protected void initDefaultCommand() {
	}

	public void setMotor(double speed) {
		motor.set(speed);
	}
}
