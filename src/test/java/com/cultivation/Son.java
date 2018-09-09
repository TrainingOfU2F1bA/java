package com.cultivation;

import com.cultivation.javaBasic.util.MyAnnotation;

public class Son extends Father {
    @SonAnnotation
    public void iAmSon(){


    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Son.thisIsFather();
    }
}
