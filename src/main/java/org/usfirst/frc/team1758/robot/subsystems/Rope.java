package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.utilities.Configuration.RopeConfig;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Rope extends Subsystem {
	private CANTalon motor;

	public Rope(RopeConfig configs) {
		motor = new CANTalon(configs.motor().port());
		motor.setInverted(configs.motor().reverse());
	}

	protected void initDefaultCommand() {
	}

	public void setMotor(double speed) {
		motor.set(speed);
	}
}
