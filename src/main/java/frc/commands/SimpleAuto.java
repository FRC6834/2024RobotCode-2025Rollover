package frc.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class SimpleAuto extends SequentialCommandGroup{
  //these bools are for whether or not the robot will go for a second note or if it will just leave the starting area after shooting the preloaded note
  final boolean SECOND_NOTE = true; //if false robot will just go for a preload note and leave
  final boolean FIRST_NOTE = true; //if false robot will just go for leave points

  public SimpleAuto(DriveSubsystem drive, IntakeSubsystem intake, ConveyorSubsystem conveyor, ShooterSubsystem shooter){
    
    if(FIRST_NOTE){
      if(SECOND_NOTE){ //robot goes for 2 notes (FIRST_NOTE&&SECOND_NOTE)
        addCommands(

          //shoots preloaded note
          new ParallelCommandGroup(new ShooterCommand(shooter, conveyor)).withTimeout(1.5),

          //collects second note; drives backwards and uses intake at the same time
          new ParallelCommandGroup(new DriveCommand(drive, -1.46,  1, 0, 0), new IntakeCommand(intake)).withTimeout(1.5),

          //drive forward again and then shoots the collected note
          new SequentialCommandGroup(new DriveCommand(drive, 1.46, 1, 0, 0), new ShooterCommand(shooter, conveyor)).withTimeout(1.5),

          //leaves starting zone
          new SequentialCommandGroup(new DriveCommand(drive, 2.9, 0, -1, 0).withTimeout(1.5), 
          new DriveCommand(drive, 1.5, -1, 0, 0)).withTimeout(1.5)  
          //^^these two are a part of the same group i just set them on different lines for readability

        );


      }else{ //ROBOT WILL NOT GO AFTER 2ND NOTE (SECOND_NOTE = false)
        addCommands(

          //shoots preloaded note
          new ParallelCommandGroup(new ShooterCommand(shooter, conveyor)).withTimeout(1.5),

          //leaves starting zone
          new SequentialCommandGroup(new DriveCommand(drive, 2.9, 0, -1, 0).withTimeout(1.5), 
          new DriveCommand(drive, 1.5, -1, 0, 0)).withTimeout(1.5)  
          //^^these two are a part of the same sequential command group i just set them on different lines for readability
        );
      } 


    }else{ //FIRST_NOTE = false
      new DriveCommand(drive, 1.5, 1, 0, 0).withTimeout(1.5); //WILL NOT SHOOT NOTES; WILL ONLY DRIVE FORWARD (intended for use to gain leave points)
    }
  }
}
