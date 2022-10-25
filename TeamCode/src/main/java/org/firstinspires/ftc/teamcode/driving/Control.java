package org.firstinspires.ftc.teamcode.driving;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.ExtruderPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.KeeperPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.TurnerPosition;

public class Control{
    public Robot1825 robot;
    public Control(Robot1825 robot){ this.robot = robot; }
    private Gamepad gamepad1 = null;
    private enum ControlMethod{ STICKS, TOUCHPAD, STUPID, DRIVERno1, DRIVERno2 };
    private ControlMethod controlMethod;
    private final static double stickDistance = 10;
    private final static double angleConst = 1.2;

    public void init(){
         gamepad1 = robot.linearOpMode.gamepad1;
    }

    public double getTargetY(){
        if(controlMethod == ControlMethod.STICKS){ return robot.position.globalY + gamepad1.left_stick_y * stickDistance; }
        else if(controlMethod == ControlMethod.TOUCHPAD){ return robot.position.globalY + gamepad1.touchpad_finger_1_y * stickDistance; }
        else if(controlMethod == ControlMethod.DRIVERno1){ return gamepad1.left_stick_y; }
        else { return gamepad1.right_trigger - gamepad1.left_trigger; }
    }

    public double getTargetX(){
        if(controlMethod == ControlMethod.STICKS){ return robot.position.globalX + gamepad1.left_stick_x * stickDistance; }
        else if(controlMethod == ControlMethod.TOUCHPAD){ return robot.position.globalX + gamepad1.touchpad_finger_1_x * stickDistance; }
        else{ return gamepad1.left_stick_x; }
    }

    public double getDrivingAngle(){
        //return robot.position.angle + (gamepad1.right_trigger - gamepad1.left_trigger);
        //return (gamepad1.right_trigger - gamepad1.left_trigger);
        return gamepad1.right_stick_x;
    }

    public void setControlMethod(boolean method){
        if(method) controlMethod = ControlMethod.DRIVERno1;
        else controlMethod = ControlMethod.DRIVERno2;
    }

}
