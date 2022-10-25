package org.firstinspires.ftc.teamcode.opmodes;

import org.firstinspires.ftc.teamcode.driving.Control;
import org.firstinspires.ftc.teamcode.functions.OtherFunctions.ButtonSwitch;
import org.firstinspires.ftc.teamcode.functions.Robot1825;

public abstract class HandControlOpMode extends BaseOpMode{

    public ButtonSwitch keeperFunction = new ButtonSwitch(() -> gamepad1.triangle, (positionK) -> robot.keeperPosition = robot.boolToKeeperPosition(positionK));
    public ButtonSwitch turnerFunction = new ButtonSwitch(() -> gamepad1.square, (positionT) -> robot.turnerPosition = robot.boolToTurnerPosition(positionT));
    public ButtonSwitch extruderFunction = new ButtonSwitch(() -> gamepad1.circle, (positionE) -> robot.extruderPosition = robot.boolToExtruderPosition(positionE));
    public ButtonSwitch controlMethod = new ButtonSwitch(() -> gamepad1.right_bumper, (control) -> robot.control.setControlMethod(control));


    @Override
    public void mainAction() {
        keeperFunction.activate();
        turnerFunction.activate();
        extruderFunction.activate();
        controlMethod.activate();
        super.mainAction();
    }

    @Override
    public void startAction(){
        robot.ControlMode = Robot1825.Mode.DRIVING;
        super.startAction();
    }

}
