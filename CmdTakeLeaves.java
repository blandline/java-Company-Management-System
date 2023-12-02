
public class CmdTakeLeaves extends RecordedCommand{
   private Employee employee;
   private String employeeName;
   private Leave l;

     
    @Override
    public void execute(String[] cmdParts){

            try {
                if (cmdParts.length<4){
                    throw new ExInsufficientArguments();
                }
                Company company = Company.getInstance();
                employeeName = cmdParts[1];
                l = new Leave(cmdParts[2], cmdParts[3]);
                employee = company.findEmployee(employeeName);
                company.takeLeaves(employeeName,l);
                
                addUndoCommand(this);
                clearRedoList();
                System.out.println("Done.  "+employeeName+"'s remaining annual leave: "+employee.getAnnualLeaves()+" days");
            } catch (ExInsufficientArguments e) {
                System.out.println(e.getMessage());
            } catch (ExInsufficientLeaves e) {
                System.out.println(e.getMessage());
            } catch (ExLeavesOverLapping e) {
                System.out.println(e.getMessage());
            }
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeLeaves(employeeName,l);
        super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        try {
            company.takeLeaves(employeeName,l);
        } catch (ExInsufficientLeaves e) {
            System.out.println(e.getMessage());
        } catch (ExLeavesOverLapping e) {
            System.out.println(e.getMessage());
        }
        super.addUndoCommand(this);
    }
}
