package frc.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class FourCargoAuto extends SequentialCommandGroup {
    public FourCargoAuto(DriveSubsystem drive, IntakeSubsystem intake, ConveyorSubsystem conveyor, ShooterSubsystem shooter) {
        addCommands(
            // Drive forward the specified distance
            new ParallelCommandGroup(new ShooterCommand(shooter, conveyor).withTimeout(1.5)),
            new ParallelCommandGroup(new DriveCommand(drive, 4,  1, 0, 0), new IntakeCommand(intake)),
            new DriveCommandBack(drive, 4, 1, 0, 0),
            new ParallelCommandGroup(new ShooterCommand(shooter, conveyor).withTimeout(1.5)));
      }
}
