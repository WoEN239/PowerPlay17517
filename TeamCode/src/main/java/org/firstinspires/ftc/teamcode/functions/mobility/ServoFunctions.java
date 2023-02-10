package org.firstinspires.ftc.teamcode.functions.mobility;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.ExtruderPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.KeeperPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.TurnerPosition;

@Config
public class ServoFunctions implements Standart {
    public Robot1825 robot;
    private boolean finished;

    public ServoFunctions(Robot1825 robot) {
        this.robot = robot;
    }

    //13.5 243
    public static double KPositionClosed = 195.0;
    public static double KPositionOpened = 159.3;
    public static double ExPositionSemiExtruded = 86.4;
    public static double ExPositionExtruded = 70.0;
    public static double ExPositionStart = 200.0;
    public static double ExPositionAboveFloor = 230.0;
    public static double ExPositionNonExtruded = 247.0;
    public static double TPositionNormal = 1;
    public static double TPositionTurned = 180.89;
    private static final int n = 3;
    public Servo[] servos = new Servo[n];
    public static final String[] ids = {"Keeper", "Extruder", "Turner"};
    double changedDouble = 0.0;
    public static final int N_KEEPER = 0, N_EXTRUDER = 1, N_TURNER = 2;


    private static final double[] servoRanges = {270, 270, 270};

    public static final ElapsedTime loopTimer = new ElapsedTime();

    private double[] servoPositionTargets = {KPositionClosed, ExPositionStart, TPositionNormal};
    private double[] servoPositionsApprox = {KPositionClosed, ExPositionStart, TPositionNormal};


    private static final double KEEPER_SERVO_SPEED = 0.25; /* sec/60deg */
    private static final double EXTRUDER_SERVO_SPEED = 0.25;
    private static final double TURNER_SERVO_SPEED = 0.25;

    private double[] servoSpeeds = {60.0 / KEEPER_SERVO_SPEED, 60.0 / EXTRUDER_SERVO_SPEED, 60.0 / TURNER_SERVO_SPEED};


    public void start() {
        extendPwmRange();
        for (int i = 0; i < n; i++) {
            servos[i] = robot.linearOpMode.hardwareMap.get(Servo.class, ids[i]);
            setServoPosition(i, servoPositionTargets[i]);
        }
        loopTimer.reset();
    }

    public void extendPwmRange() {
        robot.linearOpMode.hardwareMap.getAll(ServoImplEx.class).forEach(s -> s.setPwmRange(new PwmControl.PwmRange(500D, 2500D)));
    }

    public void activity() {
        for (int i = 0; i < n; i++) { //Servo position approximator
            double positionDiff = servoPositionTargets[i] - servoPositionsApprox[i];
            if (positionDiff > 0.0)
                servoPositionsApprox[i] = min(servoPositionTargets[i], servoPositionsApprox[i] + servoSpeeds[i] * loopTimer.seconds());
            else if (positionDiff < 0.0)
                servoPositionsApprox[i] = max(servoPositionTargets[i], servoPositionsApprox[i] - servoSpeeds[i] * loopTimer.seconds());
        }
        loopTimer.reset();


        if (servoWithinTarget(N_TURNER)) {
            if (robot.extruderPosition == ExtruderPosition.EXTRUDED)
                setServoPosition(N_EXTRUDER, robot.lift.isOnGround() ? ExPositionSemiExtruded : ExPositionExtruded);
            else
                setServoPosition(N_EXTRUDER, (robot.keeperPosition == KeeperPosition.CLOSED && abs(servoPositionsApprox[N_KEEPER] - KPositionClosed) < 0.1) ? ExPositionAboveFloor : ExPositionNonExtruded);
        }
        if (robot.keeperPosition == KeeperPosition.CLOSED || !servoWithinTarget(N_EXTRUDER))
            setServoPosition(N_KEEPER, KPositionClosed);
        else
            setServoPosition(N_KEEPER, KPositionOpened);
        if (servoWithinTarget(N_EXTRUDER)) {
            if (robot.extruderPosition == ExtruderPosition.EXTRUDED)
                setServoPosition(N_TURNER, TPositionTurned);
            else
                setServoPosition(N_TURNER, TPositionNormal);
        }


        /*

        if (robot.keeperPosition == Ke`eperPosition.CLOSED) {
            changedDouble = KPositionClosed;
            robot.telemetry.opModeTelemetry.addData("I. Status", "CLOSED");
        } else {
            changedDouble = KPositionOpened;
            robot.telemetry.opModeTelemetry.addData("I. Status", "OPENED");
        }
        setServoPosition(N_KEEPER, changedDouble);

        if (robot.extruderPosition == ExtruderPosition.EXTRUDED && Math.abs(servoPositionsApprox[N_TURNER] - TPositionTurned) < 0.1) {
            changedDouble = ExPositionExtruded;
            robot.telemetry.opModeTelemetry.addData("II.Status", "EXTRUDED");
        } else {
            changedDouble = ExPositionNonExtruded;
            robot.telemetry.opModeTelemetry.addData("II. Status", "NOT_EXTRUDED");
        }
        setServoPosition(N_EXTRUDER, changedDouble);


        if (robot.turnerPosition == TurnerPosition.TURNED) {
            changedDouble = TPositionTurned;
            robot.telemetry.opModeTelemetry.addData("III. Status", "NORMAL");
        } else {
            changedDouble = TPositionNormal;
            robot.telemetry.opModeTelemetry.addData("III. Status", "TURNED");
        }
        setServoPosition(N_TURNER, changedDouble);// ^ Depends on 'extruderPosition'

         */
    }

    private void setServoPosition(int servoIndex, double degrees) {
        servos[servoIndex].setPosition(degrees / servoRanges[servoIndex]);
        servoPositionTargets[servoIndex] = degrees;
    }

    private boolean servoWithinTarget(int servoIndex) {
        return abs(servoPositionTargets[servoIndex] - servoPositionsApprox[servoIndex]) < 0.5;
    }

    public boolean finish() {
        finished = (robot.keeperPosition == KeeperPosition.CLOSED && servoPositionsApprox[N_KEEPER] == KPositionClosed) || (robot.keeperPosition == KeeperPosition.OPENED && servoPositionsApprox[N_KEEPER] == KPositionOpened);
        finished &= (robot.turnerPosition == TurnerPosition.NORMAL && servoPositionsApprox[N_TURNER] == TPositionNormal) || (robot.turnerPosition == TurnerPosition.TURNED && servoPositionsApprox[N_TURNER] == TPositionTurned);
        finished &= (robot.extruderPosition == ExtruderPosition.NOT_EXTRUDED && servoPositionsApprox[N_EXTRUDER] == ExPositionNonExtruded) || (robot.extruderPosition == ExtruderPosition.EXTRUDED && servoPositionsApprox[N_EXTRUDER] == ExPositionExtruded);
        return finished;
    }

}
