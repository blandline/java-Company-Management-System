

    
public class CmdCreateProject extends RecordedCommand{
    private Project p;
    private String projName;
    private int projTime;
    

     
    @Override
    public void execute(String[] cmdParts){
        Company company = Company.getInstance();
        projName = cmdParts[1];
        projTime = Integer.parseInt(cmdParts[3]);
        company.createProject(cmdParts[1], cmdParts[2],projTime);
        // e = company.findEmployee(cmdParts[1]);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");

        
    }

    @Override
    public void undoMe(){
        // Company company = Company.getInstance();
        // company.removeEmployee(e);
        // super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        // Company company = Company.getInstance();
        // company.addEmployee(e);
        // super.addUndoCommand(this);
    }
}


