package org.firstinspires.ftc.teamcode.functions.openCV;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.functions.Robot1825;
import org.firstinspires.ftc.teamcode.opmodes.opModeEnums.ConePosition;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;


public class AprilDetection {
    public final Robot1825 robot;
    public AprilDetection(Robot1825 robot) { this.robot = robot; }
    OpenCvCamera camera;
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;
    double tagSize = 0.166;
    public Pipeline pipeline = new Pipeline(tagSize, fx, fy, cx, cy);

    static final double FEET_PER_METER = 3.28084;
    public static double entreDistance = 13.5;
    public static double centreOfCone = 0;
    public static double timePosition = 0;
    final float DECIMATION_HIGH = 3;
    final float DECIMATION_LOW = 2;
    final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;
    Pipeline aprilPipeline;
    int numFramesWithoutDetection = 0;
    private final SensorDeterminant<ConePosition> freightPositionTimedSensorQuery =
            new SensorDeterminant<>(this::forceGetPosition, 1.5);

    public void start() {
        camera = OpenCvCameraFactory.getInstance()
                .createWebcam(robot.linearOpMode.hardwareMap.get(WebcamName.class, "Webcam 1"));
        aprilPipeline = new Pipeline(tagSize, fx, fy, cx, cy);
        camera.setPipeline(aprilPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
                //robot.telemetry.dashboardTelemetry.getInstance().startCameraStream(camera, 5);
            }

            @Override
            public void onError(int errorCode) { }
        });
    }

    public ConePosition forceGetPosition() {
        ArrayList<AprilTagDetection> detections = aprilPipeline.getDetectionsUpdate();
        if (detections != null) {
            if (detections.size() == 0) {
                numFramesWithoutDetection++;
                if (numFramesWithoutDetection >= THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION) {
                    aprilPipeline.setDecimation(DECIMATION_LOW);
                }
                return ConePosition.RIGHT;
            } else {
                numFramesWithoutDetection = 0;
                if (detections.get(0).pose.z < THRESHOLD_HIGH_DECIMATION_RANGE_METERS) {
                    aprilPipeline.setDecimation(DECIMATION_HIGH);
                }
                timePosition = detections.get(0).pose.x * FEET_PER_METER * detections.get(0).pose.z * FEET_PER_METER;
                timePosition = timePosition + centreOfCone;
            }
        } else {
            return ConePosition.RIGHT;
        }
        if (timePosition < -entreDistance) return ConePosition.LEFT;
        if (timePosition >= -entreDistance && timePosition < entreDistance) return ConePosition.CENTER;
        return ConePosition.RIGHT;
    }

    public ConePosition getPosition() {
        return freightPositionTimedSensorQuery.getValue();
    }

    public ConePosition stopCamera() {
        ConePosition value = forceGetPosition();
        //FtcDashboard.getInstance().stopCameraStream();
        camera.stopStreaming();
        camera.closeCameraDeviceAsync(()->{});
        return value;
    }

    @Config
    public static class OpenCVConfig {
        public static boolean doCameraStream = false;
    }

}
