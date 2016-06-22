package com.hitesh.builditbigger.backend;

import com.hitesh.builditbigger.MyJoke;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private MyJoke myjokes;

    public MyBean() {
        myjokes=new MyJoke();
    }
    public  String getJoke() {
        return myjokes.getRandomJoke();
    }
}