package com.cultivation.javaBasic;

import com.cultivation.MyCloneClass;
import com.cultivation.javaBasic.showYourIntelligence.NameImpl;
import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceTest {

    @Test
    void should_support_default_method() {
        InterfaceWithDefaultMethodImpl instance = new InterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is 42" ;
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method() {
        InterfaceWithOverrideDefaultImpl instance = new InterfaceWithOverrideDefaultImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Anime" ;
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method_continued() {
        InterfaceExtendsInterfaceWithDefaultMethod instance = new InterfaceExtendsInterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Game" ;
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_resolve_ambiguity_by_yourself() {
        NameImpl instance = new NameImpl();
        String name = instance.getName();

        assertEquals("Person", name);
    }

    @Test
    void clone_an_object_without_a_default_constructor() throws CloneNotSupportedException {
        String testString = "MyCloneClass";
        MyCloneClass myCloneClass = new MyCloneClass(new String("MyCloneClass"));
        MyCloneClass clone = (MyCloneClass) myCloneClass.clone();
        assertNotEquals(myCloneClass,clone);
        assertEquals(myCloneClass.getName(),clone.getName());
        assertTrue(myCloneClass.getName()==clone.getName());
        assertFalse(testString ==clone.getName());
        System.out.println(MyAnnotation.class);
    }
}

/*
 * - Can you clone an object without a default constructor?
 */
