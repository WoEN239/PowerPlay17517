package org.firstinspires.ftc.teamcode.opmodes;

public class TeleOpOneGamepad extends HandControlOpMode{

    @Override
    public void mainAction(){
        robot.keeperPosition = robot.control.getKeeperStatus();
        robot.turnerPosition = robot.control.getTurnerStatus();
        robot.extruderPosition = robot.control.getExtruderStatus();
        robot.control.
        robot.activity();
        super.mainAction();
    }

}
