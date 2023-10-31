// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriverConstants;
import frc.robot.commands.CommandFactory;
import frc.robot.subsystems.drivetrain.Drivetrain;


public class Robot extends TimedRobot {

  //Subsystems
  private final Drivetrain drivetrain = new Drivetrain();

  //Command Factory
  private final CommandFactory commandFactory = new CommandFactory(drivetrain);

  //Controllers
  private final CommandXboxController driveController = new CommandXboxController(DriverConstants.kDriverControllerPort);

  /**
   * This function is run when the robot is first started up and should be used for any initialization code.
   */
  @Override
  public void robotInit(){

    //Set the default command for the drivetrain to allow for teleop control
    drivetrain.setDefaultCommand(
      commandFactory.TeleopSwerve(
        () ->- driveController.getLeftY(),
        () -> driveController.getLeftX(),
        () -> -driveController.getRightX()
      )
    );

  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    drivetrain.updatePoseEstimator();

  }

  @Override
  public void simulationPeriodic() {
    drivetrain.simulate();
  }

  @Override
  public void autonomousInit(){
    // if(autonomousCommand != null){
    //   CommandScheduler.getInstance().schedule(autonomousCommand);
    // }
  }

  @Override
  public void teleopInit() {
    // if (autonomousCommand != null) {
    //   autonomousCommand.cancel();
    // }
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

}
