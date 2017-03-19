package org.usfirst.frc.team1758.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.utilities.Configuration.ControllerConfig;

public class Controller {
  private Joystick joystick;
  private Logger logger;
  private ControllerConfig config;
  public JoystickButton buttonA;
  public JoystickButton buttonB;
  public JoystickButton buttonX;
  public JoystickButton buttonY;
  public JoystickButton buttonLb;
  public JoystickButton buttonRb;

  public enum Axes {
    LEFT_X(0), RIGHT_X(4), LEFT_Y(1), RIGHT_Y(5), TRIGGER_LEFT(2), TRIGGER_RIGHT(3);
    public int port;

    Axes(int port) {
      this.port = port;
    }

    public int getPort() {
      return port;
    }
  }

  public Controller(ControllerConfig config) {
    this.config = config;
    logger = LoggerFactory.getLogger(this.getClass());
    logger.info("Controller created");
    joystick = new Joystick(config.port());
    buttonA = new JoystickButton(joystick, 1);
    buttonB = new JoystickButton(joystick, 2);
    buttonX = new JoystickButton(joystick, 3);
    buttonY = new JoystickButton(joystick, 4);
    buttonLb = new JoystickButton(joystick, 5);
    buttonRb = new JoystickButton(joystick, 6);
  }

  public double getRawAxis(Axes axes) {
    return joystick.getRawAxis(axes.getPort());
  }

  public double getNormalizedAxis(Axes axes) {
    if (Math.abs(joystick.getRawAxis(axes.getPort())) < config.threshold()) {
      return 0;
    } else {
      return joystick.getRawAxis(axes.getPort());
    }
  }

  public double getTwist() {
    return joystick.getTwist();
  }
}
