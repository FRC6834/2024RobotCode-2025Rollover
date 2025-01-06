// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.commands.FourCargoAuto;
import frc.commands.SimpleAuto;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  private final ClimberSubsystem m_ClimberSubsystem = new ClimberSubsystem();
  private final ConveyorSubsystem m_ConveyorSubsystem = new ConveyorSubsystem();


  // The driver's controller
  XboxController controller1 = new XboxController(OIConstants.kDriverControllerPort);
  CommandXboxController commandController1= new CommandXboxController(OIConstants.kDriverControllerPort); //Required for commands that use Triggers

  /*
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(controller1.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(controller1.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(controller1.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            m_robotDrive));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {

    //Brakes for swerve
    new JoystickButton(controller1, Button.kX.value)
      .whileTrue(new RunCommand(() -> m_robotDrive.setX(),m_robotDrive));
    
    //Runs intake + Conveyor - A Button
    new JoystickButton(controller1, Button.kA.value)
      .whileTrue(new RunCommand(() -> m_IntakeSubsystem.startMotor(), m_IntakeSubsystem))
      .whileTrue(new RunCommand(() -> m_ConveyorSubsystem.forwardMotor(), m_ConveyorSubsystem))
      .whileFalse(new RunCommand(() -> m_IntakeSubsystem.stopMotor(), m_IntakeSubsystem))
      .whileFalse(new RunCommand(() -> m_ConveyorSubsystem.stopMotor(), m_ConveyorSubsystem));
    
    //Runs Conveyor in Reverse - B button
    new JoystickButton(controller1, Button.kB.value)
      .whileTrue(new RunCommand(() -> m_ConveyorSubsystem.backwardsMotor(), m_ConveyorSubsystem))
      .whileFalse(new RunCommand(() -> m_ConveyorSubsystem.stopMotor(), m_ConveyorSubsystem));

    //Runs shooter + Conveyor - Y button full speed
    new JoystickButton(controller1, Button.kY.value)
      .whileTrue(new RunCommand(() -> m_ShooterSubsystem.startMotor(), m_ShooterSubsystem))
      .whileTrue(new RunCommand(() -> m_ConveyorSubsystem.forwardMotor(), m_ConveyorSubsystem))
      .whileFalse(new RunCommand(() -> m_ConveyorSubsystem.stopMotor(), m_ConveyorSubsystem))
      .whileFalse(new RunCommand(() -> m_ShooterSubsystem.stopMotor(), m_ShooterSubsystem));

    //left climber goes down - left bumper
    new JoystickButton(controller1, Button.kLeftBumper.value) 
     .whileTrue(new RunCommand(() -> m_ClimberSubsystem.climberLeftDown(), m_ClimberSubsystem))
     .whileFalse(new RunCommand(() -> m_ClimberSubsystem.stopLeftMotor(), m_ClimberSubsystem));

    //right climber goes down - right bumper
    new JoystickButton(controller1, Button.kRightBumper.value)
     .whileTrue(new RunCommand(() -> m_ClimberSubsystem.climberRightDown(), m_ClimberSubsystem))
     .whileFalse(new RunCommand(() -> m_ClimberSubsystem.stopRightMotor(), m_ClimberSubsystem));

    //Right and Left Climbers go up using Triggers
    commandController1.leftTrigger().whileTrue(new RunCommand(() -> m_ClimberSubsystem.climberLeftUp(), m_ClimberSubsystem));
    commandController1.leftTrigger().whileFalse(new RunCommand(() -> m_ClimberSubsystem.stopLeftMotor(), m_ClimberSubsystem));
    commandController1.rightTrigger().whileTrue(new RunCommand(() -> m_ClimberSubsystem.climberRightUp(), m_ClimberSubsystem));
    commandController1.rightTrigger().whileFalse(new RunCommand(() -> m_ClimberSubsystem.stopRightMotor(), m_ClimberSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(){
    // Run path following command, then stop at the end.
    return new FourCargoAuto(m_robotDrive, m_IntakeSubsystem, m_ConveyorSubsystem, m_ShooterSubsystem);
    //return new SimpleAuto(m_robotDrive, m_IntakeSubsystem, m_ConveyorSubsystem, m_ShooterSubsystem);
  }
}