package org.usfirst.frc.team1758.robot.subsystems;

import org.usfirst.frc.team1758.robot.RobotMap;
import org.usfirst.frc.team1758.robot.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private Talon rf_Motor, rm_Motor, rb_Motor, lf_Motor, lm_Motor, lb_Motor;
	protected void initDefaultCommand() {
	//	setDefaultCommand(new DriveWithJoystick());
	}
	public DriveTrain(){
		//rf_Motor = new Talon(RobotMap.RIGHT_FRONT_MOTOR);
		rm_Motor = new Talon(RobotMap.RIGHT_MIDDLE_MOTOR);
		//rb_Motor = new Talon(RobotMap.RIGHT_BACK_MOTOR);
		//lf_Motor = new Talon(RobotMap.LEFT_FRONT_MOTOR);
		lm_Motor = new Talon(RobotMap.LEFT_MIDDLE_MOTOR);
		//lb_Motor = new Talon(RobotMap.LEFT_BACK_MOTOR);
	}
	public void setLeftPower(double power){
		//lf_Motor.set(power);
		lm_Motor.set(power);
		//lb_Motor.set(power);
	}
	public void setRightPower(double power){
		//rf_Motor.set(power);
		rm_Motor.set(power);
		//rb_Motor.set(power);
	}
	public void setPower(double power){
		setPower(power, power);
	}
	public void setPower(double l_power, double r_power){
		setLeftPower(l_power);
		setRightPower(r_power);
	}
}
