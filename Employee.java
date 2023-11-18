import java.util.ArrayList;

public class Employee implements Comparable<Employee>{
    private String name;
    private int annualLeaves;

    public Employee(String n,int al){
        name = n;
        annualLeaves = al;

    }

    public String getName(){
        return name;
    }

    public static Employee searchEmployee(ArrayList<Employee> list,String nameToSearch){
        for(Employee e: list){
            if(e.getName().equals(nameToSearch)){  //remember to check for the 
                return e;
            }
        }
        return null;
    }
    @Override
    public int compareTo(Employee another)  {
        return this.name.compareTo(another.name);
    }
    @Override
    public String toString(){
        return name + " (Entitled Annual Leaves: "+annualLeaves+" days)";
    }
}
