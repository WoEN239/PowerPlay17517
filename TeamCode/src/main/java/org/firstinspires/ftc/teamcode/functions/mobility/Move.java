package org.firstinspires.ftc.teamcode.functions.mobility;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class Move implements Standart {
    public Robot1825 robot;
    public Move(Robot1825 robot){this.robot = robot;}

    private DcMotorEx leftFront = null;
    private DcMotorEx leftBack = null;
    private DcMotorEx rightFront = null;
    private DcMotorEx rightBack = null;
    public double[] targets = new double[1];

    public void start(){
        setConfig(robot);
        setDirections();
        robot.lift.activity(Lift.LiftTarget.UP);
    };

    public void activity(){
        if(robot.driver_auto) { targets[0] = robot.control.getTargetX(); targets[1] = robot.control.getTargetY(); }
        else { targets[0] = 1; targets[1] = 1; }
        robot.pid.calculation(targets);
        motorUsing(robot.pid.getVectorOX(), robot.pid.getVectorOY());
    }

    public boolean finish(){ return true; }

    private void setDirections() {
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        leftBack.setDirection(DcMotorEx.Direction.REVERSE);
        rightFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightBack.setDirection(DcMotorEx.Direction.REVERSE);
    }

    private void setConfig(Robot1825 robot) {
        leftFront = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "L1");
        leftBack = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "L2");
        rightFront = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "R1");
        rightBack = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "R2");
    }

    private void motorUsing(double power1, double power2){

    }
}
