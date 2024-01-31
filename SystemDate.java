public class SystemDate extends Day{
    private static SystemDate instance;

    //this is also a singleton class 

    private SystemDate(String sDay){
         super(sDay); //creating the system date object from the condstructor of the super cclasses constructor(Day class)
    }

    public static void createTheInstance(String sDay){
        if(instance==null){
            instance = new SystemDate(sDay);

        }
        else{
            System.out.println("Cannot create one more instance.");
        }
    }
    public static SystemDate getInstance(){
        return instance;
    }
    
}
