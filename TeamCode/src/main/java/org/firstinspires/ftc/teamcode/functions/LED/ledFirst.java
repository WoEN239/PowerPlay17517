package org.firstinspires.ftc.teamcode.functions.LED;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standart;

public class ledFirst implements Standart {
    public Robot1825 robot;
    public ledFirst(Robot1825 robot){this.robot = robot;}

    private DcMotorEx firstLedMotor = null;
    private DcMotorEx secondLedMotor = null;

    public LedMode ledMode = LedMode.BREATHING;
    private ElapsedTime ledTimer = new ElapsedTime();

    public void start(){
        ledTimer.reset();
        ledTimer.startTime();
        firstLedMotor = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "EncXL");
        secondLedMotor = robot.linearOpMode.hardwareMap.get(DcMotorEx.class, "EncXR");
        firstLedMotor.setDirection(DcMotorEx.Direction.FORWARD);
        secondLedMotor.setDirection(DcMotorEx.Direction.FORWARD);
    }

    public void activity(){
        switch (ledMode){
            case DRIVING:
                firstLedMotor.setPower(1);
                secondLedMotor.setPower(1);
            case BREATHING:
                firstLedMotor.setPower(Math.abs(Math.sin(ledTimer.milliseconds()/450)));
                secondLedMotor.setPower(Math.abs(Math.cos(ledTimer.milliseconds()/450)));
        }
    }

    public boolean finish(){ return true; };
}