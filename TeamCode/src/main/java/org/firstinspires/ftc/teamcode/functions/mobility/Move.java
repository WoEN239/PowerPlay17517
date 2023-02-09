package org.firstinspires.ftc.teamcode.functions.mobility;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.functions.OtherFunctions.PID;
import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class Move implements Standart {
    public Robot1825 robot;
    public Move(Robot1825 robot){this.robot = robot;}
    public PID moveReg = new PID(robot);

    private DcMotorEx leftFront = null;
    private DcMotorEx leftBack = null;
    private DcMotorEx rightFront = null;
    private DcMotorEx rightBack = null;
    public double[] targets = new double[3];
    public double[] vectors = new double[3];

    public void start(){
        setConfig(robot);
        setDirections();
        robot.control.init();
        //robot.lift.start();
        //robot.lift.activity(Lift.LiftTarget.UP);
    };

    public void activity(){
        if(robot.ControlMode == robot.ControlMode.DRIVING) {
            vectors[0] = robot.control.getTargetX(); vectors[1] = robot.control.getTargetY();
            vectors[2] = robot.control.getDrivingAngle();
        }
        else {
            vectors[0] = moveReg.calculation(targets[0], robot.position.globalX);
            vectors[1] = moveReg.calculation(targets[1], robot.position.globalY);
            vectors[2] = moveReg.calculation(targets[2], robot.position.globalY);
        }
        finalMovement(vectors[1], vectors[0], vectors[2]);
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
