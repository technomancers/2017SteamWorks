package org.usfirst.frc.team1758.utilities;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.cfg4j.source.reload.ReloadStrategy;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;

public class Configuration {
	public ConfigurationProvider robotConfig;
	public AutonomousConfig autonomousConfig;

	public Configuration() {
		BootstrapConfig bootConfig = GetBootstrapConfig();
		robotConfig = GetRobotConfig(bootConfig);
		autonomousConfig = GetAutonomousConfig(bootConfig);
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

		double minvertices();

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

		double moveBack();

		double turnRight();

		double turnLeft();

		double finalBack();
	}

	public interface AutonomousConfig {
		GripConfig grip();

		ApproachConfig approach();

		OrientConfig orient();

		BlindConfig blind();
	}

	private Environment GetEnvironment(String environment) {
		if (environment == null || environment.isEmpty()) {
			return new ImmutableEnvironment("configs/");
		}
		return new ImmutableEnvironment("configs/" + environment + "/");
	}

	private BootstrapConfig GetBootstrapConfig() {
		ConfigurationSource bootSource = new FilesConfigurationSource(
				() -> Collections.singletonList(Paths.get("bootstrap.yaml")));
		ConfigurationProvider bootProvider = new ConfigurationProviderBuilder().withConfigurationSource(bootSource)
				.withEnvironment(GetEnvironment(null)).build();
		return bootProvider.bind("bootstrap", BootstrapConfig.class);
	}

	private ConfigurationProvider GetRobotConfig(BootstrapConfig bootConfig) {
		ConfigurationSource robotSource = new FilesConfigurationSource(
				() -> Collections.singletonList(Paths.get("robot.yaml")));
		ConfigurationProvider robotProvider = new ConfigurationProviderBuilder().withConfigurationSource(robotSource)
				.withEnvironment(GetEnvironment(bootConfig.environment())).build();
		return robotProvider;
	}

	private AutonomousConfig GetAutonomousConfig(BootstrapConfig bootConfig) {
		ConfigurationSource autoSource = new FilesConfigurationSource(
				() -> Collections.singletonList(Paths.get("autonomous.yaml")));
		ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(bootConfig.reload(), TimeUnit.SECONDS);
		ConfigurationProvider autoProvider = new ConfigurationProviderBuilder().withConfigurationSource(autoSource)
				.withEnvironment(GetEnvironment(null)).withReloadStrategy(reloadStrategy).build();
		return autoProvider.bind("autonomous", AutonomousConfig.class);
	}
}
