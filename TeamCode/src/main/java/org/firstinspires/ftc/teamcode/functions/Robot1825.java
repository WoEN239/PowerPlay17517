package org.firstinspires.ftc.teamcode.functions;

import android.os.Build;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.functions.measurements.LimitSwitch;
import org.firstinspires.ftc.teamcode.functions.mobility.GetFragment;
import org.firstinspires.ftc.teamcode.functions.mobility.Move;


import java.util.Arrays;
import java.util.List;

public class Robot1825 {
    public LinearOpMode linearOpMode;
    public Robot1825(LinearOpMode linearOpMode) {this.linearOpMode = linearOpMode;}

    private final GetFragment getFragment = new GetFragment(this);
    private final Move move = new Move(this);
    public final org.firstinspires.ftc.teamcode.functions.measurements.Position position = new  org.firstinspires.ftc.teamcode.functions.measurements.Position(this);
    public final LimitSwitch limitSwitch = new LimitSwitch(this);

    public int iteration = 1;

    public final Standart[] allFunctions = new Standart[]{
            move, position, getFragment,
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

    public boolean finish() {
        return Arrays.stream(allFunctions).allMatch(Standart::finish);
    }

}