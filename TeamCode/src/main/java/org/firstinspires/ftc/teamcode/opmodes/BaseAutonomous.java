package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.opmodes.opModeEnums.Alliance;
import org.firstinspires.ftc.teamcode.opmodes.opModeEnums.StartPosition;

public abstract class BaseAutonomous extends BaseOpMode {

    protected Alliance alliance = Alliance.BLUE;
    protected StartPosition startPosition = StartPosition.TO_CENTER;

    protected Runnable[] firstPosition() {
        return new Runnable[0];
    }

    protected Runnable[] secondPosition() {
        return new Runnable[0];
    }

    protected Runnable[] thirdPosition() { return new Runnable[0]; }

    @Override
    public void startAction(){
        robot.ControlMode = Robot1825.Mode.AUTONOMOUS;
        super.startAction();
    }

    @Override
    public void mainAction(){

        autoAction(firstPosition());
        super.mainAction();
    }

    public final void autoAction(Runnable[] runnables) {
        autoAction(runnables, 300);
    }

    public final void autoAction(Runnable[] tasks, double timeoutSeconds) {
        for (Runnable action : tasks) {
            ElapsedTime elapsedTime = new ElapsedTime();
            if (opModeIsActive()) action.run();
            while (!robot.finish() && opModeIsActive() && elapsedTime.seconds() < timeoutSeconds) robot.activity();
        }
    }
}
