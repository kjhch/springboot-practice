package com.hch;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MyTest {
    @Test
    public void testSystemProperties(){
        System.out.println(System.getProperty("user.home"));
        Path path = Paths.get("~/test/", "foo", "bar", "a.txt");
        System.out.println(path);
    }
}
