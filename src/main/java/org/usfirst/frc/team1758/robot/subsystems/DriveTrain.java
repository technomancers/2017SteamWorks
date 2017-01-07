package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
}
