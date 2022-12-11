package org.firstinspires.ftc.teamcode.functions.mobility;
import android.os.Build;

import org.firstinspires.ftc.teamcode.functions.Robot1825;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.io.File;


public class FlowK{
    Robot1825 robot;
    File k = new File("C:\\Users\\user\\AndroidStudioProjects\\Powerplay17517\\TeamCode\\src\\main\\java\\org\\firstinspires\\ftc\\teamcode\\functions\\mobility\\Koeff'");
    String way = "C:\\Users\\user\\AndroidStudioProjects\\Powerplay17517\\TeamCode\\src\\main\\java\\org\\firstinspires\\ftc\\teamcode\\functions\\mobility\\Koeff'";
    public FlowK(Robot1825 robot){ this.robot = robot; }

    public void activity() throws IOException {
        if(k.exists()){
            k.setReadable(true);
            k.setWritable(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { Files.write(Paths.get(way), (Double.toString(robot.position.globalX) + " ").getBytes(), StandardOpenOption.APPEND); }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { Files.write(Paths.get(way), (Double.toString(robot.position.globalY) + " ").getBytes(), StandardOpenOption.APPEND); }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { Files.write(Paths.get(way), (Double.toString(robot.position.angle) + " ").getBytes(), StandardOpenOption.APPEND); }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { Files.write(Paths.get(way), ("\n").getBytes(), StandardOpenOption.APPEND); }
        }
    }
}

