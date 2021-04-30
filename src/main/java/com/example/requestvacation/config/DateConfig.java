package com.example.requestvacation.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateConfig {
	public static String[] getDiffDaysFormat( String from, String to, String fmt ) { 
        SimpleDateFormat sdf  = new SimpleDateFormat( fmt );  
        ArrayList list = new ArrayList();  
        Calendar cal = Calendar.getInstance();  
        Date fmdate  = null; 
        Date todate  = null; 

        try {  
            fmdate = sdf.parse(from); 
            todate = sdf.parse(to); 
            cal.setTime(fmdate);  
        }  
        catch( Exception e )  
        { 
            e.printStackTrace(); 
            return null; 
        } 

        while( fmdate.compareTo(todate) <= 0 )  
        { 
            list.add(sdf.format(fmdate));  
            cal.add(Calendar.DATE, 1);  
            fmdate = cal.getTime();                 
        } 

        String[] result = new String[list.size()]; 
        list.toArray(result); 
        return result; 
    } 
}
