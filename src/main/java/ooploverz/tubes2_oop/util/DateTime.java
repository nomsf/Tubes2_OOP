package ooploverz.tubes2_oop.util;

import lombok.AllArgsConstructor;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
public class DateTime {
    public String dayName;
    public String monthName;
    public int month;
    public int day;
    public int year;
    public String time;

    // Make date and time with current date and time
    public DateTime() {
        Calendar now = Calendar.getInstance(); // return current date
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH);
        this.day = now.get(Calendar.DATE);

        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date());
        this.dayName = str;
        this.monthName = theMonth(this.month);
        String timeComp = now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND);
        System.out.println(timeComp);
        this.time = timeComp;
    }

    public void updateTime(){
        Calendar now = Calendar.getInstance(); // return current date
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH);
        this.day = now.get(Calendar.DATE);

        Format f = new SimpleDateFormat("EEEE");
        String str = f.format(new Date());
        this.dayName = str;
        this.monthName = theMonth(this.month);
        String timeComp = now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND);
        System.out.println(timeComp);
        this.time = timeComp;
    }

    private String theMonth(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

}
