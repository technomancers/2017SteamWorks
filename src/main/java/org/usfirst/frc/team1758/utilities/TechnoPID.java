package org.usfirst.frc.team1758.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TechnoPID{
    private double kP, kI, kD, tolerance;
    
    private double ref;
    private double prevVal, prevDif;
    private double errorSum;

    private boolean firstCycle;
    private int cycleCount, minCycleCount;

    private boolean isContinuous;
    private double midPoint, minInput, maxInput;

		private Logger logger;

    public TechnoPID(){
        this(0.0, 0.0, 0.0, 0.0);
    }

    public TechnoPID(double p, double i, double d){
        this(p, i, d, 0.0);
    }
    public TechnoPID(double p, double i, double d, double tolerance){
				logger = LoggerFactory.getLogger(this.getClass());
				logger.debug("Creating PID with P:{} I:{} D:{} and tolerance {}", p, i, d, tolerance);
        this.kP = p;
        this.kI = i;
        this.kD = d;
        this.tolerance = tolerance;

        this.ref = 0.0;
        this.firstCycle = true;

        this.cycleCount = 0;
        this.minCycleCount = 3;

        this.isContinuous = false;
        this.midPoint = 0.0;
        this.minInput = 0.0;
        this.maxInput = 0.0;
    }
    public void setTolerance(double t){
				logger.debug("Setting Tolerance to {}", t);
        this.tolerance = t;
    }
    public void setReference(double ref){
				logger.debug("Setting Reference to {}", ref);
        this.ref = ref;
    }
    public void setMinCycleCount(int count){
				logger.debug("Setting Cycle Count to {}", count);
        this.minCycleCount = count;
    }
    public void setContinuous(double min, double max){
				logger.debug("Setting Continuous Min:{} Max:{}", min, max);
        midPoint = (max - min)/2;

        maxInput = max;
        minInput = min;

        isContinuous = true;
    }
    public void resetContinuous(){
				logger.debug("Reset Continuous");
        isContinuous = false;
        midPoint = 0.0;
        maxInput = 0.0;
        minInput = 0.0;
    }
    public void resetErrorSum(){
				logger.debug("Reset Error Sum");
        this.errorSum = 0.0;
    }
    public double getRef(){
        return this.ref;
    }
    public double calculatePID(double currentVal){
        double pErr = 0.0;
        double iErr = 0.0;
        double dErr = 0.0;

        if(this.firstCycle){
            this.prevVal = currentVal;
            this.firstCycle = false;
        }

        double error = this.ref - currentVal;

        if(isContinuous){
            if(Math.abs(error) > midPoint){
                if(error > 0){
                    error = error - maxInput + minInput;
                }
                else{
                    error = error + maxInput - minInput;
                }
            }
        }

        pErr = this.kP * error;

        this.errorSum += error;
        iErr = this.kI * this.errorSum;

        double delta = currentVal - this.prevVal;
        dErr = this.kD * delta;

        double output = pErr + iErr + dErr;

        this.prevVal = currentVal;
				logger.debug("Output is {}", output);
        return output;
    }
    public double calculatePIDIncrement(double currentVal){
        double pErr = 0.0;
        double iErr = 0.0;
        double dErr = 0.0;

        if(this.firstCycle){
            this.prevVal = currentVal;
            this.firstCycle = false;
        }
        
        double delta = currentVal - this.prevVal;
        pErr = this.kP * delta;

        double error = this.ref - currentVal;
        
        if(isContinuous){
            if(Math.abs(error) > midPoint){
                if(error > 0){
                    error = error - maxInput + minInput;
                }
                else{
                    error = error + maxInput - minInput;
                }
            }
        }

        iErr = this.kI * error;

        double dDelta = delta - this.prevDif;
        dErr = this.kD * dDelta;

        double output = pErr + iErr + dErr;

        this.prevVal = currentVal;
				logger.debug("Incremental Output is {}", output);
        return output;
    }

    public boolean isDone(){
        double currentError = Math.abs(this.ref - this.prevVal);
        if(currentError <= this.tolerance){
            this.cycleCount++;
        }
        else{
            this.cycleCount = 0;
        }
				logger.debug("Cycle Count {} : Min Cycle Count {}", this.cycleCount, this.minCycleCount);
        return this.cycleCount > this.minCycleCount;
    }
    public void resetPrevious(){
				logger.debug("Reset Previous");
        this.prevDif = 0.0;
        this.prevVal = 0.0;
    }
}
