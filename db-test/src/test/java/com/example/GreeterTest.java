package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GreeterTest {

    private Greeter gr;

    @Before
    public void setUp() {
        gr = new Greeter();
    }

    @Test
    public void testMethod1() {
        String test_string = "AA";
        assertEquals(test_string, gr.method1(test_string));
    }

}
