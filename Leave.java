public class Leave {
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


    public Day getEndDay() {
        return endDay;
    }

}
