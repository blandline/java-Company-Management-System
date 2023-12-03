import java.util.ArrayList;

public class Project implements Comparable<Project>{
    private String projName;
    private Day startDay;
    private int daysToComp;
    private Day endDay;
    private Day finalStageStart;


    

    public Project(String pName,String sDay,int d){
        projName = pName;
        startDay = new Day(sDay);
        daysToComp = d;
        endDay = new Day(sDay);
        endDay = endDay.newDay(sDay,d);
        finalStageStart = new Day(sDay);
        if(d>=5){
            finalStageStart = finalStageStart.getPreviousDate(endDay, 5);
        }
        

    }
    

    @Override
    public String toString(){
        Company c = Company.getInstance();
        String output = String.format("%-9s%-13s%-13s",projName,startDay,endDay);
        return output;
    }

    public String getProjName() {
        return projName;
    }

    @Override
    public int compareTo(Project another)  {
        return this.projName.compareTo(another.projName);
    }


    public boolean overLap(Leave l) {
        boolean output = false;
        if(((l.getStartDay().compareTo(finalStageStart)==1)||(l.getStartDay().compareTo(finalStageStart)==0))&&((l.getStartDay().compareTo(endDay)==-1))||(l.getStartDay().compareTo(endDay)==0)){
            output = true;
        }
        if(((l.getEndDay().compareTo(finalStageStart)==1)||(l.getEndDay().compareTo(finalStageStart)==0))&&((l.getEndDay().compareTo(endDay)==-1)||(l.getEndDay().compareTo(endDay)==0))){
            output = true;
        }
        if(((finalStageStart.compareTo(l.getStartDay())==1)||(finalStageStart.compareTo(l.getStartDay())==0))&&((finalStageStart.compareTo(l.getEndDay())==-1)||(finalStageStart.compareTo(l.getEndDay())==0))){
            output = true;
        }
        if(((endDay.compareTo(l.getStartDay())==1)||(endDay.compareTo(l.getStartDay())==0))&&((endDay.compareTo(l.getEndDay())==-1)||(endDay.compareTo(l.getEndDay())==0))){
            output = true;
        }
        return output;
    }


    public Day getFinalStart() {
        return finalStageStart;
    }


    public Day getEndDay() {
        return endDay;
    }
    

}
