package org.firstinspires.ftc.teamcode.functions.mobility;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standard;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.ExtruderPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.KeeperPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.TurnerPosition;

public class SetServoPositions implements Standard {
    public Robot1825 robot;
    private boolean finished;
    public SetServoPositions(Robot1825 robot){this.robot = robot;}
    private static final int n = 3;
    private double[] firstServoPositions = {1.0, 1.0, 1.0};
    private double[] secondServoPositions = {0.0, 0.0, 0.0};
    private double[] realServoPositions = {0.0, 0.0, 0.0};
    private Servo[] servos = new Servo[n];
    private String[] ids = { "Servo1", "Servo2", "Servo3" };

    public void start(){
        for(int i=0;i<n;i++){ servos[i] = robot.linearOpMode.hardwareMap.get(Servo.class, ids[i]); }
    }

    public void activity(){
        switch (robot.keeperPosition){
            case CLOSED: realServoPositions[0] = firstServoPositions[0];
            case OPENED: realServoPositions[0] = secondServoPositions[0];
        }
        switch (robot.turnerPosition){
            case NORMAL: realServoPositions[1] = firstServoPositions[1];
            case TURNED: realServoPositions[1] = secondServoPositions[1];
        }
        switch (robot.extruderPosition){
            case NOT_EXTRUDED: realServoPositions[2] = firstServoPositions[2];
            case EXTRUDED: realServoPositions[2] = secondServoPositions[2];
        }
        for(int i=0;i<n;i++){ servos[i].setPosition(realServoPositions[i]); }
    }

    public boolean finish(){
        finished = (robot.keeperPosition == KeeperPosition.CLOSED && servos[0].getPosition() == firstServoPositions[0]) || (robot.keeperPosition == KeeperPosition.OPENED && servos[0].getPosition() == secondServoPositions[0]);
        finished &= (robot.turnerPosition == TurnerPosition.NORMAL && servos[1].getPosition() == firstServoPositions[1]) || (robot.turnerPosition == TurnerPosition.TURNED && servos[1].getPosition() == secondServoPositions[1]);
        finished &= (robot.extruderPosition == ExtruderPosition.NOT_EXTRUDED && servos[2].getPosition() == firstServoPositions[2]) || (robot.extruderPosition == ExtruderPosition.EXTRUDED && servos[2].getPosition() == secondServoPositions[2]);
        return finished;
    }

}
