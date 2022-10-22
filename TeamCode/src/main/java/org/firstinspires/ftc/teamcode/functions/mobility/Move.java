package org.firstinspires.ftc.teamcode.functions.mobility;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standard;

public class Move implements Standard {
    public Robot1825 robot;
    public Move(Robot1825 robot){this.robot = robot;}

    private DcMotorEx leftFront = null;
    private DcMotorEx leftBack = null;
    private DcMotorEx rightFront = null;
    private DcMotorEx rightBack = null;
    public double[] targets = new double[2];

    public void start(){
        setConfig(robot);
        setDirections();
        robot.lift.activity(Lift.LiftTarget.UP);
    };

    public void activity(){
        if(robot.ControlMode == robot.ControlMode.DRIVING) {
            targets[0] = robot.control.getTargetX(); targets[1] = robot.control.getTargetY();
            targets[2] = robot.control.getDrivingAngle();
        }
        if (robot.ControlMode == robot.ControlMode.AUTONOMOUS)finalMovement(robot.pReg.calculation(targets[0], robot.position.globalX), robot.pReg.calculation(targets[1], robot.position.globalY), targets[2]);
        else{ finalMovement(robot.control.getTargetY(), robot.control.getTargetX(), robot.control.getDrivingAngle()); }
    }

    public boolean finish(){ return (Math.abs(targets[0]-robot.position.globalX)<3)&&(Math.abs(targets[1]-robot.position.globalY)<3)&&(Math.abs(targets[0]-robot.position.globalX)<3);}

    private void setDirections() {
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(leftFront.getDirection().inverted());
        rightBack.setDirection(leftBack.getDirection().inverted());
    }

    private void setConfig(Robot1825 robot) {
        leftFront = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "L1");
        leftBack = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "L2");
        rightFront = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "R1");
        rightBack = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "R2");
    }

    public void setAutoTargets(double x, double y, double angle){
        targets[0] = x; targets[1] = y; targets[2] = angle;
    }

    private void finalMovement(double front, double side, double turn){
        leftFront.setPower(front + side + turn);
        leftBack.setPower(front - side + turn);
        rightFront.setPower(front - side - turn);
        rightBack.setPower(front + side - turn);
    }

}
