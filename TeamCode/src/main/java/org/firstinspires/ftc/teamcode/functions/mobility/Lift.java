package org.firstinspires.ftc.teamcode.functions.mobility;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class Lift implements Standart {
    Robot1825 robot;
    public Lift(Robot1825 robot1825){ this.robot = robot1825;}
    private DcMotorEx lift1 = null;
    private DcMotorEx lift2 = null;
    public int liftTargetLimit = 0;
    public int oldLiftTargetLimit = 0;
    public static double liftSpeed = 0.5;

    public enum LiftTarget { UP, MIDDLE, DOWN; }

    public void start() {
        lift1 = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "liftLeft");
        lift2 = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "liftRight");
        lift1.setDirection(DcMotorSimple.Direction.FORWARD);
        lift2.setDirection(lift1.getDirection().inverted());
    }

    public void activity(LiftTarget target){
        setLiftTargetLimit(target);
        if(liftTargetLimit > oldLiftTargetLimit){ lift1.setDirection(DcMotorSimple.Direction.FORWARD); }
        else{ lift1.setDirection(DcMotorSimple.Direction.REVERSE); }
        lift2.setDirection(lift1.getDirection().inverted());
        while(!robot.limitSwitch.getLimit(liftTargetLimit)){ lift1.setVelocity(liftSpeed); lift2.setVelocity(liftSpeed); }
        oldLiftTargetLimit = liftTargetLimit;
    }

    public boolean finish(){ return oldLiftTargetLimit == liftTargetLimit; }

    private void setLiftTargetLimit(LiftTarget localTarget){
        if(localTarget == localTarget.UP){ liftTargetLimit = 1; }
        else if(localTarget == localTarget.MIDDLE){ liftTargetLimit = 2; }
        else if(localTarget == localTarget.DOWN){ liftTargetLimit = 3; }
    }
}
