package org.firstinspires.ftc.teamcode.functions.mobility;

import org.firstinspires.ftc.teamcode.dash.Telemetry;
import org.firstinspires.ftc.teamcode.functions.LED.LedMode;
import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.ExtruderPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.TurnerPosition;

public class TAuto implements Standart {
    Robot1825 robot;
    public TAuto(Robot1825 robot1825){ this.robot = robot1825; }

    private boolean fin = true;

    public void start(){}

    public void activity(){
        if(robot.ControlMode == Robot1825.Mode.DRIVING) tAuto();
    }

    private void tAuto(){
        if(robot.extruderPosition == ExtruderPosition.EXTRUDED){
            robot.ledFirst.ledMode = LedMode.Ist;
            robot.turnerPosition = TurnerPosition.NORMAL;
        }
        else{
            robot.ledFirst.ledMode = LedMode.IInd;
            robot.turnerPosition = TurnerPosition.TURNED;
        }
        // ^ turnerPosition && ledMode depend on the extruderPosition
    }

    public boolean finish(){ return fin; }
}
