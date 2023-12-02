
public class ExLeavesOverLapping extends Exception {
    private Leave overLap;
    public ExLeavesOverLapping(Leave l) { 
        super("Leave overlapped: The leave period "+ l +" is found!");
        overLap = l;
         
    } 
    public ExLeavesOverLapping(String message){ super(message); }
}
