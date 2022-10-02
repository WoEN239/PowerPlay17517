package org.firstinspires.ftc.teamcode.test;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class PID implements Standart {
    Robot1825 robot;
    public PID(Robot1825 robot){ this.robot = robot; }

    private static double k = 0.0;
    private double errorX = 0.0;
    private double errorY = 0.0;
    private double vectorOX = 0.0;
    private double vectorOY = 0.0;
    public double[] position = new double[2];

    public void start(){ }

    public void calculation(double[] target){
        position[0] = robot.position.globalX;
        position[1] = robot.position.globalY;
        errorX = target[0] - position[0];
        errorY = target[1] - position[1];
        vectorOX = errorX * k;
        vectorOY = errorY * k;
    }

    public double getVectorOX(){ return vectorOX; }
    public double getVectorOY(){ return vectorOY; }

}