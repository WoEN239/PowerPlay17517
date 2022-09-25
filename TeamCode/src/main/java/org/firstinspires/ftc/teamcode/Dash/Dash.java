package org.firstinspires.ftc.teamcode.Dash;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class Dash implements Standart{
    public Robot1825 robot;
    public Dash(Robot1825 robot){this.robot = robot;}

    public void start(){};

    public void activity(){}

    public boolean finish(){ return true; };
}
