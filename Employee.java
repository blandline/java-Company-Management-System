import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee>{

    private String name;
    private int annualLeaves;
    private int leavesLeft;
    private ArrayList <Leave> leaves;
    public Employee(String n,int al){
        name = n;
        annualLeaves = al;
        leavesLeft = al;
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
    public void addLeaves(Leave l) throws ExLeavesOverLapping,ExFinalStageOverlap{
        Company c = Company.getInstance();
        Team t = c.findTeamFromMember(this);
        ArrayList<Project> projects = c.findProjectFromTeam(t);
        if(projects!=null){
            for(Project p: projects){
                if(p.overLap(l)){
                    throw new ExFinalStageOverlap(p);
                }
            }
            
        }
        
        for(Leave a: leaves){
            if(a.leaveOverlap(l)){
                throw new ExLeavesOverLapping(a);
            }
        }
        leaves.add(l);
        Collections.sort(leaves);
        leavesLeft -= l.getDifference();
    }

    @Override
    public int compareTo(Employee another)  {
        return this.name.compareTo(another.name);
    }
    @Override
    public String toString(){
        return name + " (Entitled Annual Leaves: "+annualLeaves+" days)";
    }
    public String getLeaves(){
        String leaveString = "";
        if(leaves.size()==0){
            leaveString+="--";
        }
        else{
            int numLeaves = leaves.size();
            int count = 0;
            for (Leave l : leaves) {
                if (count < numLeaves - 1) {
                    leaveString+=l+", ";
                }
                else {
                    leaveString+=l;
                }
                count++;
            }
        }
        return leaveString;
    }
    public void printLeaves() {
    
        System.out.print(this.getName()+": ");
      
        
        if(leaves.size()==0){
            System.out.print("--");
        }
        else{
            int numLeaves = leaves.size();
            int count = 0;
            for (Leave l : leaves) {
                if (count < numLeaves - 1) {
                    System.out.print(l + ", ");
                } else {
                    System.out.print(l);
                }
                count++;

            }
        }
        
        System.out.println();
    }
    public void updateAnnualLeaves(int l){
        this.leavesLeft+=l;
    }

    public void updateLeaves() {
        Day s = SystemDate.getInstance();
        for(int i =0;i<leaves.size();i++)
            if(leaves.get(i).getEndDay().compareTo(s)==-1){
                this.updateAnnualLeaves(leaves.get(i).getDifference());
                leaves.remove(i);
                
            }
        }

    public int getAnnualLeaves() {
        return annualLeaves;
    }

    public void removeLeave(Leave l) {
        leavesLeft+=l.getDifference();
        leaves.remove(l);
    }

    public int getLeavesLeft() {
        return leavesLeft;
    }
    }

