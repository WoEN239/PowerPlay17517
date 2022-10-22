package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Driving.Control;

@Autonomous
public class Autonomous1 extends BaseAutonomous{

    Runnable[] firstPosition = {
            () -> { robot.move.setAutoTargets(15,15, 0); },
    };

    Runnable[] secondPosition = {
            () -> { robot.move.setAutoTargets(20,10, 0); },
    };

    Runnable[] thirdPosition = {
            () -> { robot.move.setAutoTargets(10,20, 0); },
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

    @Override
    public void mainAction() {
        super.mainAction();
    }

}
