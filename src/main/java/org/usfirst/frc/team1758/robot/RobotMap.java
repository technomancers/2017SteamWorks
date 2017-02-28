package org.usfirst.frc.team1758.robot;

import edu.wpi.cscore.VideoMode.PixelFormat;

public class RobotMap {
	//DriveTrain
	public final static int RIGHT_FRONT_MOTOR = 1;
	public final static int RIGHT_BACK_MOTOR = 0;
	public final static int LEFT_FRONT_MOTOR = 4;
	public final static int LEFT_BACK_MOTOR = 3;
	public final static int ENCODER_CODES_PER_REVOLUTION = 1024;

	//Vison
	public final static int GEAR_CAMERA_PORT = 0;
	public final static int CAMERA_LIGHT_RELAY = 0;
	public final static int CAMERA_WIDTH = 320;
	public final static int CAMERA_HEIGHT = 240;
	public final static int MJPEG_SERVER_PORT = 1182;
	public final static int CAMERA_FPS = 30;
	public final static int CAMERA_EXPOSURE = 0;
	public final static PixelFormat CAMERA_PIXEL_FORMAT = PixelFormat.kMJPEG;
	public final static int CAMERA_BRIGHTNESS = 50;

	//Servos
	public final static int SERVO_PORT = 0;

	//Sensors
	public final static int ANALOG_SONIC_PORT = 0;
	public final static int PROXIMITY_PORT = 1;
	public final static double OUT_VOLTS = 4.85;

	//Pneumatics
	public final static int COMPRESSOR_NODE_ID = 0;
	public final static int GEAR_SOLENOID_IN = 0;
	public final static int GEAR_SOLENOID_OUT = 1;
	public final static int BALL_SOLENOID_IN = 2;
	public final static int BALL_SOLENOID_OUT = 3;

	//BallPickUp
	public final static int BALL_PICKUP_MOTOR_PORT = 5;

	//Shooter
	public final static int SHOOTER_MOTOR_PORT = 6;

	//Rope
	public final static int ROPE_MOTOR_PORT = 7;
}
