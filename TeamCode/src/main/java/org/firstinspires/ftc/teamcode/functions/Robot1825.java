package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.dash.Telemetry;
import org.firstinspires.ftc.teamcode.driving.Control;
import org.firstinspires.ftc.teamcode.functions.LED.ledFirst;
import org.firstinspires.ftc.teamcode.functions.measurements.LimitSwitch;
import org.firstinspires.ftc.teamcode.functions.measurements.Position;
import org.firstinspires.ftc.teamcode.functions.mobility.FlowK;
import org.firstinspires.ftc.teamcode.functions.mobility.Lift;
import org.firstinspires.ftc.teamcode.functions.mobility.Move;
import org.firstinspires.ftc.teamcode.functions.mobility.ServoFunctions;
import org.firstinspires.ftc.teamcode.functions.mobility.TAuto;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.ExtruderPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.KeeperPosition;
import org.firstinspires.ftc.teamcode.functions.mobility.servoEnums.TurnerPosition;
import org.firstinspires.ftc.teamcode.functions.openCV.AprilDetection;
import org.firstinspires.ftc.teamcode.opmodes.opModeEnums.ConePosition;

import java.util.Arrays;
import java.util.List;

public class Robot1825 {
    public LinearOpMode linearOpMode;

    public final Move move;
    public final Position position;
    public final LimitSwitch limitSwitch;
    public final Lift lift;
    public final Control control;
    public final ledFirst ledFirst;
    public final Telemetry telemetry;
    public final ServoFunctions servoFunctions;
    public final FlowK flowK;
    public final AprilDetection aprilDetection;
    public TAuto tAuto;

    public Robot1825(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
        move = new Move(this);
        position = new Position(this);
        limitSwitch = new LimitSwitch(this);
        lift = new Lift(this);
        control = new Control(this);
        ledFirst = new ledFirst(this);
        telemetry = new Telemetry(this);
        servoFunctions = new ServoFunctions(this);
        flowK = new FlowK(this);
        aprilDetection = new AprilDetection(this);
        tAuto = new TAuto(this);
        allFunctions = new Standart[]{
                move, servoFunctions, telemetry, ledFirst, lift, position, tAuto
        };
    }

    public ConePosition conePosition = ConePosition.UNKNOWN;
    public KeeperPosition keeperPosition = KeeperPosition.CLOSED;
    public TurnerPosition turnerPosition = TurnerPosition.NORMAL;
    public ExtruderPosition extruderPosition = ExtruderPosition.NOT_EXTRUDED;

    public int iteration = 1;

    public enum Mode {AUTONOMOUS, DRIVING}

    public Mode ControlMode = Mode.DRIVING;

    public final Standart[] allFunctions;

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

    public KeeperPosition boolToKeeperPosition(Boolean bool){
        if(bool){ return KeeperPosition.OPENED; }
        else{ return KeeperPosition.CLOSED; }
    }

    public TurnerPosition boolToTurnerPosition(Boolean bool){
        if(bool){ return TurnerPosition.NORMAL; }
        else{ return TurnerPosition.TURNED; }
    }

    public ExtruderPosition boolToExtruderPosition(Boolean bool){
        if(bool){ return ExtruderPosition.EXTRUDED; }
        else{ return ExtruderPosition.NOT_EXTRUDED; }
    }

    public boolean boolD(DcMotorSimple.Direction direction){
        if(direction == DcMotorSimple.Direction.FORWARD){ return true; }
        else{ return false; }
    }

    public boolean finish() {
        return Arrays.stream(allFunctions).allMatch(Standart::finish);
    }

}