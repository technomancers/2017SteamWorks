package org.usfirst.frc.team1758.robot.commands;

public class StartAutomaticCapture extends CommandBase{

	private boolean finished;

	public StartAutomaticCapture(){
		requires(vision);
	}
	protected void initialize(){
		finished=false;
	}

	protected void execute(){
		vision.startAutomaticCapture();
		finished = true;
	}

	protected boolean isFinished(){
		return finished;
	}
}
