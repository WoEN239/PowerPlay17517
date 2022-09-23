package org.firstinspires.ftc.teamcode.functions.measurements;

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

    private DcMotorEx encXR = null;
    private DcMotorEx encXL = null;
    private DcMotorEx encY = null;
    private BNO055IMU gyro;

    double distanceX0 = 0.0;
    private double distanceY0 = 0.0;
    private double angle = 0.0;
    private double globalX = 0.0;
    private double globalY = 0.0;
    private static final double circle = 100;
    private static final double encConst = 100;
    private boolean fin = false;

    public void start() {
        encFuck();
        gyroFuck();
    }

    ;

    public void activity() {
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
        //TODO why use gyro angle every iteration?
        if (Math.abs((encXL.getCurrentPosition() / encXR.getCurrentPosition()) - 1) > 0.2) {
            angle += ((encXL.getCurrentPosition() - encXR.getCurrentPosition()) / circle / 2 * Math.PI * 2) / 2;
            angle += (angle - gyro.getAngularOrientation().firstAngle) / 2; //FIXME angle - angle = ???
        }
        distanceX0 -= Math.abs((encXL.getCurrentPosition() - encXR.getCurrentPosition()) / encConst / 2);
    }

    private void getDistance() {
        distanceX0 = (encXL.getCurrentPosition() + encXR.getCurrentPosition()) / encConst / 2;
        distanceY0 = encY.getCurrentPosition() / encConst;
    }

    private void encFuck() {
        encXL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encXL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        encXR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encXR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        encY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encY.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void gyroFuck(){
        gyro = robot.linearOpMode.hardwareMap.get(BNO055IMU.class, "imu");
        gyro.initialize(new BNO055IMU.Parameters());
        gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
    }
}