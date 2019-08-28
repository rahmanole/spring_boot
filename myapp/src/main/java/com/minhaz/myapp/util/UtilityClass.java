package com.minhaz.myapp.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class UtilityClass {

    public String getDateTime(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String postDate = numberConvert(day)+" "+getMonthNameInBangla(month)+" "
                +numberConvert(year)+", ";
        String postTime = numberConvert(hour)+":"+numberConvert(minute);

        if(day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
            postDate = "আজ"+", ";
        }

        if(minute<10){
            postTime = numberConvert(hour)+":০"+numberConvert(minute);
        }
        return postDate+postTime;
    }

    public String getMonthNameInBangla(int month){
        switch (month){
            case 0:
                return  "জানুয়ারী";
            case 1:
                return  "ফেব্রুয়ারী";
            case 2:
                return  "মার্চ";
            case 3:
                return  "এপ্রিল";
            case 4:
                return  "মে";
            case 5:
                return  "জুন";
            case 6:
                return  "জুলাই";
            case 7:
                return  "আগস্ট";
            case 8:
                return  "সেপ্টেম্বর";
            case 9:
                return  "অক্টোবর";
            case 10:
                return  "নভেম্বর";
            case 11:
                return  "ডিসেম্বর";
        }
        return  "";
    }

    public String numberConvert(int num){
        String res = "";
        char[] nums = (num+"").toCharArray();
        for (char c:nums) {
            switch (c){
                case '0':
                      res+="০";
                      break;
                case '1':
                    res+="১";
                    break;
                case '2':
                    res+="২";
                    break;
                case '3':
                    res+="৩";
                    break;
                case '4':
                    res+="৪";
                    break;
                case '5':
                    res+="৫";
                    break;
                case '6':
                    res+="৬";
                    break;
                case '7':
                    res+="৭";
                    break;
                case '8':
                    res+="৮";
                    break;
                case '9':
                    res+="৯";
                    break;
            }
        }

        return res;

    }

    public static String getCatNameInBangla(String cat){

        switch (cat){
            case "politics":
                return "রাজনীতি";
            case "bangladesh":
                return "বাংলাদেশ";
            case "international":
                return "আন্তর্জাতিক";
            case "sports":
                return "খেলা";
            case "economy":
                return "অর্থনীতি";
            case "entertainment":
                return "বিনোদন";
            case "sciTech":
                return "বিজ্ঞান ও প্রযুক্তি";
            case "editorial":
                return "সম্পাদকীয়";
            case "opinion":
                return "মতামত";
            case "aboard":
                return "প্রবাসে বাংলাদেশীরা";
            case "campus":
                return "শিক্ষাঙ্গন";
        }
        return "";
    }
}
