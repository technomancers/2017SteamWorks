package org.usfirst.frc.team1758.utilities;

import org.usfirst.frc.team1758.robot.commands.StartCamera;
import org.usfirst.frc.team1758.robot.subsystems.Vision.CameraMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controller
{
	private Joystick joystick;
	public JoystickButton a, b, x, y, lb, rb;
	public enum Axes {
		LEFT_X(1), RIGHT_X(4), LEFT_Y(2), RIGHT_Y(5), TRIGGER(3);
		public int port;
		Axes(int port)
		{
			this.port = port;
		}
		public int getPort()
		{
			return port;
		}
	}
	public Controller(int port)
	{
		joystick = new Joystick(port);
		a = new JoystickButton(joystick, 1);
		b = new JoystickButton(joystick, 2);
		x = new JoystickButton(joystick, 3);
		y = new JoystickButton(joystick, 4);
		lb = new JoystickButton(joystick, 5);
		rb = new JoystickButton(joystick, 6);
		a.whenPressed(new StartCamera());
		b.whenPressed(new StartCamera(CameraMode.BACK));
	}
	public double getRawAxis(Axes axes){
		return joystick.getRawAxis(axes.getPort());
	}
	public double getNormalizedAxis(Axes axes)
	{
		return getRawAxis(axes) * (-1.0);
	}
}
