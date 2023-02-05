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
    }

    private DcMotorEx encXR = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "EncXR");
    private DcMotorEx encXL = encXR = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "EncXL");
    private DcMotorEx encY = encXR = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "R1");

    private BNO055IMU gyro;

    double distanceX0 = 0.0;
    private double distanceY0 = 0.0;
    public double angle = 0.0;
    private double encAngle = 0.0;
    private double gyroAngle = 0.0;
    public double globalX = 0.0;
    public double globalY = 0.0;
    private double gyroIteration = 0.0;
    private static final double circle = 130;
    private static final double encConst = 0.01;
    private static final double gyroPriority = 0.5;
    private static final int gyroTact = 5;
    private boolean fin = false;
    private boolean gyroActive = false;

    public void start() {
        encInit();
        gyroInit();
    }

    public void activity() {
        angleСor();
        getDistance();
        getAngle();
        globalX += distanceY0 * Math.sin(angle) + distanceX0 * Math.cos(angle);
        globalY += distanceY0 * Math.cos(angle) + distanceX0 * Math.sin(angle);
        distanceX0 = 0.0;
        distanceY0 = 0.0;
    }

    public boolean finish() {
        return fin;
    }

    private void getAngle() {
        gyroActive = robot.iteration % gyroTact==0;
        if (Math.abs((encXL.getCurrentPosition() / encXR.getCurrentPosition()) - 1) > 0.1) {
            encAngle = ((encXL.getCurrentPosition() - encXR.getCurrentPosition()) / 2 / encConst / circle * Math.PI * 2);
            if(gyroActive){
                angle += encAngle * (1 - gyroPriority);
                gyroAngle = (angle - gyro.getAngularOrientation().firstAngle - gyroIteration * PI * 2);
                angle += gyroAngle * gyroPriority;
            }
            else{ angle += encAngle; }
        }
        distanceX0 -= Math.abs((encXL.getCurrentPosition() - encXR.getCurrentPosition()) / encConst / 2);
        distanceY0 -= (encXR.getCurrentPosition() - encXL.getCurrentPosition()) / encConst / 2;
    }

    private void getDistance() {
        distanceX0 = (encXL.getCurrentPosition() + encXR.getCurrentPosition()) / encConst / 2;
        distanceY0 = encY.getCurrentPosition() / encConst;
        resetEncoders();
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
    }

    private void gyroInit(){
        gyro = robot.linearOpMode.hardwareMap.get(BNO055IMU.class, "imu");
        gyro.initialize(new BNO055IMU.Parameters());
        gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS);
    }

    private void angleСor(){
            while (angle > PI) gyroIteration++;
            while (angle < -PI) gyroIteration--;
    }
}