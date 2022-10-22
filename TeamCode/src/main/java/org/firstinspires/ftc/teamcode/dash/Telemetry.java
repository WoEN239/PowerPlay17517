package org.firstinspires.ftc.teamcode.dash;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standard;

public class Telemetry implements Standard {
    public Robot1825 robot;
    public Telemetry(Robot1825 robot){this.robot = robot;}

    private FtcDashboard dash;
    private org.firstinspires.ftc.robotcore.external.Telemetry dashboardTelemetry = null;
    public org.firstinspires.ftc.robotcore.external.Telemetry opModeTelemetry = null;
    private org.firstinspires.ftc.robotcore.external.Telemetry currentTelemetry = null;

    public void start() {
        dash = FtcDashboard.getInstance();
        dashboardTelemetry = dash.getTelemetry();
        opModeTelemetry = robot.linearOpMode.telemetry;
    }

    public void activity(){
        movementTelemetry();
    }

    public boolean finish(){ return true; }

    private void movementTelemetry(){
        opModeTelemetry.addData(" X ", robot.position.globalX);
        opModeTelemetry.addData(" Y ", robot.position.globalY);
        opModeTelemetry.addData(" Angle ", robot.position.angle);
        dashboardTelemetry.addData(" X ", robot.position.globalX);
        dashboardTelemetry.addData(" Y ", robot.position.globalY);
        dashboardTelemetry.addData(" Angle ", robot.position.angle);
    }
}
