package com.hch;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class MyTest {
    @Test
    public void testSystemProperties() {
        System.out.println(System.getProperty("user.home"));
        Path path = Paths.get("~/test/", "foo", "bar", "a.txt");
        System.out.println(path);
    }

    @Test
    public void testException() {
        try {
            try {
                throw new Exception("1");
            } catch (Exception e) {
                Exception se = new Exception("2");
                se.initCause(e);  // 等价于new Exception("2", e);  如果去除将看不到cause打印
                throw se;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDivide() {
        System.out.println(1 % 0);
    }

    @Test
    public void testSynchronized() {
        System.out.println(new File("/Users/hch") == new File("/Users/hch"));
        System.out.println(new File("/Users/hch").equals(new File("/Users/hch")));

        System.out.println(new File("/Users/hch/").getName().intern() == new File("/Users/hch").getName().intern());
        System.out.println(new File("/Users/hch").getName().equals(new File("/Users/hch").getName()));
    }

    @Test
    public void testStringFormat() {
        System.out.println(String.format("hi, %s,%s", new Object[1]));
    }

    @Test
    public void test() {
        System.out.println("{\"code\":10500,\"message\":\"服务器未知错误\",\"data\":\"hello, hch\"}".getBytes().length);
        System.out.println("\"服".getBytes().length);
    }

    @Test
    public void testProperties() throws IOException {
        Properties properties = new Properties();
        // spring factories加载配置的核心方法
        properties.load(MyTest.class.getClassLoader().getResourceAsStream("test.properties"));
        for (Map.Entry<Object, Object> set : properties.entrySet()) {
            System.out.println(set.getValue());
        }
    }

    public static void main(String[] args) {
        // 给虚拟机传递参数-Dauthor=hch
        System.out.println(System.getProperty("author"));
        // 这个property不是os的环境变量
        System.out.println(System.getProperty("PATH"));
        // exam2();
    }

    private static void exam1(){
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.println(i);
        int firstNum = i / 1000;
        int secondNum = i / 100 % 10;
        int thirdNum = i / 10 % 10;
        int fourthNum = i % 10;
        firstNum = firstNum < 5 ? firstNum + 5 : firstNum - 5;
        secondNum = secondNum < 5 ? secondNum + 5 : secondNum - 5;
        thirdNum = thirdNum < 5 ? thirdNum + 5 : thirdNum - 5;
        fourthNum = fourthNum < 5 ? fourthNum + 5 : fourthNum - 5;
        System.out.println(fourthNum * 1000 + thirdNum * 100 + secondNum * 10 + firstNum);
    }

    private static void exam2(){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] sChars = s.toCharArray();
        char[] chars = new char[sChars.length];
        for (int i = 0; i < sChars.length; i++) {
            chars[i] = (char) (sChars[i] ^ '8');
        }
        System.out.println(new String(chars));
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ '8');
        }
        System.out.println(new String(chars));
    }
}
