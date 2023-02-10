package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.functions.Robot1825;

public abstract class BaseOpMode extends LinearOpMode {
    protected Robot1825 robot;

    public void startAction() {
        robot.start();
    }

    public void mainAction() {
        robot.activity();
    }

    @Override
    public void waitForStart(){
        if(!isStarted() && !isStopRequested()){ startAction(); }
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot1825(this);
        robot.start();
        startAction();
        waitForStart();
        mainAction();
        robot.telemetry.opModeTelemetry.addData("Status", "Finished successfully");
        robot.telemetry.opModeTelemetry.update();
    }
}
