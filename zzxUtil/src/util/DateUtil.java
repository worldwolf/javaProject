/*    */ package com.fid.util;
import java.text.DateFormat;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DateUtil
/*    */ {
/*    */   public static Date parse(String input, String format)
/*    */   {
/* 15 */     SimpleDateFormat formatter = new SimpleDateFormat(format);
/*    */     try {
/* 17 */       return formatter.parse(input);
/*    */     }
/*    */     catch (ParseException e) {
/* 20 */       e.printStackTrace();
/*    */     }
/* 22 */     return null;
/*    */   }


public static Date getYesterday(){
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE,   -1);
	
	return cal.getTime();
}

public static Date getYesterday(Date date){
	if(date == null){
		date = new Date();
	}
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.DATE,   -1);
	
	return cal.getTime();
}

public static Date getSomeDay(Date date, int interval){
	if(date == null){
		date = new Date();
	}
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.DATE, interval);
	
	return cal.getTime();
}

public static String dateToString(Date date, String type) {  
    String str = null;  
    DateFormat format = new SimpleDateFormat(type);  
    str = format.format(date);   
    return str;  
}

public static Long getTodayTimeStamp(){
	
	Calendar todayStart = Calendar.getInstance();  
    todayStart.set(Calendar.HOUR_OF_DAY, 23);  
    todayStart.set(Calendar.MINUTE, 59);  
    todayStart.set(Calendar.SECOND, 59);  
    todayStart.set(Calendar.MILLISECOND, 0);
    
    return todayStart.getTime().getTime();
}

public static boolean overThisTime(Long now, int hour, int minute){
	Calendar planTime = Calendar.getInstance();  
    planTime.set(Calendar.HOUR_OF_DAY, hour);  
    planTime.set(Calendar.MINUTE, minute);  
    planTime.set(Calendar.SECOND, 0);  
    planTime.set(Calendar.MILLISECOND, 0);
    
    if(now >= planTime.getTime().getTime()){
    	return true;
    }else{
    	return false;
    }
}

public static Long timeInterval(Long now, int hour, int minute){
	Calendar planTime = Calendar.getInstance();  
    planTime.set(Calendar.HOUR_OF_DAY, hour);  
    planTime.set(Calendar.MINUTE, minute);  
    planTime.set(Calendar.SECOND, 0);  
    planTime.set(Calendar.MILLISECOND, 0);
    
    return planTime.getTime().getTime()-now;
}

public static boolean isToday(Date date){
	Long dateTime = date.getTime();
	return isToday(dateTime);
}

public static boolean isToday(Long now){
	Long span =  getTodayTimeStamp() - now;
	
	if(span >= 0 && span < 24*60*60*1000){
		return true;
	}else{
		return false;
	}
	
}

public static Long intervalBetweenNow(Long timeStamp){
	Long now = new Date().getTime();
	
	return timeStamp-now;
}

public static boolean isBetweenAfterAndTo(String after, String to, String now){
	
	if(now.compareTo(after) >= 0 && now.compareTo(to) <=0){
		return true;
	}
	
	return false;
}

public static void main(String[] args) {
	Date date = new Date(getTodayTimeStamp());
	System.out.println(date);
	
	Calendar todayStart = Calendar.getInstance();  
    todayStart.set(Calendar.HOUR_OF_DAY, 0);  
    todayStart.set(Calendar.MINUTE, 0);  
    todayStart.set(Calendar.SECOND, 0);  
    todayStart.set(Calendar.MILLISECOND, 0);
}
/*    */ }

/* Location:           /Users/yk/Documents/19-监测系统/2-代码/
 * Qualified Name:     org.c3.util.DateUtil
 * JD-Core Version:    0.6.2
 */