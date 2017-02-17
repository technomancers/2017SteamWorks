package org.usfirst.frc.team1758.utilities;

public class TechnoPID{
    private double kP, kI, kD, tolerance;
    
    private double ref;
    private double prevVal, prevDif;
    private double errorSum;

    private boolean firstCycle;
    private int cycleCount, minCycleCount;

    private boolean isContinuous;
    private double midPoint, minInput, maxInput;

    public TechnoPID(){
        this(0.0, 0.0, 0.0, 0.0);
    }

    public TechnoPID(double p, double i, double d){
        this(p, i, d, 0.0);
    }
    public TechnoPID(double p, double i, double d, double tolerance){
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
        this.tolerance = t;
    }
    public void setReference(double ref){
        this.ref = ref;
    }
    public void setMinCycleCount(int count){
        this.minCycleCount = count;
    }
    public void setContinuous(double min, double max){
        midPoint = (max - min)/2;

        maxInput = max;
        minInput = min;

        isContinuous = true;
    }
    public void resetContinuous(){
        isContinuous = false;
        midPoint = 0.0;
        maxInput = 0.0;
        minInput = 0.0;
    }
    public void resetErrorSum(){
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

        return output;
    }

    public boolean isDone(){
        double currentError = Math.abs(this.ref - this.prevVal);
			//System.out.println(currentError);
        if(currentError <= this.tolerance){
            this.cycleCount++;
        }
        else{
            this.cycleCount = 0;
        }
        System.out.println(cycleCount);
        return this.cycleCount > this.minCycleCount;
    }
    public void resetPrevious(){
        this.prevDif = 0.0;
        this.prevVal = 0.0;
    }
}
