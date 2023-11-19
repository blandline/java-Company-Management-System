
public class CmdAssignProject extends RecordedCommand{
    private Team t;
    private Project p;
    

     
    @Override
    public void execute(String[] cmdParts){
        Company company = Company.getInstance();
        p = company.findProject(cmdParts[1]);
        t = company.findTeam(cmdParts[2]);
        t.setProj(p);
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
