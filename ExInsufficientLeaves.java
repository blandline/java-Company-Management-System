
public class ExInsufficientLeaves extends Exception{
    public ExInsufficientLeaves(int leaveBalance) { super("Insufficient balance of annual leave. "+ leaveBalance +" days left only!"); } 
    public ExInsufficientLeaves(String message){ super(message); }
}
