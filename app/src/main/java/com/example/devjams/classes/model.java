package com.example.devjams.classes;

public class model {
    public static String arr[] = new String[5]  ;
    public static String url[] = new String[5] ;

    public static String getArr(int i) {
        return arr[i];
    }

    public static void setArr(String arr1,int i) {
        arr[i] = arr1;
    }

    public String[] getUrl() {
        return url;
    }

    public static  void setUrl(String url1,int i) {
        url[i] = url1;
    }
}
