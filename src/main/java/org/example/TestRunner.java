package org.example;

import org.testng.TestNG;

import java.util.Collections;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        // Đường dẫn đến file testng.xml
        testng.setTestSuites(Collections.singletonList("testng.xml"));
        testng.run();
    }
}
