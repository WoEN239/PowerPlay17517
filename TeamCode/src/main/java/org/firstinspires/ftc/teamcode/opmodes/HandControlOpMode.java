package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.functions.Robot1825;

public abstract class HandControlOpMode extends BaseOpMode{

    @Override
    public void startAction(){
        robot.ControlMode = Robot1825.Mode.DRIVING;
        super.startAction();
    }

}
