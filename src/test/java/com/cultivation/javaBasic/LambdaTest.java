package com.cultivation.javaBasic;

import com.cultivation.Comsumer;
import com.cultivation.IntFunction;
import com.cultivation.MyFunction;
import com.cultivation.Son;
import com.cultivation.javaBasic.showYourIntelligence.PersonForEquals;
import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaTest {
    @Test
    void test_summer() {
        MyFunction summer = ints -> {
            if (ints != null) {
                int sum = 0;
                for ( int i = 0; i < ints.length; i++) {
                    sum += ints[i];
                }
                return sum;
            }
            return 0;
        };
        int ints[] = null;
        assertEquals(0 ,summer.apply(ints));
        ints = new int[]{1};
        assertEquals(1 ,summer.apply(ints));
        ints = new int[]{1, 2, 3};
        assertEquals(6 ,summer.apply(ints));
    }

    @Test
    void test_exchanger() {
        Comsumer comsuer=(objects)->{
            if (objects!=null&&objects.length>1) {
                Object swap=objects[0];
                objects[0] = objects[1];
                objects[1] = swap;
            }
        };
        Object obj1=new Object();
        Object[] objects1=new Object[]{obj1};
        comsuer.apply(objects1);
        assertArrayEquals(new Object[]{obj1},objects1);

        Object obj2=new Object();
        Object[] objects2 = new Object[]{obj1,obj2};
        comsuer.apply(objects2);
         assertArrayEquals(new Object[]{obj2,obj1},objects2);

         Object obj3=new Object();
        Object[] objects3 = {obj1, obj2, obj3};
        comsuer.apply(objects3);
        assertArrayEquals(new Object[]{obj2,obj1,obj3},objects3);
    }

    @Test
    void test_int_bifunction() {
        IntBiFunction biFunction=(a,b)->a+b;
        assertEquals(5,biFunction.apply(2,3));
    }

    @Test
    void test_int_function() {
        IntFunction intFunction=i->i;
        int expected = 2;
        assertEquals(expected,intFunction.apply(expected));
    }

    @Test
    void test_char_supplier(){
        CharSupplier charSupplier=()->'a';
        char expected = 'a';
        assertEquals(expected,charSupplier.getAsChar());
    }

    @Test
    void test_int_supplier() {
        IntegerSuppliers integerFunc=()->1;
        int expected = 1;
        assertEquals(expected,integerFunc.getAsInt());
    }

    @Test
    void practice_one() {
        PersonForEquals james1990 = new PersonForEquals("James", (short) 1990);
        PersonForEquals james1992 = new PersonForEquals("James", (short) 1992);
        PersonForEquals alice1990 = new PersonForEquals("Alice", (short) 1990);
        PersonForEquals persons[]={james1990, james1992, alice1990};
        Arrays.sort(persons);
        assertArrayEquals(new PersonForEquals[]{
                alice1990,james1990,james1992
        },persons);
    }

    @Test
    void should_apply_to_interface_with_single_abstract_method() {
        StringFunc lambda = () -> "Hello";

        // TODO: please modify the following code to pass the test_summer
        // <--start
        final String expect = "Hello" ;
        // --end-->

        assertEquals(expect, lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO: please bind lambda to instanceMethod.
        // <--start
        StringFunc lambda = this::instanceMethod;
        // --end-->

        assertEquals("instanceMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO: please bind lambda to staticMethod
        // <--start
        StringFunc lambda = LambdaTest::staticMethod;
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_bind_to_constructor() {
        // TODO: please bind lambda to constructor of ArrayList<Integer>
        // <--start
        GenericFunc<ArrayList<Integer>> lambda = ArrayList::new;
        // --end-->

        ArrayList<Integer> value = lambda.getValue();

        assertEquals(0, value.size());
    }

    @Test
    void should_capture_variable_in_a_closure() {
        int captured = 5;

        StringFunc lambda = () -> captured + " has been captured.";

        final String message = lambda.getString();

        // TODO: please modify the following code to pass the test_summer
        // <--start
        final String expected = "5 has been captured." ;
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_evaluate_captured_variable_when_executing() {
        ValueHolder<String> value = new ValueHolder<>();
        value.setValue("I am the King of the world!");

        StringFunc lambda = () -> "The length of captured value is: " + value.getValue().length();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected =  "The length of captured value is: 4";
        // --end-->

        value.setValue("Blah");
        assertEquals(expected, lambda.getString());
    }

    @Test
    void name() {

        System.out.println(hjk().get());
    }

    Supplier<Son> hjk(){
        Son son = new Son();
        Supplier<Son> sonSupplier=()->son;
        System.out.println(son);
        return sonSupplier;
    }
    @Test
    void should_extend_variable_scope() {
        StringFunc stringFunc = returnLambda();
        String message = stringFunc.getString();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "In the year 2019";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_capture_this_variable() {
        ThisInClosure instance = new ThisInClosure();
        StringFunc stringFunc = instance.getLambda();

        // TODO: please modify the following code to pass the test_summer
        // <--start
        final String expected = "ThisInClosure" ;
        // --end-->

        assertEquals(expected, stringFunc.getString());
    }

    private static StringFunc returnLambda() {
        int year = 2019;
        return () -> "In the year " + year;
    }

    @SuppressWarnings("unused")
    private static String staticMethod() {
        return "staticMethod";
    }

    @SuppressWarnings("unused")
    private String instanceMethod() {
        return "instanceMethod";
    }
}

/*
 * - Do you think you can assign a lambda expression to an Object instance?
 */
