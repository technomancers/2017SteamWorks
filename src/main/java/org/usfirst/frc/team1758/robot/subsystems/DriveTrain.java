package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private CANTalon rf_Motor, rb_Motor, lf_Motor, lb_Motor;
	private RobotDrive tmDrive;

	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public DriveTrain() {
		rf_Motor = new CANTalon(RobotMap.RIGHT_FRONT_MOTOR);
		rb_Motor = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
		lf_Motor = new CANTalon(RobotMap.LEFT_FRONT_MOTOR);
		lb_Motor = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
		tmDrive = new RobotDrive(lf_Motor, lb_Motor, rf_Motor, rb_Motor);
	}

	public void mecanumDriveCartesian(double x, double y, double rotation, double gyroAngle) {
		tmDrive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
	}
	public void mecanumDrivePolar(double magnitude, double direction, double rotation){
		tmDrive.mecanumDrive_Polar(magnitude, direction, rotation);
	}

	public void tankDrive(double left, double right) {
		tmDrive.tankDrive(left, right);
	}
}
