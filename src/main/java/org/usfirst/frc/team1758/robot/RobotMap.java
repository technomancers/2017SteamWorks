package org.usfirst.frc.team1758.robot;

import edu.wpi.cscore.VideoMode.PixelFormat;

public class RobotMap {
  //DriveTrain
  public static final int RIGHT_FRONT_MOTOR = 3;
  public static final int RIGHT_BACK_MOTOR = 4;
  public static final int LEFT_FRONT_MOTOR = 1;
  public static final int LEFT_BACK_MOTOR = 2;
  public static final int ENCODER_CODES_PER_REVOLUTION = 1024;

  //Vison
  public static final int GEAR_CAMERA_PORT = 0;
  public static final int CAMERA_LIGHT_RELAY = 0;
  public static final int CAMERA_WIDTH = 320;
  public static final int CAMERA_HEIGHT = 240;
  public static final int MJPEG_SERVER_PORT = 1182;
  public static final int CAMERA_FPS = 30;
  public static final int CAMERA_EXPOSURE = 0;
  public static final PixelFormat CAMERA_PIXEL_FORMAT = PixelFormat.kMJPEG;
  public static final int CAMERA_BRIGHTNESS = 50;

  //Servos
  public static final int SERVO_PORT = 0;

  //Sensors
  public static final int ANALOG_SONIC_PORT = 3;
  public static final int PROXIMITY_PORT = 1;
  public static final double OUT_VOLTS = 4.85;

  //Pneumatics
  public static final int COMPRESSOR_NODE_ID = 0;
  public static final int GEAR_SOLENOID_IN = 1;
  public static final int GEAR_SOLENOID_OUT = 0;
  public static final int BALL_SOLENOID_IN = 2;
  public static final int BALL_SOLENOID_OUT = 3;

  //BallPickUp
  public static final int BALL_PICKUP_MOTOR_PORT = 0;

  //Shooter
  public static final int SHOOTER_MOTOR_PORT = 5;

  //Rope
  public static final int ROPE_MOTOR_PORT = 0;
}
