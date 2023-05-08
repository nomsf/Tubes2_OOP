package ooploverz.tubes2_oop.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class DateTime {
    private String dayName;
    private String monthName;
    private int month;
    private int day;
    private int year;
    private String time;

    // Make date and time with current date and time
    public DateTime() {
        Calendar now = Calendar.getInstance(); // return current date
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH);
        this.day = now.get(Calendar.DATE);

        Format f = new SimpleDateFormat("EEEE");
        this.dayName = f.format(new Date());
        this.monthName = theMonth(this.month);
        String timeComp = now.get(Calendar.HOUR_OF_DAY)+":"+now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND);
        System.out.println(timeComp);
        this.time = timeComp;
    }

    private String theMonth(int month){
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }
    public String toString(){
        String res = "";

        res += this.dayName + " ";
        res += this.monthName + " ";
        res += this.day +"/";
        res += this.month +"/";
        res += this.year +" ";
        res += this.time;

        return res;
    }

}
