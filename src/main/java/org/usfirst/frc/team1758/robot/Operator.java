package org.usfirst.frc.team1758.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.usfirst.frc.team1758.utilities.Controller;

public class Operator {
  public static Controller drivingController;
  public static Controller pitController;

  public static void init() {
    Logger logger = LoggerFactory.getLogger(Operator.class);
    logger.debug("Creating Controllers");
    drivingController = new Controller(0, 0.1);
    pitController = new Controller(1);
  }
}
