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

    public void start(){
        setConfig(robot);
        setDirections();
        robot.lift.activity(Lift.LiftTarget.UP);
    };

    public void activity(){}

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

    private double distance(){
        return 1.0;
    }
}
