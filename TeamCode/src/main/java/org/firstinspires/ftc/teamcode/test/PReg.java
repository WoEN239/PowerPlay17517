package org.firstinspires.ftc.teamcode.test;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standard;

public class PReg implements Standard {
    Robot1825 robot;
    public PReg(Robot1825 robot){ this.robot = robot; }

    private static double k = 0.0;
    private double error = 0.0;
    private double vector = 0.0;

    public void start(){ }

    public double calculation(double target, double position){
        position = robot.position.globalX;
        error = target - position;
        vector = error * k;
        return vector;
    }
}