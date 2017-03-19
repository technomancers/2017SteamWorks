package org.usfirst.frc.team1758.robot.commands;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.usfirst.frc.team1758.robot.Operator;
import org.usfirst.frc.team1758.utilities.Controller.Axes;

public class DriveWithJoystick extends CommandBase {
  private boolean finished;
  private boolean firstIt;
  private boolean firstTime;
  private double staticAngle;
  private Logger logger;
  private Mode mode;

  public enum Mode {
    RABBIT(0), HUMAN(1), TURTLE(2);
    private int order;
    private static Map<Integer, Mode> map = new HashMap<Integer, Mode>();
    static {
      for (Mode mode : Mode.values()) {
        map.put(mode.order, mode);
      }
    }
    
    private Mode(final int order) {
      this.order = order;
    }
    
    public static Mode valueOf(int order) {
      return map.get(order);
    }
  }

  public DriveWithJoystick() {
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Created DriveWithJoystick command");
    requires(driveTrain);
    requires(sensors);
    mode = Mode.RABBIT;
  }

  protected void initialize() {
    finished = false;
    firstIt = true;
    firstTime = true;
    staticAngle = 0.0;
  }

  protected void execute() {
    if (firstTime) {
      logger.debug("DriveWithJoystick command started");
      firstTime = false;
    }
    double gyroAngle;
    if (Operator.drivingController.buttonRb.get()) {
      if (firstIt) {
        sensors.resetGyroAngle();
      }
      gyroAngle = sensors.getGyroAngle();
      staticAngle = gyroAngle;
      firstIt = false;
    } else {
      gyroAngle = staticAngle;
      firstIt = true;
    }
    updateMode();
    double x = driveTrain.getSensitivityConfigs().directionX() * Operator.drivingController.getNormalizedAxis(Axes.LEFT_X);
    double y = driveTrain.getSensitivityConfigs().directionY() * Operator.drivingController.getNormalizedAxis(Axes.LEFT_Y);
    double twist = driveTrain.getSensitivityConfigs().twist() * Operator.drivingController.getNormalizedAxis(Axes.RIGHT_X);
    switch (mode) {
      case HUMAN:
        x *= driveTrain.getModesConfigs().human();
        y *= driveTrain.getModesConfigs().human();
        twist *= driveTrain.getModesConfigs().human();
        break;
      case TURTLE:
        x *= driveTrain.getModesConfigs().turtle();
        y *= driveTrain.getModesConfigs().turtle();
        twist *= driveTrain.getModesConfigs().turtle();
        break;
      default: //RABBIT
        x *= driveTrain.getModesConfigs().rabbit();
        y *= driveTrain.getModesConfigs().rabbit();
        twist *= driveTrain.getModesConfigs().rabbit();
        break;
    }
    driveTrain.mecanumDriveCartesian(x, y, twist, gyroAngle);
    if (Operator.drivingController.buttonLb.get()) {
      sensors.resetGyroAngle();
      driveTrain.resetEncoderPosition();
      staticAngle = 0.0;
    }
  }

  private void updateMode() {
    double control = Operator.drivingController.getNormalizedAxis(Axes.POV_X);
    if (Math.abs(control) < 0.5) {
      return;
    }
    int next = mode.order;
    if (control > 0.5) {
      next++;
    } else if (control < -0.5) {
      next--;
    }
    if (next < 0) {
      next = 0;
    }
    if (next > 2) {
      next = 2;
    }
    mode = Mode.valueOf(next);
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
