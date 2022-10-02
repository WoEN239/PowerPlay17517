package org.firstinspires.ftc.teamcode.Driving;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class Control{
    Robot1825 robot;
    public Control(Robot1825 robot){ this.robot = robot; }
    Gamepad gamepad1 = new Gamepad();

    public double[] sticksTarget = new double[2];

    private final static double stickDistance = 10;

    public void init(){ }

    public double getTargetY(){ return gamepad1.left_stick_x * stickDistance; }
    public double getTargetX(){ return gamepad1.left_stick_y * stickDistance; }

}
