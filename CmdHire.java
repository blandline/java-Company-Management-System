
public class CmdHire extends RecordedCommand{
    private Employee e;
    private int annualLeaves;
    

     
    @Override
    public void execute(String[] cmdParts){
        Company company = Company.getInstance();
        annualLeaves = Integer.parseInt(cmdParts[2]);

        company.createEmployee(cmdParts[1], annualLeaves);
        e = company.findEmployee(cmdParts[1]);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");

        
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeEmployee(e);
        super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        company.addEmployee(e);
        super.addUndoCommand(this);
    }
}
