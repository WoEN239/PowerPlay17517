package org.firstinspires.ftc.teamcode.functions.mobility;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class LiftTest implements Standart {
    Robot1825 robot;
    public LiftTest(Robot1825 robot1825){ this.robot = robot1825; }
    private DcMotorEx lift1 = null;
    private DcMotorEx lift2 = null;

    public void start(){
        lift1 = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "liftMotorLeft");
        lift2 = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "liftMotorRight");
        lift1.setDirection(DcMotorSimple.Direction.FORWARD); lift2.setDirection(lift1.getDirection().inverted());
    }

    public void activity(){}

    public boolean finish(){ return true; }

    public void setLiftParameters(boolean d, double p){
        if(d) lift1.setDirection(DcMotorSimple.Direction.FORWARD);
        else lift1.setDirection(DcMotorSimple.Direction.REVERSE);
        lift2.setDirection(lift1.getDirection().inverted());
        lift1.setPower(p); lift2.setPower(p);
    }

}
