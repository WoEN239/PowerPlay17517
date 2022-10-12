package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class Autonomous1 extends BaseAutonomous{

    Runnable[] firstPosition = {
            () -> { robot.move.setAutoTargets(15,15); },
    };

    Runnable[] secondPosition = {
            () -> { robot.move.setAutoTargets(20,10); },
    };

    Runnable[] thirdPosition = {
            () -> { robot.move.setAutoTargets(10,20); },
    };

    @Override
    protected Runnable[] firstPosition() {
        return firstPosition;
    }

    @Override
    protected Runnable[] secondPosition() {
        return secondPosition;
    }

    @Override
    protected Runnable[] thirdPosition() { return thirdPosition;}

}
