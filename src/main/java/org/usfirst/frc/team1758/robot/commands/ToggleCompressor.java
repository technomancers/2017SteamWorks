package org.usfirst.frc.team1758.robot.commands;

public class ToggleCompressor extends CommandBase {
	private boolean finished;
	private boolean engaged;
	public ToggleCompressor() {
		requires(compressing);
		engaged = false;
	}

	protected void initialize() {
		finished = false;
	}

	protected void execute() {
		if(engaged){
			compressing.turnOffCompressor();
			engaged = false;
		}else{
			compressing.turnOnCompressor();
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
