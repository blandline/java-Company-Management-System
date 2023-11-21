public class Day implements Cloneable{
	
	private int year;
	private int month;
	private int day;
	
	//Constructor
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}
	public void set(String sDay){
        String[] sDayParts = sDay.split("-");
        this.day = Integer.parseInt(sDayParts[0]);
        this.year = Integer.parseInt(sDayParts[2]);
        this.month = MonthNames.indexOf(sDayParts[1])/3 +1; 
    }
    public Day(String sDay){
        set(sDay);
    }

	// public Day newDay(String sDay,int d){
	// 	int nDate=0;
	// 	String[] sDayParts = sDay.split("-");
	// 	int m = MonthNames.indexOf(sDayParts[1])/3 +1;
	// 	Day nDay = new Day(sDay);
	// 	nDay.day+=d;
	// 	if(valid(nDay.year, nDay.month, nDay.day)){
	// 		return nDay;
	// 	}
	// 	else{
	// 		nDay.day-=d;
			
	// 		switch (m) {
	// 			case 1:
	// 			case 3:
	// 			case 5:
	// 			case 7:
	// 			case 8:
	// 			case 10:
	// 			case 12:
	// 				nDate = d-(31-nDay.day) ;
	// 			case 4:
	// 			case 6:
	// 			case 9:
	// 			case 11:
	// 				nDate = d-(30-nDay.day) ;
	// 			case 2:
	// 				if (isLeapYear(nDay.year))
	// 					nDate = d-(29-nDay.day) ;
	// 				else
	// 					nDate = d-(28-nDay.day) ;
						

				
	// 		}
	// 		nDay.day = nDate-1;
	// 		nDay.month++;
	// 		if(!valid(nDay.year, nDay.month, nDay.day)){
	// 			nDay.day++;
	// 		}
	// 	}
	// 	return nDay;

	// }

	public Day newDay(String sDay,int d){
		// String[] sDayParts = sDay.split("-");
		// int m = MonthNames.indexOf(sDayParts[1])/3 +1;
		Day oDay = new Day(sDay);
		oDay.day+=d-1;
		while(!valid(oDay.year,oDay.month,oDay.day)){
			oDay.day-=getDaysForMonth(oDay.year, oDay.month);
			oDay.month++;
		}
		return oDay;

	}

	public int getDaysForMonth(int y,int m){
		
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return 31; 
			case 4: case 6: case 9: case 11:
					 return 30; 
			case 2:
					 if (isLeapYear(y))
						 return 29; 
					 else
						 return 28; 
		}
		return 0;
	}

    private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
	// check if a given year is a leap year
	static public boolean isLeapYear(int y)
	{
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	// Return a string for the day like dd MMM yyyy
    @Override
	public String toString() {
		return day+"-"+MonthNames.substring((month-1)*3,(month*3))+"-"+year;
	}

    @Override
    public Day clone(){
        Day copy = null;
        try{
            copy = (Day)super.clone();  //shallow clone of day object
        }catch(CloneNotSupportedException e){
            e.printStackTrace();

        }
        return copy;
    }
}