package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloatingTypeTest {
    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int) floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int) floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start_
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @Test
    void should_transfer_type_without_force_change() {
        byte b=120;
        int i=b;
        double m=9l;
        float p=0x7fffffffl;
        byte ms= (byte) 1.0;
        short s=0;
        i=s;
        s= (short) i;
        s=b;
        long l= (long) 9.0;
        i= (int) 9.0f;
        i= (int) 9l;
        long mm=i;
        int mms='s';
    }

    @Test
    void should_be_two_when_rounding_2_49() {
        double floatingPointNumber = 2.49;
        assertTrue(2==Math.round(floatingPointNumber));
    }

    @SuppressWarnings("unused")
    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;

        // TODO: Please call some method to round the floating point number.
        // <!--start
        final long rounded = Math.round(floatingPointNumber);
        // --end-->

        assertEquals(3L, rounded);
    }

    @Test
    void should_unequals_between_two_nan() {
        double nan1 = 0d / 0;
        double nan2 = 0d / 0;
        System.out.println(nan1);
        System.out.println(Double.NaN);
        System.out.println(nan2);
        assertFalse(nan1==nan2);

    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
        return realNumber!=realNumber;
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
        return realNumber==Double.POSITIVE_INFINITY||realNumber==Double.NEGATIVE_INFINITY;
    }

    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?
     * - Can we compare XXX_INFINITY using == directly?
     */
}
