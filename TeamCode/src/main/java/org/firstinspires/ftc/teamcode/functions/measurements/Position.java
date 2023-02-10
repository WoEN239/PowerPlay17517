package org.firstinspires.ftc.teamcode.functions.measurements;

import static java.lang.Math.PI;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class Position implements Standart {
    public Robot1825 robot;

    public Position(Robot1825 robot) {
        this.robot = robot;
        robot.linearOpMode.sleep(0);
        robot.linearOpMode.hardwareMap.getAll(DcMotor.class);
        // ^ NC
        encXR = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "EncXR");
        encXL = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "EncXL");
        // ^ on LED's motors
        encY = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "R1");
        // ^ on frontal right motor
    }

    private final DcMotorEx encXR;
    private final DcMotorEx encXL;
    private final DcMotorEx encY;

    private BNO055IMU gyro;

    int gyroIteration = 0;
    // ^ number of angle calculations

    private double distanceX0 = 0.0;
    private double distanceY0 = 0.0;
    private double DX = 0.0;
    private double encAngle = 0.0;
    // ^ temporary variables

    public double angle = 0.0;
    public double globalX = 0.0;
    public double globalY = 0.0;
    // ^ global variables

    private static final double circle = 130;
    private static final double encConst = 0.01;
    private static final double gyroPriority = 0.5;
    private static final int gyroTact = 10;
    // ^ constants

    private boolean fin = false;

    public void start() {
        encInit();
        gyroInit();
    }

    public void activity() {
        getDistance();
        getAngle();
        globalCalculation();
        resetVar();
        fin = true;
    }

    public boolean finish() {
        return fin;
    }

    private void getAngle() {
        encAngle = DX * encConst * 0.5 / circle * PI * 2.0;
        // ^ temporary angle from odometers
        angle += encAngle;
        // ^ first changing of global angle: only with encAngle
        if(gyroIteration % gyroTact == 0) angle += (gyro.getAngularOrientation().firstAngle - angle) * gyroPriority;
        // ^ second changing of global angle: correction with gyro
    }

    private void getDistance() {
        DX = encXL.getCurrentPosition() - encXR.getCurrentPosition();
        distanceX0 = (encXL.getCurrentPosition() + encXR.getCurrentPosition()) * encConst * 0.5;
        distanceY0 = encY.getCurrentPosition() * encConst;
        //resetEncoders();
    }

    private void globalCalculation(){
        globalX += distanceY0 * Math.sin(angle) + distanceX0 * Math.cos(angle);
        globalY += distanceY0 * Math.cos(angle) + distanceX0 * Math.sin(angle);
    }

    private void encInit(){
        encXL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encXL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        encXR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encXR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        encY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encY.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void resetEncoders() {
        encXL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encXR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encXL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        encXR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        encY.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    private void resetVar(){
        encAngle = 0.0;
        distanceX0 = 0.0;
        distanceY0 = 0.0;
    }

    private void gyroInit(){
        gyro = robot.linearOpMode.hardwareMap.get(BNO055IMU.class, "imu");
        gyro.initialize(new BNO055IMU.Parameters());
        gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS);
    }
}

/*
gyroActive = robot.iteration % gyroTact == 0;
        if (true) (Math.abs((encXL.getCurrentPosition() / encXR.getCurrentPosition()) - 1.0) > 0.1) {
        //TODO fix
        encAngle = ((encXL.getCurrentPosition() - encXR.getCurrentPosition()) / 2.0 / encConst / circle * Math.PI * 2.0);
        if (gyroActive) {
        angle += encAngle * (1 - gyroPriority);
        gyroAngle = (angle - gyro.getAngularOrientation().firstAngle - gyroIteration * PI * 2.0);
        angle += gyroAngle * gyroPriority;
        } else
        angle += encAngle;
        }
        distanceX0 -= Math.abs((encXL.getCurrentPosition() - encXR.getCurrentPosition()) / encConst / 2.0);
        distanceY0 -= (encXR.getCurrentPosition() - encXL.getCurrentPosition()) / encConst / 2.0;
 */