package com.cultivation;

import com.sun.xml.internal.ws.api.model.CheckedException;

public class MyCloneClass implements Cloneable{
    private String name;

    public MyCloneClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    class Point {
        int x, y;
//        int useCount;
//        Point(int x, int y) { this.x = x; this.y = y; }
//        static final Point origin = new Point(0, 0);
    }

    class Fc extends Z{

        private static final int dsa=9;
        int dsak(){
            return k;
        }
    }
     static  class Z{
        static {
            System.out.println("Dsad");
        }
         protected static final int k=99;
     }

    public static void main(String[] args) {
        Fc fc = new MyCloneClass("Dsa").new Fc();
    }
}
