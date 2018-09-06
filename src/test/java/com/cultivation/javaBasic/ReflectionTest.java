package com.cultivation.javaBasic;

import com.cultivation.Father;
import com.cultivation.Son;
import com.cultivation.SonAnnotation;
import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {
    @Test
    void array_of_subclass_is_not_subclass_of_array_of_superclass() {
        DerivedFromSuperClassWithDefaultConstructor[] array = new DerivedFromSuperClassWithDefaultConstructor[4];
        SuperClassWithDefaultConstructor[] array1 = new SuperClassWithDefaultConstructor[6];
        assertTrue(array instanceof SuperClassWithDefaultConstructor[]);
        assertFalse(array1 instanceof DerivedFromSuperClassWithDefaultConstructor[]);
        assertNotEquals(SuperClassWithDefaultConstructor[].class, DerivedFromSuperClassWithDefaultConstructor[].class);
    }

    @Test
    void should_be_able_to_get_class_object() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();


        // TODO: please modify the following code to pass the test
        // <--start
        final Class<? extends Employee> expected = Employee.class;
        // --end-->

        assertEquals(expected, employeeClass);
    }

    @Test
    void should_be_able_to_get_full_qualified_name() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = employee.getClass().getName();
        // --end-->

        assertEquals(expected, employeeClass.getName());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_instantiate_types_at_runtime() throws Exception {
        Class<?> theClass = Class.forName("com.cultivation.javaBasic.util.Employee");

        // TODO: please created an instance described by `theClass`
        // <--start
        Employee instance = (Employee) theClass.newInstance();
        // --end-->

        assertEquals("Employee", instance.getTitle());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_print_all_static_methods_of_double() {
        Class<Double> doubleClass = Double.class;

        // TODO: please get all public static declared methods of Double. Sorted in an ascending order
        // <--start
        Method[] methods = doubleClass.getDeclaredMethods();
        ArrayList<String> list = new ArrayList<>();
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                list.add(method.getName());
            }
        }
        list.sort(String::compareTo);
        String publicStaticMethods[] =list.toArray(new String[0]);
        // --end-->

        final String[] expected = {
                "compare", "doubleToLongBits", "doubleToRawLongBits", "hashCode",
                "isFinite", "isInfinite", "isNaN", "longBitsToDouble", "max",
                "min", "parseDouble", "sum", "toHexString", "toString", "valueOf",
                "valueOf"
        };

        assertArrayEquals(expected, publicStaticMethods);
    }

    @SuppressWarnings({"unused", "ConstantConditions", "RedundantThrows"})
    @Test
    void should_be_able_to_evaluate_object_field_values_at_runtime() throws Exception {
        Object employee = new Employee();

        // TODO: please get the value of `getTitle` method using reflection. No casting to Employee is allowed.
        // <--start
        Object result = Employee.class.getMethod("getTitle").invoke(employee);
        // --end-->

        assertEquals("Employee", result);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_be_able_to_get_the_item_class_of_the_array() {
        Object employees = new Employee[0];

        // TODO: please get the class of array item `employees`
        // <--start
        Class<?> itemClass = employees.getClass().getComponentType();
        // --end-->

        assertEquals(Employee.class, itemClass);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_get_the_methods_who_contains_MyAnnotation_annotation() {
        List<String> methodsNames = new ArrayList<>();
        for (Method method : Son.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(SonAnnotation.class)) {
                methodsNames.add(method.getName());
            }
        }
        assertArrayEquals(new String[]{"iAmSon"},methodsNames.toArray());
    }

    @Test
    void fatherClass_of_arrayofSubclass_is_not_arrayofFatherclass() {
        assertNotEquals(Father[].class,Son[].class.getSuperclass());
        assertEquals(Object.class,Son[].class.getSuperclass());
    }

    @Test
    void fatherClass_of_component_of_arrayofSubclass_is_component_arrayofFatherclass() {
        assertEquals(Father[].class.getComponentType(),Son[].class.getComponentType().getSuperclass());
    }
}

/*
 * - What is the class name of array type?
 * Link:https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.3.2
 * - How to compare 2 classes?
 * - What if the class does not contain a default constructor when you call `newInstance()`?
 * - What is source only annotation? Can we get source only annotations via reflection?
 */
