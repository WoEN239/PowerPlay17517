package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOpOneGamepad extends HandControlOpMode{

    @Override
    public void mainAction(){
        robot.start();
        while (opModeIsActive()) {
            super.mainAction();
        }
    }

}
