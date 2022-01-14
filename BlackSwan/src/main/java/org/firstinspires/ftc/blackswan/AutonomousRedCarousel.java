package org.firstinspires.ftc.blackswan;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous

public class AutonomousRedCarousel extends LinearOpMode {
    Robot robot;
    int cubethingwithlevels = 0;

    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap,telemetry,this);
        waitForStart();
        robot.liftForMovement();
        // turns to rotate carousel and rotates carousel
        robot.left(.5,.5);
        robot.turnLeft(45,.5);
        robot.left(.5,.5);
        robot.back(.47,.5);
        robot.redCarousel(4000);
        //check the code underneath, wrong places, too jerky
        robot.forward(.47,.5);
        robot.right(.5,.5);
        robot.turnLeft(25,.5);
        robot.pause(10);
        robot.forward(.5,.5);
        robot.pause(10);
        robot.right(.5,.5);
        robot.pause(10);
        robot.forward(.35,.5);
        robot.pause(10);
        robot.turnLeft(.15,.5);
        //robot.pause(1000); //DELETE AFTER TESTING
        robot.right(.5, .5);
        if (robot.colorSensorRight.green() > 100) {
            cubethingwithlevels = 1;

        }
        robot.right(.80,.5);
        //robot.pause(1000); //DELETE AFTER TESTING
        robot.forward(.25, .5);
        if (robot.colorSensorRight.green() > 100) {
            //cubethingwithlevels = 2;
        }

        if (cubethingwithlevels == 0 ){}
        cubethingwithlevels = 3;

        telemetry.addData("position: ", cubethingwithlevels); //testing
        telemetry.update(); //testing

        //robot.pause(1000); //DELETE AFTER TESTING
        robot.right(1.6,.5);
        robot.armThing(cubethingwithlevels);
        robot.forward(1, .5);
        robot.eject();

        robot.back(.75,.5);
        robot.armThing(1);
        robot.left(4.25,.75);
        robot.forward(.8,.5);


    }
}
