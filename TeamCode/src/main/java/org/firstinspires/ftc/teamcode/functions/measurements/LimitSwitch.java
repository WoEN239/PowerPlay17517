package org.firstinspires.ftc.teamcode.functions.measurements;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.functions.Standard;

public class LimitSwitch implements Standard {
    public Robot1825 robot;
    public LimitSwitch(Robot1825 robot){this.robot = robot;}

    private Boolean[] limitSwitch = new Boolean[6];
    private String[] names = {"limit1", "limit2", "limit3", "limit4", "limit5", "limit6" };
    private DigitalChannel[] digitalChannels = new DigitalChannel[6];

    public void start(){
        for(int i = 0; i < 6; i++) {
            digitalChannels[i] = robot.linearOpMode.hardwareMap.get(DigitalChannel .class, names[i]);
            digitalChannels[i].setMode(DigitalChannel.Mode.INPUT);
        }
    };

    public void activity(){ for(int i = 0; i < 6; i++) { limitSwitch[i] = digitalChannels[i].getState(); } }

    public boolean finish(){ return true; };

    public boolean getLimit(int number){ return limitSwitch[number]; }
}
