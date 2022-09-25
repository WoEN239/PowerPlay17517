package org.firstinspires.ftc.teamcode.functions.LED;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class ledFirst implements Standart {
    public Robot1825 robot;
    public ledFirst(Robot1825 robot){this.robot = robot;}

    private DcMotorEx ledMotor = null;

    private double ledForce = 0.0;

    public void start(){
        ledMotor = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "#1");
        ledMotor.setDirection(DcMotorEx.Direction.FORWARD);
    };

    public void activity(){}

    public boolean finish(){ return true; };
}
