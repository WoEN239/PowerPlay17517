package org.firstinspires.ftc.teamcode.functions.mobility;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.ExtruderPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.KeeperPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.TurnerPosition;

public class ServoFunctions implements Standart {
    public Robot1825 robot;
    private boolean finished;
    public ServoFunctions(Robot1825 robot){this.robot = robot;}
    private static double KPositionClosed = 0.25;
    private static double KPositionOpened = 0.0;
    private static double TPositionNormal = 0.77;
    private static double TPositionTurned = 0.0;
    private static double ExPositionExtruded = 0.05;
    private static double ExPositionNonExtruded = 0.3;
    private static int n = 3;
    public Servo[] servos = new Servo[n];
    private String[] ids = { "Keeper", "Extruder", "Turner" };
    private double changedDouble = 0.0;

    public void start(){
        for(int i=0;i<n;i++){ servos[i] = robot.linearOpMode.hardwareMap.get(Servo.class, ids[i]); }
    }

    public void activity(){

        if(robot.keeperPosition == KeeperPosition.CLOSED){ changedDouble = KPositionClosed; robot.telemetry.opModeTelemetry.addData("I. Status", "CLOSED"); }
        else { changedDouble = KPositionOpened; robot.telemetry.opModeTelemetry.addData("II. Status", "OPENED"); }
        servos[0].setPosition(changedDouble);

        if(robot.extruderPosition == ExtruderPosition.EXTRUDED){ changedDouble = ExPositionExtruded; robot.telemetry.opModeTelemetry.addData("Status", "EXTRUDED"); }
        else { changedDouble = ExPositionNonExtruded; robot.telemetry.opModeTelemetry.addData("III. Status", "NOT_EXTRUDED"); }
        servos[1].setPosition(changedDouble);

        if(robot.turnerPosition == TurnerPosition.TURNED){ changedDouble = TPositionTurned; robot.telemetry.opModeTelemetry.addData("Status", "NORMAL"); }
        else { changedDouble = TPositionNormal; robot.telemetry.opModeTelemetry.addData("Status", "TURNED"); }
        servos[2].setPosition(changedDouble);

        robot.telemetry.opModeTelemetry.update();
    }

    public boolean finish(){
        finished = (robot.keeperPosition == KeeperPosition.CLOSED && servos[0].getPosition() == KPositionClosed) || (robot.keeperPosition == KeeperPosition.OPENED && servos[0].getPosition() == KPositionOpened);
        finished &= (robot.turnerPosition == TurnerPosition.NORMAL && servos[1].getPosition() == TPositionNormal) || (robot.turnerPosition == TurnerPosition.TURNED && servos[1].getPosition() == TPositionTurned);
        finished &= (robot.extruderPosition == ExtruderPosition.NOT_EXTRUDED && servos[2].getPosition() == ExPositionNonExtruded) || (robot.extruderPosition == ExtruderPosition.EXTRUDED && servos[2].getPosition() == ExPositionExtruded);
        return finished;
    }

}
