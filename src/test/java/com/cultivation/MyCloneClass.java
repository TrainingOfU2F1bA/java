package com.cultivation;

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
}
