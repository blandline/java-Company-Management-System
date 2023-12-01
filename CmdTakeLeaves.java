
public class CmdTakeLeaves extends RecordedCommand{
//    private Employee e;
   private String employeeName;
   private Leave l;

     
    @Override
    public void execute(String[] cmdParts){
        Company company = Company.getInstance();
            employeeName = cmdParts[1];
            l = new Leave(cmdParts[2], cmdParts[3]) ;
            company.takeLeaves(employeeName,l);
            
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
    }

    @Override
    public void undoMe(){
        // Company company = Company.getInstance();
        // company.removeAssignment(a);
        // super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        // Company company = Company.getInstance();
        // company.assignProject(a);
        // super.addUndoCommand(this);
    }
}
