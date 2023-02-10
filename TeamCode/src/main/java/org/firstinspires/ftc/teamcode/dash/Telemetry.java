package org.firstinspires.ftc.teamcode.dash;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class Telemetry implements Standart {
    public Robot1825 robot;
    public Telemetry(Robot1825 robot){this.robot = robot;}

    private FtcDashboard dash;
    private int iterationT = 0;
    public org.firstinspires.ftc.robotcore.external.Telemetry dashboardTelemetry = null;
    public org.firstinspires.ftc.robotcore.external.Telemetry opModeTelemetry = null;
    private org.firstinspires.ftc.robotcore.external.Telemetry dualTelemetry = null;
    private org.firstinspires.ftc.robotcore.external.Telemetry currentTelemetry = null;

    public void start() {
        dash = FtcDashboard.getInstance();
        dashboardTelemetry = dash.getTelemetry();
        opModeTelemetry = robot.linearOpMode.telemetry;
        dualTelemetry = new MultipleTelemetry(dashboardTelemetry, opModeTelemetry);

    }

    public void activity() {
        iterationT++;
        movementTelemetry();
        liftTelemetry();
        opModeTelemetry.update();
        dashboardTelemetry.update();
        //if(iterationT%5==0){ robot.flowK.activity(); }
    }

    public boolean finish() {
        return true;
    }

    private void liftTelemetry() {
        dualTelemetry.addData("Lift pos", robot.lift.getPosition());
    }

    private void movementTelemetry() {
        opModeTelemetry.addData(" X ", robot.position.globalX);
        opModeTelemetry.addData(" Y ", robot.position.globalY);
        opModeTelemetry.addData(" Angle ", robot.position.angle);
        dashboardTelemetry.addData(" X ", robot.position.globalX);
        dashboardTelemetry.addData(" Y ", robot.position.globalY);
        dashboardTelemetry.addData(" Angle ", robot.position.angle);
    }
}
