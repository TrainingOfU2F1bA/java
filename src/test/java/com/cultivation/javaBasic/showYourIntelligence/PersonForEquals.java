package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals implements Comparable{
    private final String name;
    private final short yearOfBirth;

    @Override
    public int compareTo(Object o) {
        if (o==null) throw new NullPointerException();
        PersonForEquals person= (PersonForEquals) o;
        int compare = this.name.compareTo(person.name);
        if (compare !=0) return compare;
        return this.yearOfBirth<person.yearOfBirth?-1:1;
    }

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        if (obj==this) {
            return true;
        }
        if (obj==null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())){
            return false;
        }
        PersonForEquals person=(PersonForEquals) obj;
        if (name.equals(person.getName()) && yearOfBirth==person.getYearOfBirth()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return (name + yearOfBirth).hashCode();
        // --end-->
    }
}
