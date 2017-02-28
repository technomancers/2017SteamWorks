package org.usfirst.frc.team1758.robot.commands;

import edu.wpi.first.wpilibj.Timer;



public class WaitOneSec extends CommandBase {
	private boolean finished;
	private Timer mTimer;

	public WaitOneSec() {
	}

	protected void initialize() {
		finished = false;
		mTimer = new Timer();
		mTimer.start();
	}

	protected void execute() {
		if(mTimer.hasPeriodPassed(.5))
		{
			finished = true;
		}
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
