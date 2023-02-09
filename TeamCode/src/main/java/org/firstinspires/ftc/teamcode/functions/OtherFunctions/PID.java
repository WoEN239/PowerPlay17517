package org.firstinspires.ftc.teamcode.functions.OtherFunctions;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class PID implements Standart {
    Robot1825 robot;
    public PID(Robot1825 robot){ this.robot = robot; }

    private static double pk = 1.0;
    private static double ik = 1.0;
    private static double dk = 1.0;
    private double error = 0.0;
    private double U = 0.0;
    private double PC = 0;
    private double IC = 0;
    private double DC = 0;
    private double differential = 0;


    public void start(){ }

    public double calculation(double target, double position){
        error = target - position;
        PC = error * pk;
        IC = 0 * ik;
        DC = (error - differential) * dk;
        differential = error;
        U = PC + IC + DC;
        return U;
    }
}