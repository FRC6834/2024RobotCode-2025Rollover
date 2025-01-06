package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;

public class ShooterCommand extends Command{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ShooterSubsystem m_ShooterSubsystem;
    private final ConveyorSubsystem m_ConveyorSubsystem;

    public ShooterCommand(ShooterSubsystem subsystem, ConveyorSubsystem subsystem2){
        m_ShooterSubsystem = subsystem;
        m_ConveyorSubsystem = subsystem2;
    }
    //called when the command is initialized
    @Override
    public void initialize(){
      m_ShooterSubsystem.startMotor();
      m_ConveyorSubsystem.forwardMotor();
    }
    //called every time the command is scheduled
    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
      m_ShooterSubsystem.stopMotor();
      m_ConveyorSubsystem.stopMotor();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
