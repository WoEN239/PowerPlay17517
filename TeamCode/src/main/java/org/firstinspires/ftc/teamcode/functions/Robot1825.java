package org.firstinspires.ftc.teamcode.functions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.functions.mobility.GetFragment;
import org.firstinspires.ftc.teamcode.functions.mobility.Move;

public class Robot1825 {
    public LinearOpMode linearOpMode;
    public Robot1825(LinearOpMode linearOpMode) {this.linearOpMode = linearOpMode;}

    private final GetFragment getFragment = new GetFragment(this);
    private final Move move = new Move(this);
    private final org.firstinspires.ftc.teamcode.functions.measurements.Position position = new  org.firstinspires.ftc.teamcode.functions.measurements.Position(this);


    public final Standart[] allfunctions = new Standart[]{
            move, position,
    };

}