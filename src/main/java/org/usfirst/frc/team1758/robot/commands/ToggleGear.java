package org.usfirst.frc.team1758.robot.commands;

public class ToggleGear extends CommandBase {
	private boolean finished;
	private boolean engaged;
	public ToggleGear() {
		requires(gear);
		engaged = false;
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		if(engaged){
			gear.pullPiston();
			engaged = false;
		}else{
			gear.pushPiston();
			engaged = true;
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
