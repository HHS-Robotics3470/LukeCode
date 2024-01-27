package org.firstinspires.ftc.teamcode.opmode.auto;

import static org.firstinspires.ftc.teamcode.robot.commands.tilt.TiltGoToPosition.TELEOP_DEPOSIT;
<<<<<<< Updated upstream
=======
import static org.firstinspires.ftc.teamcode.robot.commands.tilt.TiltGoToPosition.TELEOP_INTAKE;
>>>>>>> Stashed changes

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.opmode.auto.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.robot.commands.claw.ClawCloseCommand;
import org.firstinspires.ftc.teamcode.robot.commands.claw.ClawOpenCommand;
import org.firstinspires.ftc.teamcode.robot.commands.tilt.TiltGoToPosition;
import org.firstinspires.ftc.teamcode.robot.commands.wrist.WristDeposit;
import org.firstinspires.ftc.teamcode.robot.commands.wrist.WristIntake;
import org.firstinspires.ftc.teamcode.robot.commands.wrist.WristStow;
import org.firstinspires.ftc.teamcode.robot.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.robot.subsystems.TiltSubsystem;
import org.firstinspires.ftc.teamcode.robot.subsystems.WristSubsystem;
import org.firstinspires.ftc.teamcode.vision.TeamElementPipeline;
import org.firstinspires.ftc.teamcode.vision.Vision;

@Autonomous(group="auto", name="Auto_RED_Backboard_side")
public class AutoRedRight extends LinearOpMode
{
    private static final double TILE = 24.0;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        ClawSubsystem clawSubsystem = new ClawSubsystem(hardwareMap);
        TiltSubsystem tiltSubsystem = new TiltSubsystem(hardwareMap, telemetry);
        WristSubsystem wristSubsystem = new WristSubsystem(hardwareMap);

        Pose2d startPose = new Pose2d(TILE/2, -2.5*TILE, Math.toRadians(90));

        drive.setPoseEstimate(startPose);

        ParallelCommandGroup initiate = new ParallelCommandGroup(
                new ClawCloseCommand(clawSubsystem),
<<<<<<< Updated upstream
                new WristStow(wristSubsystem)
=======
                new WristStow(wristSubsystem),
                new TiltGoToPosition(tiltSubsystem, TiltGoToPosition.TELEOP_INTAKE)
>>>>>>> Stashed changes
        );
        SequentialCommandGroup place_pixel_and_stow = new SequentialCommandGroup(
                new TiltGoToPosition(tiltSubsystem, TiltGoToPosition.AUTO_STACK_INTAKE1),
                new WristIntake(wristSubsystem),
                new ClawOpenCommand(clawSubsystem, ClawOpenCommand.Side.RIGHT),
                new WristStow(wristSubsystem),
                new TiltGoToPosition(tiltSubsystem, TiltGoToPosition.TELEOP_INTAKE));
        SequentialCommandGroup deposit = new SequentialCommandGroup(
<<<<<<< Updated upstream
                new TiltGoToPosition(tiltSubsystem, TELEOP_DEPOSIT),
                new WristDeposit(wristSubsystem),
                new ClawOpenCommand(clawSubsystem, ClawOpenCommand.Side.RIGHT));
=======
                new TiltGoToPosition(tiltSubsystem, TiltGoToPosition.TELEOP_DEPOSIT),
                new WristDeposit(wristSubsystem),
                new ClawOpenCommand(clawSubsystem, ClawOpenCommand.Side.BOTH),
                new WristStow(wristSubsystem),
                new TiltGoToPosition(tiltSubsystem, TiltGoToPosition.TELEOP_INTAKE),
                new ClawCloseCommand(clawSubsystem));
>>>>>>> Stashed changes

        TrajectorySequence Center = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(TILE/2, -1.56*TILE))
                //.lineToConstantHeading(new Vector2d(TILE/2, -1.5*TILE))
                .turn(Math.toRadians(170))
                .addTemporalMarker(1.3, () -> {
                    telemetry.addData("RUNNING BEFORE", 0);
                    telemetry.update();
                    CommandScheduler.getInstance().schedule(place_pixel_and_stow);
                    CommandScheduler.getInstance().run();
                    telemetry.addData("RUNNING AFTER", 0);
                    telemetry.update();
                })
<<<<<<< Updated upstream
                .addTemporalMarker(2.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(3+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(4.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(5.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(7+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .waitSeconds(5)
                .turn(Math.toRadians(-170+90))
                .lineToConstantHeading(new Vector2d(2*TILE, -1.4*TILE))
                .addDisplacementMarker(()->{
                    CommandScheduler.getInstance().schedule(new WristIntake(wristSubsystem));
                    CommandScheduler.getInstance().run();
                    sleep(160);
                    requestOpModeStop();})
                .lineToConstantHeading(new Vector2d(1.2*TILE, -2.5*TILE))
=======
                .waitSeconds(5)
                .turn(Math.toRadians(-170+90-180))
                .addDisplacementMarker(()->{
                    CommandScheduler.getInstance().schedule(deposit);
                })
                .lineToConstantHeading(new Vector2d(2*TILE, -1.3*TILE))
                .waitSeconds(5)
                .lineToConstantHeading(new Vector2d(2*TILE, -2*TILE))
>>>>>>> Stashed changes

                /*.addTemporalMarker(5, () -> {
                    CommandScheduler.getInstance().schedule(deposit);
                })
                .waitSeconds(4)*/
                //.lineToConstantHeading(new Vector2d(2*TILE, -3*TILE))
                .build();
<<<<<<< Updated upstream
                /////////////////
=======
        /////////////////
>>>>>>> Stashed changes


        TrajectorySequence Left = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(TILE/2, -1.3*TILE))
                .turn(Math.toRadians(-90))
                .lineToConstantHeading(new Vector2d(TILE*0.44, -1.29*TILE))
<<<<<<< Updated upstream
                .addTemporalMarker(1.3, () -> {
=======
                .addTemporalMarker(1.2, () -> {
>>>>>>> Stashed changes
                    telemetry.addData("RUNNING BEFORE", 0);
                    telemetry.update();
                    CommandScheduler.getInstance().schedule(place_pixel_and_stow);
                    CommandScheduler.getInstance().run();
                    telemetry.addData("RUNNING AFTER", 0);
                    telemetry.update();
                })
<<<<<<< Updated upstream
                .addTemporalMarker(2.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(3+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(4.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(5.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(7+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .waitSeconds(6)
                .turn(Math.toRadians(-180))
                .lineToConstantHeading(new Vector2d(TILE*1.7, -1.8*TILE))
                .lineToConstantHeading(new Vector2d(2*TILE, -1.74*TILE))
                .addDisplacementMarker(()->{
                    CommandScheduler.getInstance().schedule(new WristIntake(wristSubsystem));
                    CommandScheduler.getInstance().run();
                    sleep(160);
                    requestOpModeStop();})
                .lineToConstantHeading(new Vector2d(1.2*TILE, -2.5*TILE))
=======
                .waitSeconds(5)
                //.turn(Math.toRadians(-180))
                .lineToConstantHeading(new Vector2d(TILE*1.7, -1.8*TILE))
                .lineToConstantHeading(new Vector2d(2*TILE, -1.74*TILE))
                .addDisplacementMarker(()->{
                    CommandScheduler.getInstance().schedule(deposit);
                })
                .waitSeconds(5)
                .lineToConstantHeading(new Vector2d(2*TILE, -2.0*TILE))
>>>>>>> Stashed changes
                .build();

        TrajectorySequence Right = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(TILE/2, -1.27*TILE))
                .turn(Math.toRadians(-90))
<<<<<<< Updated upstream
                .lineToConstantHeading(new Vector2d(TILE*1.2, -1.3*TILE))
                .addTemporalMarker(2.2+1, () -> {
=======
                .lineToConstantHeading(new Vector2d(TILE*1.4, -1.3*TILE))
                .addTemporalMarker(2.2, () -> {
>>>>>>> Stashed changes
                    telemetry.addData("RUNNING BEFORE", 0);
                    telemetry.update();
                    CommandScheduler.getInstance().schedule(place_pixel_and_stow);
                    CommandScheduler.getInstance().run();
                    telemetry.addData("RUNNING AFTER", 0);
                    telemetry.update();
                })
<<<<<<< Updated upstream
                .addTemporalMarker(2.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(3+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(4.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(5.5+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .addTemporalMarker(7+1, () -> {
                    CommandScheduler.getInstance().run();
                })
                .waitSeconds(6)
                .turn(Math.toRadians(-180))
                .lineToConstantHeading(new Vector2d(TILE*1.7, -1.8*TILE))
                .lineToConstantHeading(new Vector2d(2*TILE, -1.74*TILE))
                .addDisplacementMarker(()->{
                    CommandScheduler.getInstance().schedule(new WristIntake(wristSubsystem));
                    CommandScheduler.getInstance().run();
                    sleep(160);
                    requestOpModeStop();})
                .lineToConstantHeading(new Vector2d(1.2*TILE, -2.5*TILE))
=======
                .waitSeconds(5)
                //.turn(Math.toRadians(-180))
                .lineToConstantHeading(new Vector2d(TILE*1.7, -1.8*TILE))
                .lineToConstantHeading(new Vector2d(2*TILE, -1.74*TILE))
                .addDisplacementMarker(()->{
                    //CommandScheduler.getInstance().schedule(new WristIntake(wristSubsystem));
                    CommandScheduler.getInstance().run();
                    CommandScheduler.getInstance().schedule(deposit);
                })
                .waitSeconds(5)
                .lineToConstantHeading(new Vector2d(1.2*TILE, -2*TILE))
>>>>>>> Stashed changes
                .build();

        TeamElementPipeline.MarkerPosistion markerPosistion;
        Vision.startStreaming(hardwareMap, telemetry);
        markerPosistion = TeamElementPipeline.MarkerPosistion.CENTER;
        CommandScheduler.getInstance().schedule(initiate);
        CommandScheduler.getInstance().run();
        while(opModeInInit()) {
            markerPosistion = Vision.determineMarkerPosistion();
        }
        ////////////////////////////////////////////


        waitForStart();
        Vision.webcam.stopStreaming();


<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        switch (markerPosistion) {
            case CENTER:
            case UNKNOWN:
                drive.followTrajectorySequenceAsync((Center));
                break;
            case RIGHT:
                drive.followTrajectorySequenceAsync(Right);
                break;
            case LEFT:
                drive.followTrajectorySequenceAsync(Left);
                break;
        }
        /*telemetry.addData("DONE 2",0);
        telemetry.update();*/
        while(!isStopRequested()) {
            drive.update();
            CommandScheduler.getInstance().run();
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        }
    }

}
