public class ExEmployeeExists extends Exception{

    public ExEmployeeExists(){ super("Employee already Exists."); } 
    public ExEmployeeExists(String message){ super(message); }
}
