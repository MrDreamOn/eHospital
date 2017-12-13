package com.hospital.register.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    
    public static final String TODAY = "yyMMdd";
    
    private static String DATE_TIME_FORMAT_NO_SPLIT = "yyyyMMddHHmmss";
    public static final String DATE_NO_SPLIT = "yyyyMMdd";

    private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static String DATE_FORMAT = "yyyy-MM-dd";
    
    private static String DATE_TIME_FORMAT_SLASH = "yyyy/MM/dd HH:mm:ss";
    private static String DATE_FORMAT_SLASH = "yyyy/MM/dd";
	 /**
     * 得到该月的第一天,
     * 
     * @return Date
     */
    public static Date getMonthBegin(Date date) {

        Calendar can = Calendar.getInstance();
        can.setTime(date);
        // 说明给定时间是月末,返回值应该是下个月末

        can.set(Calendar.DAY_OF_MONTH, can.getMinimum(Calendar.DAY_OF_MONTH));
        can.set(Calendar.HOUR_OF_DAY, 0);
        can.set(Calendar.MINUTE, 0);
        can.set(Calendar.SECOND, 0);

        return can.getTime();
    }

    /**
     * 得到指定日期所在月的最后一天,如果给定时间是月末,返回值应该是下个月末
     * 
     * @param date
     * @return Date
     */
    public static Date getMonthEnd(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        // 说明给定时间是月末,返回值应该是下个月末
        can.set(Calendar.DAY_OF_MONTH, can.getActualMaximum(Calendar.DAY_OF_MONTH));
        can.set(Calendar.HOUR_OF_DAY, 23);
        can.set(Calendar.MINUTE, 59);
        can.set(Calendar.SECOND, 59);

        return can.getTime();
    }

    /**
     * 获取当前周的第一天
     * 
     * 
     * @param date
     * @return Date
     */
    public static Date getWeekstart(Date date) {
        Calendar c = Calendar.getInstance();
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 1);//本周第一天，以星期日开始       
        return c.getTime();
    }

    /**
    * 获取当前周的第一天
    * 
    * 
    * @param date
    * @return Date
    */
    public static Date getWeekend(Date date) {
        Calendar c = Calendar.getInstance();
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 7);//本周第一天，以星期日开始       
        return c.getTime();
    }

    /**
     * 将日值字符串转换成Date类型
     * 
     * @param str
     *            参数字符串
     * @param format
     *            格式
     * @return Date
     */
    public static Date parseDateString(String str, String format) {
        if (str == null || str.equals(""))
            return null;
        Date dt = null;
        try {
            DateFormat df = new SimpleDateFormat(format);
            dt = df.parse(str);

        } catch (Exception pe) {
        }

        return dt;
    }

    public static String parseDateToString(String str, String formatOld, String formatNew) {
        if (str == null || str.equals(""))
            return null;
        String result = null;
        try {
            DateFormat df = new SimpleDateFormat(formatOld);
            Date date = df.parse(str);
            DateFormat dfNew = new SimpleDateFormat(formatNew);
            result = dfNew.format(date);
        } catch (Exception pe) {
        }

        return result;
    }
   
    /**
     * 将Date类型转换成日值字符串
     * 
     * @param str
     *            参数字符串
     * @param format
     *            格式
     * @return Date
     */
    public static String parseDateToStr(Date date, String format) {
        if (date == null)
            return null;
        String result = null;
        try {
            DateFormat df = new SimpleDateFormat(format);
            result = df.format(date);
        } catch (Exception pe) {
        }

        return result;
    }

    /**
     * 调整时间 将指定日期调整 时间
     * 
     * @param date
     *            参数日期
     * @param field
     *            自定字段域
     * @param amount
     *            调整数量
     * @param format
     *            字符串格式
     * @return String
     */
    public static Date getAdjustDate(Date date, int field, int amount) {
        if ((date == null)) {
            return null;
        }
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        can.add(field, amount);
        Date newDate = can.getTime();
        return newDate;
    }

    /**
     * 得到指定日期是一个星期中的第几天
     * 
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        int weekDay = can.get(Calendar.DAY_OF_WEEK);
        return weekDay;
    }

    /**
     * 得到指定日期是星期几
     * 
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static String getAmOrPm() {
        GregorianCalendar ca = new GregorianCalendar();
        int i = ca.get(GregorianCalendar.AM_PM);

        if (i == 0) {
            return "上午";
        } else {
            return "下午";
        }
    }

    /**
     * 将日期format成指定格式字符串
     * 
     * @param dt
     *            日期
     * @param format
     *            格式
     * @return String
     */
    public static String formatDate(java.util.Date dt, String format) {
        if ((dt == null) || format == null) {
            return "";
        }
        String strDate = "";
        String s1;
        try {
            java.text.SimpleDateFormat DATA_FORMAT = new java.text.SimpleDateFormat(format);
            strDate = DATA_FORMAT.format(dt);
            String s = strDate;
            return s;
        } catch (Exception e) {
            s1 = null;
        }
        return s1;
    }

    /**
     * 时间段转换
     * 
     * @param date
     * @return
     */
    public static String conversionTime(String date) {
        if(!StringUtils.hasText(date)){
            return "";
        }
        date = date.replaceAll("-", "—").replaceAll("：", ":").trim();
        String str = null;
        if ("08:00—09:00".equals(date)) {
            str = "1";
        } else if ("09:00—10:00".equals(date)) {
            str = "2";
        } else if ("10:00—11:00".equals(date)) {
            str = "3";
        } else if ("13:30—14:30".equals(date)) {
            str = "6";
        } else if ("14:30—15:30".equals(date)) {
            str = "7";
        } else if ("15:30—16:30".equals(date)) {
            str = "8";
        } else if ("08:00-17:00".equals(date)) {
            str = "9";//没有时间段默认全天
        } else {
            str = "";
        }
        return str;
    }

    /**
     * 时间段转换
     * 
     * @param date
     * @return
     */
    public static String conversionTimeString(String date) {
        date = date.replaceAll("-", "—").replaceAll("：", ":").trim();
        String str = null;
        if ("08:00—09:00".equals(date)) {
            str = "1";
        } else if ("09:00—10:00".equals(date)) {
            str = "2";
        } else if ("10:00—11:00".equals(date)) {
            str = "3";
        } else if ("13:30—14:30".equals(date)) {
            str = "4";
        } else if ("14:30—15:30".equals(date)) {
            str = "5";
        } else if ("15:30—16:30".equals(date)) {
            str = "6";
        } else {
            str = "";
        }
        return str;
    }

    /**
     * 获取当前日期
     * 
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期和时间
     * 
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @return
     */
    public static String formatDateTimeNoSplit(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_NO_SPLIT);
            return sdf.format(date);
        }
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
            return sdf.format(date);
        }
    }

    /**
     * 格式化日期
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(date);
        }
    }
    
    /**
     * 格式化日期  DATE_NO_SPLIT
     * 
     * @param date
     * @return
     */
    public static String formatDateNoSplit(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_NO_SPLIT);
            return sdf.format(date);
        }
    }
    
    
    public static String formatDateString(Date date){
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(TODAY);
            return sdf.format(date);
        }
    }

    public static Date formatDateToDate(Date date) {
        SimpleDateFormat simple = new SimpleDateFormat(DATE_FORMAT);
        String ds = simple.format(date);
        try {
            return simple.parse(ds);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 转换日期为23:59:59的格式 如 param 为 2016-01-01 00:00:00 则转为 2016-01-01 23:59:59
     * 
     * @author : liuyang.chen
     * @time : 2016年8月11日 下午7:33:11
     * @param param
     * @return
     */
    public static Date convertDate(Date param) {
        if (null == param) {
            return null;
        }
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(param);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        return currentDate.getTime();
    }

    /**
     * 格式化string为Date
     * 
     * @param datestr
     * @return date
     */
    public static Date parseDate(String datestr) {
        if (null == datestr || "".equals(datestr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf;
            if (datestr.indexOf("-") > 0){
                if(datestr.indexOf(':') > 0){
                    sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
                }else{
                    sdf = new SimpleDateFormat(DATE_FORMAT);
                }
            }else if(datestr.indexOf("/") > 0){
                if(datestr.indexOf(':') > 0){
                    sdf = new SimpleDateFormat(DATE_TIME_FORMAT_SLASH);
                }else{
                    sdf = new SimpleDateFormat(DATE_FORMAT_SLASH);
                }
            }else{
                if(datestr.indexOf(':') > 0){
                    sdf = new SimpleDateFormat(DATE_TIME_FORMAT_NO_SPLIT);
                }else{
                    sdf = new SimpleDateFormat(DATE_TIME_FORMAT_NO_SPLIT);
                }
            }
            return sdf.parse(datestr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间的星期并且判断是上午还是下午
     * @author : yanan.zhang
     * @time : 2017年3月22日 上午10:42:09
     * @return
     */
    public static String getCurrentWeekAndTime(){
        Calendar calendar = new GregorianCalendar();
        String weekStr = getWeekByDate(calendar.get(Calendar.DAY_OF_WEEK));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour >= 12){
            return weekStr + "下午";
        }else{
            return weekStr + "上午";
        }
    }
    
    public static String getWeekByDate(int i) {
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * 获取前一天的日期
     * @param date
     * @return
     */
    public static Date getBeforeDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();
        return date;  
    }
    
    /**
     * 获取后一天的日期
     * @param date
     * @return
     */
    public static Date getAfterDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }
    
    /**
     * 获取某一天是一星期中的星期几
     * @param pTime
     * @return
     * @throws Exception
     */
    public static int dayForWeek(String pTime) throws Exception {
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
          Calendar c = Calendar.getInstance();
          c.setTime(format.parse(pTime));
          int dayForWeek = 0;
          dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
          return dayForWeek;
     }
    
    /**
     * 两个日期间隔天数
     * 
     * @param from
     * @param to
     * @return
     */
    public static int compareDays(Date from, Date to) {
        if (null == from || null == to) {
            return -1;
        }
        long intervalMilli = to.getTime() - from.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }
    
    /**
     * 计算两个日期之间相差的天数
     * 
     * @author : liuyang.chen
     * @time : 2016年9月20日 下午5:25:45
     * @param start_time
     * @param end_time
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date start_time, Date end_time) {
        start_time = DateUtil.parseDate(DateUtil.formatDate(start_time));
        end_time = DateUtil.parseDate(DateUtil.formatDate(end_time));

        Calendar cal = Calendar.getInstance();
        cal.setTime(start_time);
        // 开始时间的毫秒数
        long startTimeMillis = cal.getTimeInMillis();
        cal.setTime(end_time);
        // 结束时间的毫秒数
        long endTimeMillis = cal.getTimeInMillis();
        long days = (endTimeMillis - startTimeMillis) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(days)) + 1;
    }

    /**
     * 获取当前日期之前的某多少天的日期
     * 
     * @author : liuyang.chen
     * @time : 2016年10月31日 上午10:59:05
     * @return
     */
    public static Date getCurBeforDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -(day - 1));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        return date;
    }
    
    /**
     * 校验时间是否在当前时间之前
     * 
     * @param hour
     * @param min
     * @return
     */
    public static boolean isSubscriptionTime(Integer hour, Integer min) {
        boolean result = false;
        Calendar start = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        start.set(Calendar.HOUR_OF_DAY, hour);
        start.set(Calendar.MINUTE, min);
        start.set(Calendar.SECOND, 0);

        if (now.before(start)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    
    /**
     * 计算年龄
     * 
     * @param birthDate
     * @return
     */
    public static Integer getAge(Date birthDate) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(birthDate);
        return year - calendar.get(Calendar.YEAR);
    }
    
    /**
     * 判断挂号时间是否是在挂号开始和结束时间之内
     * @param startTime  开始时间
     * @param endTime 结束时间
     * @return true or false
     * @throws ParseException 
     */
    public static boolean isRegisterTime(String startTime, String endTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH:mm");

        /*将字符型时间转为日期型时间*/
        Date startDate = format.parse(startTime);
        Date endDate = format.parse(endTime);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        /** 挂号开始时间 */
        Calendar regStartTime = Calendar.getInstance();
        regStartTime.set(Calendar.HOUR_OF_DAY, start.get(Calendar.HOUR_OF_DAY));
        regStartTime.set(Calendar.MINUTE, start.get(Calendar.MINUTE));
        regStartTime.set(Calendar.SECOND, start.get(Calendar.SECOND));

        /** 挂号结束时间 */
        Calendar regEndTime = Calendar.getInstance();
        regEndTime.set(Calendar.HOUR_OF_DAY, end.get(Calendar.HOUR_OF_DAY));
        regEndTime.set(Calendar.MINUTE, end.get(Calendar.MINUTE));
        regEndTime.set(Calendar.SECOND, end.get(Calendar.SECOND));

        /** 服务器当前时间 */
        Calendar now = Calendar.getInstance();

        /** 如果当前时间在挂号开始时间和结束时间之前,则返回true,否则返回false */
        if (now.before(regEndTime) && now.after(regStartTime)) {
            return true;
        }

        return false;
    }

    /**
     * 判断上午挂号结束时间
     * @param amEndtime 上午结束时间
     * @return true or false
     * @throws ParseException 
     */
    public static boolean isRegTime(String amEndTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH:mm");

        /*将字符型时间转为日期型时间*/
        Date amEndDate = format.parse(amEndTime);
        Calendar amEnd = Calendar.getInstance();
        amEnd.setTime(amEndDate);

        /** 挂号上午结束时间 */
        Calendar regAMEndTime = Calendar.getInstance();
        regAMEndTime.set(Calendar.HOUR_OF_DAY, amEnd.get(Calendar.HOUR_OF_DAY));
        regAMEndTime.set(Calendar.MINUTE, amEnd.get(Calendar.MINUTE));
        regAMEndTime.set(Calendar.SECOND, amEnd.get(Calendar.SECOND));

        /** 服务器当前时间 */
        Calendar now = Calendar.getInstance();

        /** 如果当前时间在挂号开始时间和结束时间之前,则返回true,否则返回false */
        if (now.after(regAMEndTime)) {
            return true;
        }
        return false;
    }

    /**
     * 获取相隔num天的日期
     * @param date 当前日期
     * @param num 相隔天数(正整数：今天之后的日期，负整数：今天之前的日期)
     * @return
     */
    public static Date getNotToday(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        date = calendar.getTime();
        return date;
    }
    
    public static void main(String[] args) {
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(parseDate("2013/10/19 8:57:06"));
        System.out.println(formatDateTime(calendar.getTime()));
    }
}
