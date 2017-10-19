package com.example.administrator.project;

/**
 * Created by Administrator on 2017-10-17.
 */

public class ListData1 {

    private String text1;
    private String text2;
    private String text3;
    private String text4;

    ListData1(String text1, String text2, String text3, String text4){
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;

    }
    ListData1(String text1){
        this.text1 = text1;
    }




    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }
}
