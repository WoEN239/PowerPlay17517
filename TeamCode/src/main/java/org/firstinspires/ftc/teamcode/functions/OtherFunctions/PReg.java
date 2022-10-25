package org.firstinspires.ftc.teamcode.functions.OtherFunctions;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class PReg implements Standart {
    Robot1825 robot;
    public PReg(Robot1825 robot){ this.robot = robot; }

    private static double k = 1.0;
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