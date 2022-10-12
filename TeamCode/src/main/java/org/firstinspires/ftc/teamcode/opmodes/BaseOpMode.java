package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.functions.Robot1825;

public abstract class BaseOpMode extends LinearOpMode {
    protected final Robot1825 robot = new Robot1825(this);

    public void startAction() {
        robot.activity();
    }

    public void mainAction() { }

    @Override
    public void waitForStart(){
        if(!isStarted() && !isStopRequested()){ startAction(); }
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robot.start();
        waitForStart();
        mainAction();
        robot.telemetry.opModeTelemetry.addData("Status", "Finished successfully");
        robot.telemetry.opModeTelemetry.update();
    }
}
