package org.firstinspires.ftc.teamcode.functions;

import android.os.Build;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Driving.Control;
import org.firstinspires.ftc.teamcode.dash.Telemetry;
import org.firstinspires.ftc.teamcode.functions.measurements.LimitSwitch;
import org.firstinspires.ftc.teamcode.functions.mobility.GetFragment;
import org.firstinspires.ftc.teamcode.functions.mobility.Lift;
import org.firstinspires.ftc.teamcode.functions.mobility.Move;
import org.firstinspires.ftc.teamcode.test.PID;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Robot1825 {
    public LinearOpMode linearOpMode;
    public Robot1825(LinearOpMode linearOpMode) { this.linearOpMode = linearOpMode; }

    private final GetFragment getFragment = new GetFragment(this);
    public final Move move = new Move(this);
    public final org.firstinspires.ftc.teamcode.functions.measurements.Position position = new  org.firstinspires.ftc.teamcode.functions.measurements.Position(this);
    public final LimitSwitch limitSwitch = new LimitSwitch(this);
    public final Lift lift = new Lift(this);
    public final Control control = new Control(this);
    public final PID pid = new PID(this);
    public final Telemetry telemetry = new Telemetry(this);

    public int iteration = 1;
    public enum Mode{ AUTONOMOUS, DRIVING };
    public Mode ControlMode;

    public final Standart[] allFunctions = new Standart[]{
            move, position, getFragment, lift,
    };
    private List<LynxModule> revHubs = null;

    public void start() {
        revHubs = linearOpMode.hardwareMap.getAll(LynxModule.class);
        for (Standart standart : allFunctions)
            standart.start();
        for (LynxModule module : revHubs)
            module.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
    }

    public void updateRevBulkCache() {
        for (LynxModule module : revHubs)
            module.clearBulkCache();
    }

    public void activity() {
        updateRevBulkCache();
        iteration++;
        for (Standart standart : allFunctions)
            standart.activity();
    }

    public DcMotorSimple.Direction revD(DcMotorSimple.Direction direction){
        if(direction == DcMotorSimple.Direction.FORWARD){ return DcMotorSimple.Direction.REVERSE; }
        else{ return DcMotorSimple.Direction.FORWARD; }
    }

    public boolean boolD(DcMotorSimple.Direction direction){
        if(direction == DcMotorSimple.Direction.FORWARD){ return true; }
        else{ return false; }
    }

    public boolean finish() {
        return Arrays.stream(allFunctions).allMatch(Standart::finish);
    }

}