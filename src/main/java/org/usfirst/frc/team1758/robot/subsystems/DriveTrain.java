package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private Talon rf_Motor, rm_Motor, rb_Motor, lf_Motor, lm_Motor, lb_Motor;
	private RobotDrive tmDrive;

	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public DriveTrain() {
		rf_Motor = new Talon(RobotMap.RIGHT_FRONT_MOTOR);
		rb_Motor = new Talon(RobotMap.RIGHT_BACK_MOTOR);
		lf_Motor = new Talon(RobotMap.LEFT_FRONT_MOTOR);
		tmDrive = new RobotDrive(RobotMap.LEFT_FRONT_MOTOR, RobotMap.LEFT_BACK_MOTOR, RobotMap.RIGHT_FRONT_MOTOR, RobotMap.RIGHT_BACK_MOTOR);
		lb_Motor = new Talon(RobotMap.LEFT_BACK_MOTOR);
	}

	public void setLeftPower(double power) {
		//lf_Motor.set(power);
		lm_Motor.set(power);
		//lb_Motor.set(power);
	}

	public void setRightPower(double power) {
		//rf_Motor.set(power);
		rm_Motor.set(power);
		//rb_Motor.set(power);
	}

	public void setPower(double power) {
		setPower(power, power);
	}

	public void setPower(double l_power, double r_power) {
		setLeftPower(l_power);
		setRightPower(r_power);
	}

	public void mecanumDrive(double x, double y, double rotation, double gyroAngle) {
		tmDrive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
	}

	public void tankDrive(double left, double right) {
		tmDrive.tankDrive(left, right);
	}
}
