package org.firstinspires.ftc.Qualifier1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwarePushBot
{
    /* Public OpMode members. */
    public DcMotor  topleftMotor   = null;
    public DcMotor  toprightMotor  = null;
    public DcMotor  botleftMotor   = null;
    public DcMotor  botrightMotor  = null;


    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwarePushBot()
    {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        topleftMotor   = hwMap.dcMotor.get("motorFL");
        toprightMotor  = hwMap.dcMotor.get("motorFR");
        botleftMotor =   hwMap.dcMotor.get("motorBL");
        botrightMotor = hwMap.dcMotor.get("motorBR");
//        armMotor    = hwMap.dcMotor.get("left_arm");
        topleftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        toprightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        botleftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        botrightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        // Set all motors to zero power
        topleftMotor.setPower(0);
        toprightMotor.setPower(0);
        botleftMotor.setPower(0);
        botrightMotor.setPower(0);
  //      armMotor.setPower(0);
        topleftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        toprightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        botleftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        botrightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        topleftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        toprightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        botleftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        botrightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

