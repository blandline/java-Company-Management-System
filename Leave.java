public class Leave implements Comparable<Leave> {
    private Day startDay;
    private Day endDay;
    private int difference;
  


    public Leave(String sDay,String eDay){
        startDay = new Day(sDay);
        endDay = new Day(eDay);
        difference = startDay.dayDifference(endDay);
    }


    public int getDifference(){
        return difference;
    }

    @Override
    public String toString(){
        return startDay+" to "+endDay;
    }

    public boolean leaveOverlap(Leave l){
        boolean output = false;
        if((l.getStartDay().compareTo(startDay)==1)&&(l.getStartDay().compareTo(endDay)==-1)){
            output = true;
        }
        if((l.getEndDay().compareTo(startDay)==1)&&(l.getEndDay().compareTo(endDay)==-1)){
            output = true;
        }
        if((startDay.compareTo(l.getStartDay())==1)&&(startDay.compareTo(l.getEndDay())==-1)){
            output = true;
        }
        if((endDay.compareTo(l.getStartDay())==1)&&(endDay.compareTo(l.getEndDay())==-1)){
            output = true;
        }
        return output;
    }

    public Day getEndDay() {
        return endDay;
    }
    public Day getStartDay() {
        return startDay;
    }

    @Override
    public int compareTo(Leave another)  {
        return this.getStartDay().compareTo(another.getStartDay());
    }

}
