package org.firstinspires.ftc.teamcode.opmode.teleop

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.arcrobotics.ftclib.gamepad.GamepadEx
import com.arcrobotics.ftclib.gamepad.GamepadKeys
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.opmode.OpModeBase

@Suppress("UNUSED")
@TeleOp(name="CDTeleop")
class CDTeleop : OpModeBase() {
    private var driveSpeedScale = DRIVE_SPEED_NORMAL

    override fun initialize() {
        initHardware()
        initializeDriverGamepad(driverGamepad)
        initializeCoDriverGamepad(accessoryGamepad)
    }

    override fun run() {
        super.run()

        mecanumDrive.setDrivePower(
            Pose2d(
                driverGamepad.leftY * driveSpeedScale,
                -driverGamepad.leftX * driveSpeedScale,
                -driverGamepad.rightX * driveSpeedScale
            )
        )

        mecanumDrive.updatePoseEstimate()

        // Driver controls
        val driverLeftTriggerValue = driverGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)
        val driverRightTriggerValue = driverGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)

        if (hardware.gripperHomeSensor?.isPressed == true) {
            hardware.gripperServo?.position = 0.5
        } else if (driverLeftTriggerValue > VARIABLE_INPUT_DEAD_ZONE) {
            hardware.gripperServo?.position = 0.25
        } else if (driverRightTriggerValue > VARIABLE_INPUT_DEAD_ZONE) {
            hardware.gripperServo?.position = 0.75
        } else {
            hardware.gripperServo?.position = 0.5
        }

        // Accessory controls
        val accessoryLeftTriggerValue = accessoryGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)
        val accessoryRightTriggerValue = accessoryGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)

        if (accessoryLeftTriggerValue > VARIABLE_INPUT_DEAD_ZONE) {
            hardware.intakeWheelServo?.power = -1.0
            telemetry.addLine("intake servo present? ${hardware.intakeWheelServo != null}")
            telemetry.addLine("left trigger $accessoryLeftTriggerValue")
        } else if (accessoryRightTriggerValue > VARIABLE_INPUT_DEAD_ZONE) {
            hardware.intakeWheelServo?.power = 1.0
            telemetry.addLine("intake servo present? ${hardware.intakeWheelServo != null}")
            telemetry.addLine("left trigger $accessoryRightTriggerValue")
        } else {
            hardware.intakeWheelServo?.power = 0.0
        }

        if (accessoryGamepad.rightY > VARIABLE_INPUT_DEAD_ZONE || accessoryGamepad.rightY < -VARIABLE_INPUT_DEAD_ZONE) {
            // TODO: Fix multiplier later
            viperArmSubsystem.setExtensionMotorGroupPower(accessoryGamepad.rightY * 1.0)
        } else {
            viperArmSubsystem.setExtensionMotorGroupPower(0.0)
        }

        if (accessoryGamepad.leftY > VARIABLE_INPUT_DEAD_ZONE || accessoryGamepad.leftY < -VARIABLE_INPUT_DEAD_ZONE) {
            // TODO: Fix multiplier later
            viperArmSubsystem.setRotationMotorGroupPower(accessoryGamepad.leftY * 0.75)
        } else {
            viperArmSubsystem.setRotationMotorGroupPower(0.0)
        }

        writeTelemetry()
    }

    private fun initializeDriverGamepad(gamepad: GamepadEx) {
        val speedFastButton = gamepad.getGamepadButton(GamepadKeys.Button.Y)
        val speedSlowButton = gamepad.getGamepadButton(GamepadKeys.Button.A)
        val normalDriveButton = gamepad.getGamepadButton(GamepadKeys.Button.B)
//        val gripperPickupButton = gamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
//        val gripperLowDeliveryButton = gamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
//        val gripperHighDeliveryButton = gamepad.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)

        speedFastButton.whenPressed(Runnable { driveSpeedScale = DRIVE_SPEED_FAST })
        speedSlowButton.whenPressed(Runnable { driveSpeedScale = DRIVE_SPEED_SLOW })
        normalDriveButton.whenPressed(Runnable { driveSpeedScale = DRIVE_SPEED_NORMAL})
//        lowChamberHeightButton.whenPressed(Runnable { gripperSubsystem.setLowChamberHeight() })
    }

    private fun initializeCoDriverGamepad(gamepad: GamepadEx) {
//        val wristForwardButton = gamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP)
//        val wristReverseButton = gamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
//        val wristLeftButton = gamepad.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
//        val wristRightButton = gamepad.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
//
//        wristForwardButton.whenPressed(Runnable { activeIntakeSubsystem.rotateHome() })
//        wristReverseButton.whenPressed(Runnable { activeIntakeSubsystem.rotateToBasket() })
//        wristLeftButton.whenPressed(Runnable { activeIntakeSubsystem.rotateLeft() })
//        wristRightButton.whenPressed(Runnable { activeIntakeSubsystem.rotateRight() })
    }

    private fun writeTelemetry() {
        telemetry.addLine()
        telemetry.addLine("speed mult: $driveSpeedScale")
        telemetry.addLine()

        if (hardware.viperExtensionMotorLeft == null) {
            telemetry.addLine("extensionLeft is null")
        }

        if (hardware.viperExtensionMotorRight == null) {
            telemetry.addLine("extensionRight is null")
        }

        if (hardware.viperRotationMotorLeft == null) {
            telemetry.addLine("rotationLeft is null")
        }

        if (hardware.viperRotationMotorRight == null) {
            telemetry.addLine("rotationRight is null")
        }

//        telemetry.addLine("viperExtensionPos: ${viperArmSubsystem.getExtensionMotorGroupPosition()}")
//        telemetry.addLine("viperRotationPos: ${viperArmSubsystem.getRotationMotorGroupPosition()}")
//        telemetry.addLine()

        // TODO: Position is meaningless for continuous servos, we need some way to measure position without an encoder in the servo

//        hardware.suspendMotor?.let {
//            telemetry.addLine("suspend motor pos: ${it.currentPosition}")
//        } ?: telemetry.addLine("[WARNING] Suspend motor not found")
//
//        hardware.viperAngleServo?.let {
//            telemetry.addLine("viper angle pos: ${it.position}")
//        } ?: telemetry.addLine("[WARNING] Viper angle servo not found")
//
//        hardware.viperPot?.let {
//            telemetry.addLine("pot voltage: ${it.voltage}")
//        } ?: telemetry.addLine("[WARNING] Viper potentiometer not found")
//
//        hardware.viperMotor?.let {
//            telemetry.addLine("viper motor pos: ${it.currentPosition}")
//        } ?: telemetry.addLine("[WARNING] Viper motor not found")
//
//        hardware.droneServo?.let {
//            telemetry.addLine("drone pos: ${it.position}")
//        } ?: telemetry.addLine("[WARNING] Drone servo not found")
//
//        hardware.suspendServo?.let {
//            telemetry.addLine("suspendServo pos: ${it.position}")
//        } ?: telemetry.addLine("[WARNING] suspendServo not found")

        telemetry.update()
    }

    companion object {
        private const val VARIABLE_INPUT_DEAD_ZONE = 0.05

        private const val DRIVE_SPEED_FAST = 0.9
        private const val DRIVE_SPEED_NORMAL = 0.75
        private const val DRIVE_SPEED_SLOW = 0.5
    }
}