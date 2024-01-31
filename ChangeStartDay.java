public class ChangeStartDay extends RecordedCommand{
    private Day s;
    String newDate;
    
    

     
    @Override
    public void execute(String[] cmdParts){
        
        try {
            Company company = Company.getInstance();
            if (cmdParts.length<2){
                throw new ExInsufficientArguments();
            } 
            s = SystemDate.getInstance().clone();
            
            newDate = cmdParts[1];
            SystemDate.getInstance().set(cmdParts[1]);

            company.adjustLeaves();
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientArguments e) {
            
            System.out.println(e.getMessage());
        }

        
    }

    @Override
    public void undoMe(){
        SystemDate.getInstance().set(s.toString());
        super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        SystemDate.getInstance().set(newDate);
        super.addUndoCommand(this);
    }
}
