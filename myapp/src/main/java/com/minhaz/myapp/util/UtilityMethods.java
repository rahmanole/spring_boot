package com.minhaz.myapp.util;

public class UtilityMethods {

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
