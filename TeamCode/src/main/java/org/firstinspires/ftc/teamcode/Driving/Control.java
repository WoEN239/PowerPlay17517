package org.firstinspires.ftc.teamcode.Driving;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class Control{
    Robot1825 robot;
    public Control(Robot1825 robot){ this.robot = robot; }
    Gamepad gamepad1 = new Gamepad();

    public enum ControlMethod{ STICKS, TOUCHPAD };
    ControlMethod controlMethod;
    public double[] sticksTarget = new double[2];
    private final static double stickDistance = 10;
    private final static double angleConst = 1.2;

    public void init(){ }

    public double getTargetY(){
        if(controlMethod == ControlMethod.STICKS){ return gamepad1.left_stick_y * stickDistance; }
        else { return gamepad1.touchpad_finger_1_y; }
    }

    public double getTargetX(){
        if(controlMethod == ControlMethod.STICKS){ return gamepad1.left_stick_x * stickDistance; }
        else { return gamepad1.touchpad_finger_1_x; }
    }

    public double getDrivingAngle(){
        if(gamepad1.left_bumper){ return angleConst; }
        else{ return -angleConst; }
    }
}
