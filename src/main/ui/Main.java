package ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(2000,12,25);
        SimpleDateFormat ft = new SimpleDateFormat("E dd-MM-yyyy");
        System.out.println(ft.format(cal.getTime()));
    }
}
