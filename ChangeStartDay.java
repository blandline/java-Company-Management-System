public class ChangeStartDay extends RecordedCommand{
    private Day s;
    String newDate;
    
    

     
    @Override
    public void execute(String[] cmdParts){
        
        try {
            if (cmdParts.length<3){
                throw new ExInsufficientArguments();
            } 
            s = SystemDate.getInstance().clone();
            newDate = cmdParts[1];
            SystemDate.getInstance().set(cmdParts[1]);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientArguments e) {
            
            System.out.println(e.getMessage());;
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
