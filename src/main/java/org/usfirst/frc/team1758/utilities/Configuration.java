package org.usfirst.frc.team1758.utilities;

import edu.wpi.cscore.VideoMode.PixelFormat;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.compose.MergeConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.cfg4j.source.reload.ReloadStrategy;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;

public class Configuration {
  public RobotConfig robotConfig;
  public AutonomousConfig autonomousConfig;

  public Configuration() {
    BootstrapConfig bootConfig = getBootstrapConfig();
    robotConfig = getRobotConfig(bootConfig);
    autonomousConfig = getAutonomousConfig(bootConfig);
  }

  public interface BootstrapConfig {
    String environment();

    int reload();
  }

  public interface GripConfig {
    double[] hueThreshold();

    double[] saturationThreshold();

    double[] valueThreshold();

    boolean externalContours();

    double minArea();

    double minPerimeter();

    double minWidth();

    double maxWidth();

    double minHeight();

    double maxHeight();

    double[] solidity();

    double maxVertices();

    double minVertices();

    double minRatio();

    double maxRatio();
  }

  public interface ApproachConfig {
    double untilDistance();

    double doneDistance();

    double speed();

    double centerProportional();

    double centerThreshold();

    double doneIterations();
  }

  public interface OrientConfig {
    double centerProportional();

    double orientedProportional();

    double centerThreshold();

    double orientThreshold();
  }

  public interface BlindConfig {
    double speed();

    int moveBack();

    int moveBackCenter();

    double turnRight();

    double turnLeft();

    int finalBack();
  }

  public interface AutonomousConfig {

    ApproachConfig approach();

    OrientConfig orient();

    BlindConfig blind();
  }

  public interface Enabler {
    boolean enable();
  }

  public interface Porter {
    int port();
  }

  public interface MotorConfig extends Porter {
    boolean reverse();
  }

  public interface DriveTrainMotorsConfig {
    MotorConfig rightFront();

    MotorConfig rightBack();

    MotorConfig leftFront();

    MotorConfig leftBack();
  }

  public interface DriveTrainEncodersConfig {
    int encoderCodesPerRevolution();
  }

  public interface DriveTrainConfig extends Enabler {
    DriveTrainMotorsConfig motors();

    DriveTrainEncodersConfig encoders();
  }

  public interface VisionCameraConfig extends Porter {
    int width();

    int height();

    int fps();

    int exposure();

    int brightness();

    String format();
  }

  public interface VisionConfig extends Enabler {
    VisionCameraConfig camera();

    Porter server();

    Porter relay();

    GripConfig grip();
  }

  public interface SensorsUltrasonicConfig extends Porter {
    double suppliedVolts();
  }

  public interface SensorsConfig extends Enabler {
    SensorsUltrasonicConfig ultrasonic();
  }

  public interface RopeConfig extends Enabler {
    MotorConfig motor();
  }

  public interface GearSolenoidsConfig {
    int inPort();

    int outPort();
  }

  public interface GearConfig extends Enabler {
    GearSolenoidsConfig solenoid();
  }

  public interface PneumaticsConfig extends Enabler {
    Porter compressor();
  }

  public interface ControllerConfig extends Enabler, Porter {
    double threshold();
  }

  public interface ControllersConfig {
    ControllerConfig driving();

    ControllerConfig pit();
  }

  public interface RobotConfig {
    DriveTrainConfig driveTrain();

    VisionConfig vision();

    SensorsConfig sensors();

    RopeConfig rope();

    GearConfig gear();

    PneumaticsConfig pneumatics();

    ControllersConfig controllers();
  }

  public static PixelFormat stringToFormat(String format) {
    switch (format.toLowerCase()) {
      case "mjpeg":
        return PixelFormat.kMJPEG;
      case "bgr":
        return PixelFormat.kBGR;
      case "rgb565":
        return PixelFormat.kRGB565;
      case "yuyv":
        return PixelFormat.kYUYV;
      case "gray":
        return PixelFormat.kGray;
      default:
        return PixelFormat.kUnknown;
    }
  }

  private Environment getEnvironment(String environment) {
    if (environment == null || environment.isEmpty()) {
      return new ImmutableEnvironment("configs/");
    }
    return new ImmutableEnvironment("configs/" + environment + "/");
  }

  private BootstrapConfig getBootstrapConfig() {
    ConfigurationSource bootSource = new FilesConfigurationSource(
        () -> Collections.singletonList(Paths.get("bootstrap.yaml")));
    ConfigurationProvider bootProvider = new ConfigurationProviderBuilder().withConfigurationSource(bootSource)
        .withEnvironment(getEnvironment(null)).build();
    return bootProvider.bind("bootstrap", BootstrapConfig.class);
  }

  private RobotConfig getRobotConfig(BootstrapConfig bootConfig) {
    ConfigFilesProvider robotFileProvider = () -> Arrays.asList(Paths.get("robot.yaml"), Paths.get("../default.yaml"));
    ConfigurationSource robotSource = new FilesConfigurationSource(robotFileProvider);
    ConfigurationProvider robotProvider = new ConfigurationProviderBuilder().withConfigurationSource(robotSource)
        .withEnvironment(getEnvironment(bootConfig.environment())).build();
    return robotProvider.bind("robot", RobotConfig.class);
  }

  private AutonomousConfig getAutonomousConfig(BootstrapConfig bootConfig) {
    ConfigurationSource autoSource = new FilesConfigurationSource(
        () -> Collections.singletonList(Paths.get("autonomous.yaml")));
    ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(bootConfig.reload(), TimeUnit.SECONDS);
    ConfigurationProvider autoProvider = new ConfigurationProviderBuilder().withConfigurationSource(autoSource)
        .withEnvironment(getEnvironment(null)).withReloadStrategy(reloadStrategy).build();
    return autoProvider.bind("autonomous", AutonomousConfig.class);
  }
}
