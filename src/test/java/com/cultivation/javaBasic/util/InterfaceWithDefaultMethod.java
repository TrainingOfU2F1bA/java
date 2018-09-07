package com.cultivation.javaBasic.util;


public interface InterfaceWithDefaultMethod {
    default String tellMeTheTruthOfTheUniverse() {
        return "The truth of the universe is " + getTheTruthOfTheUniverse();
    }

    default String getTheTruthOfTheUniverse() {
        return "42";
    }

    public static void he(String[] args) {
        System.out.println("he");
    }

    public static void main(String[] args) {

    }
}

