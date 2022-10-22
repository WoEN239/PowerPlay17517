package org.firstinspires.ftc.teamcode.Driving;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.ExtruderPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.KeeperPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.TurnerPosition;

public class Control{
    Robot1825 robot;
    public Control(Robot1825 robot){ this.robot = robot; }
    Gamepad gamepad1 = new Gamepad();

    public enum ControlMethod{ STICKS, TOUCHPAD, STUPID };
    public ControlMethod controlMethod;
    private final static double stickDistance = 10;
    private final static double angleConst = 1.2;

    public void init(){ }

    public double getTargetY(){
        if(controlMethod == ControlMethod.STICKS){ return robot.position.globalY + gamepad1.left_stick_y * stickDistance; }
        else if(controlMethod == ControlMethod.TOUCHPAD){ return robot.position.globalY + gamepad1.touchpad_finger_1_y * stickDistance; }
        else{ return gamepad1.left_stick_y; }
    }

    public double getTargetX(){
        if(controlMethod == ControlMethod.STICKS){ return robot.position.globalX + gamepad1.left_stick_x * stickDistance; }
        else if(controlMethod == ControlMethod.TOUCHPAD){ return robot.position.globalX + gamepad1.touchpad_finger_1_x * stickDistance; }
        else{ return gamepad1.left_stick_x; }
    }

    public double getDrivingAngle(){
        //return robot.position.angle + (gamepad1.right_trigger - gamepad1.left_trigger);
        return (gamepad1.right_trigger - gamepad1.left_trigger);
    }

    public KeeperPosition getKeeperStatus(){
        if(gamepad1.a){ return KeeperPosition.CLOSED; }
        else{ return KeeperPosition.OPENED; }
    }

    public TurnerPosition getTurnerStatus(){
        if(gamepad1.b){ return TurnerPosition.NORMAL; }
        else{ return TurnerPosition.TURNED; }
    }

    public ExtruderPosition getExtruderStatus(){
        if(gamepad1.start){ return ExtruderPosition.NOT_EXTRUDED; }
        else{ return ExtruderPosition.EXTRUDED; }
    }

}
