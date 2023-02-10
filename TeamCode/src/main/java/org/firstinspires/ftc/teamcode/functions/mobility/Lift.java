package org.firstinspires.ftc.teamcode.functions.mobility;

import com.qualcomm.robotcore.hardware.DcMotor;
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
    private double usefulVector = 0.0;
    private double upPosition = 0.0;
    private double middleUpPosition = 0.0;
    private double middleDownPosition = 0.0;
    private double downPosition = 0.0;

    //public enum LiftTarget { UP, MIDDLE_UP, MIDDLE_DOWN, DOWN; }

    public void start() {
        lift1 = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "liftLeft");
        lift2 = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "liftRight");
        lift1.setDirection(DcMotorSimple.Direction.FORWARD);
        lift2.setDirection(lift1.getDirection().inverted());
        resetEncoders();
    }

    public void resetEncoders() {
        lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public double getPosition() {
        return lift1.getCurrentPosition() * 0.5 + lift2.getCurrentPosition() * 0.5;
    }

    public boolean isOnGround(){
        return getPosition() < 160.0;
    }

    public void activity() {
        /*switch (target) {
            case(UP):
                usefulVector = robot.liftPID.calculation(upPosition, lift1.getCurrentPosition());
                break;
            case(MIDDLE_UP):
                usefulVector = robot.liftPID.calculation(middleUpPosition, lift1.getCurrentPosition());
                break;
            case(MIDDLE_DOWN):
                usefulVector = robot.liftPID.calculation(middleDownPosition, lift1.getCurrentPosition());
                break;
            case(DOWN):
                usefulVector = robot.liftPID.calculation(downPosition, lift1.getCurrentPosition());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + target);
                break;
        }
        lift1.setPower(usefulVector);
        lift2.setPower(usefulVector);*/
        double power = -robot.control.gamepad1.left_trigger + robot.control.gamepad1.right_trigger + 0.15;
        lift1.setPower(power);
        lift2.setPower(power);
    }

    public double PID(double target, double sensor){ return 0; }

    public boolean finish(){ return oldLiftTargetLimit == liftTargetLimit; }

    /*private void setLiftTargetLimit(LiftTarget localTarget){
        if(localTarget == localTarget.UP){ liftTargetLimit = 1; }
        else if(localTarget == localTarget.MIDDLE_UP){ liftTargetLimit = 2; }
        else if(localTarget == localTarget.MIDDLE_DOWN){ liftTargetLimit = 3; }
        else if(localTarget == localTarget.DOWN){ liftTargetLimit = 4; }
    }*/
}
