package org.usfirst.frc.team1758.robot.commands;

public class ToggleLight extends CommandBase {
	private static boolean finished;
	private static LightMode lightMode;
	public enum LightMode{
		ON, OFF, TOGGLE;
	}
	public ToggleLight() {
		this(LightMode.TOGGLE);
	}
	public ToggleLight(LightMode mode){
		requires(vision);
		lightMode = mode;
	}
	protected void initialize() {
		finished = false;
	}
	protected void execute() {
		switch (lightMode) {
			case ON:
				vision.turnOnLight();
				break;
			case OFF:
				vision.turnOffLight();	
			default:
				vision.toggleLight();
				break;
		}
		finished = true;
	}
	protected boolean isFinished() {
		return finished;
	}
	protected void end() {
	}
	protected void interrupted() {
	}
}
