package org.usfirst.frc.team1758.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team1758.robot.vision.PegPipeline;
import org.usfirst.frc.team1758.utilities.Configuration;
import org.usfirst.frc.team1758.utilities.Configuration.VisionConfig;

public class Vision extends Subsystem {
  private Logger logger;
  private Relay lightRelay;
  private UsbCamera gearCamera;
  private CvSink gearSink;
  private CvSource outputStream;
  private MjpegServer cameraServer;
  private Thread visionThread;
  private int numRectangles;
  private double centerX;
  private PegPipeline pegPipeline;
  private Rect leftMost;
  private Rect rightMost;
  private Rect bigRect;
  private long currTime;
  private long prevTime;
  private VisionConfig configs;

  public Vision(VisionConfig configs) {
    this.configs = configs;
    logger = LoggerFactory.getLogger(this.getClass());
    logger.debug("Vision subsystem created");
    lightRelay = new Relay(configs.relay().port());
    pegPipeline = new PegPipeline(configs.grip());
    initCamera();
  }

  public void initDefaultCommand() {
  }

  public void turnOnLights() {
    logger.debug("Turning on light");
    lightRelay.set(Value.kForward);
  }

  public void turnOffLights() {
    logger.debug("Turning off light");
    lightRelay.set(Value.kReverse);
  }

  public void initCamera() {
    logger.debug("Initializing Camera");
    if (gearCamera == null) {
      logger.debug("Creating a new gear camera");
      gearCamera = new UsbCamera("gearCamera", configs.camera().port());
      gearCamera.setExposureManual(configs.camera().exposure());
      gearCamera.setBrightness(configs.camera().brightness());
      gearCamera.setVideoMode(Configuration.stringToFormat(configs.camera().format()), configs.camera().width(),
          configs.camera().height(), configs.camera().fps());
    }

    if (gearSink == null) {
      logger.debug("Creating a new gear sink");
      gearSink = new CvSink("gearSink");
      gearSink.setEnabled(true);
      gearSink.setSource(gearCamera);
    }

    if (outputStream == null) {
      logger.debug("Creating a new output stream");
      outputStream = new CvSource("Cameras", Configuration.stringToFormat(configs.camera().format()),
          configs.camera().width(), configs.camera().height(), configs.camera().fps());
    }

    //May need to create a server for each camera
    if (cameraServer == null) {
      logger.debug("Creating a new camera server");
      cameraServer = new MjpegServer("serveCameras", configs.server().port());
      cameraServer.setSource(outputStream);
    }
  }

  public void startVisionThread() {
    if (visionThread == null) {
      logger.debug("Creating a new vision thread");
      visionThread = new Thread(() -> {
        Mat image = new Mat(configs.camera().height(), configs.camera().width(), 6);
        while (!Thread.interrupted()) {
          logger.trace("Grabbing frame from gear sink");
          currTime = gearSink.grabFrameNoTimeout(image);
          if (currTime == 0 || currTime == prevTime) {
            logger.warn("No Frame to grab");
            continue;
          }
          prevTime = currTime;
          pegPipeline.process(image);
          leftMost = new Rect(configs.camera().width(), configs.camera().height(), 0, 0);
          rightMost = new Rect(0, 0, 0, 0);
          bigRect = new Rect(0, 0, 0, 0);
          ArrayList<MatOfPoint> mops = pegPipeline.filterContoursOutput();
          for (MatOfPoint mop : mops) {
            Rect r = Imgproc.boundingRect(mop);
            Imgproc.rectangle(image, r.tl(), r.br(), new Scalar(255, 0, 255));
            if (leftMost.tl().x > r.tl().x) {
              leftMost = r;
            }
            if (rightMost.br().x < r.br().x) {
              rightMost = r;
            }
            if (bigRect.width < r.width) {
              bigRect = r;
            }
          }
          numRectangles = mops.size();
          centerX = (leftMost.tl().x + rightMost.br().x) / 2.0;
          Imgproc.line(image, new Point(centerX, 0), new Point(centerX, configs.camera().height()),
              new Scalar(255, 0, 0));
          outputStream.putFrame(image);
        }
      });
    }
    logger.debug("Starting Vision Thread");
    visionThread.start();
  }

  public double getCenterX() {
    return centerX;
  }

  public double getNumRectangles() {
    return numRectangles;
  }

  public Rect getLeftMost() {
    return leftMost;
  }

  public Rect getRightMost() {
    return rightMost;
  }

  public Rect getBigRect() {
    return bigRect;
  }

  public long getCurrTime() {
    return currTime;
  }

  public void stopVisionThread() {
    logger.debug("Stopping vision thread");
    if (visionThread != null && visionThread.getState() == Thread.State.RUNNABLE) {
      visionThread.interrupt();
    } else {
      logger.warn("Vision thread is not created or not runnable to stop");
    }
  }
}
