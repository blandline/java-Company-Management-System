import java.util.ArrayList;

public class Employee implements Comparable<Employee>{

    private String name;
    private int annualLeaves;
    private ArrayList <Leave> leaves;
    public Employee(String n,int al){
        name = n;
        annualLeaves = al;
        leaves = new ArrayList<>();
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
    public void addLeaves(Leave l){
        leaves.add(l);
        annualLeaves -= l.getDifference();
    }

    @Override
    public int compareTo(Employee another)  {
        return this.name.compareTo(another.name);
    }
    @Override
    public String toString(){
        return name + " (Entitled Annual Leaves: "+annualLeaves+" days)";
    }

    public void printLeaves() {
        System.out.print(this.getName()+": ");
        if(leaves.size()==0){
            System.out.print("--");
        }
        else{
            for (Leave l : leaves) {
            System.out.print(l+",");

            }
        }
        
        System.out.println();
    }
    public void updateAnnualLeaves(int l){
        this.annualLeaves+=l;
    }

    public void updateLeaves() {
        Day s = SystemDate.getInstance();
        for(int i =0;i<leaves.size();i++)
            if(leaves.get(i).getEndDay().compareTo(s)==1){
                this.updateAnnualLeaves(leaves.get(i).getDifference());
                leaves.remove(leaves.get(i));
                
            }
        }
    }

