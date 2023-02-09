package org.firstinspires.ftc.teamcode.opmodes.autoModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.BaseAutonomous;
import org.firstinspires.ftc.teamcode.opmodes.opModeEnums.Alliance;
import org.firstinspires.ftc.teamcode.opmodes.opModeEnums.StartPosition;

@Autonomous
public class BlueCenterAuto extends BaseAutonomous {

    Runnable[] firstPosition = {
            () -> { robot.move.setAutoTargets(-20,0, 0); },
    };

    Runnable[] secondPosition = {
            () -> { /*robot.move.setAutoTargets(20,10, 0);*/ },
    };

    Runnable[] thirdPosition = {
            () -> {/* robot.move.setAutoTargets(10,20, 0);*/ },
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
        super.alliance = Alliance.BLUE;
        super.startPosition = StartPosition.TO_CENTER;
        super.mainAction();
    }

}
