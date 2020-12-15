package com.hch;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.cert.X509Certificate;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.concurrent.*;

import org.apache.commons.codec.binary.Hex;

public class MyTest {
    private static int a = 1;

    static {
        a = 0;
        b = 0;
    }

    private static int b = 2;

    public static void main(String[] args) throws InterruptedException {
        testBlockingQ();

        System.out.println(a);
        System.out.println(b);
        // 给虚拟机传递参数-Dauthor=hch
        System.out.println(System.getProperty("author"));
        // 这个property不是os的环境变量
        System.out.println(System.getProperty("PATH"));
        // exam2();
        MyTest o = new MyTest();
        TimeUnit.SECONDS.sleep(300);

        // new AnnotationConfigApplicationContext()

    }

    public static void testBlockingQ() throws InterruptedException {
        BlockingQueue<String> strings = new LinkedBlockingDeque<>(1);

    }

    @Test
    public void testGenerateKey() throws Exception {
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(1024);
        KeyPair kp = kpGen.generateKeyPair();
        try (FileOutputStream f1 = new FileOutputStream("/tmp/public.key");
             FileOutputStream f2 = new FileOutputStream("/tmp/private.key");) {
            f1.write(kp.getPublic().getEncoded());
            f2.write(kp.getPrivate().getEncoded());
        }
    }

    @Test
    public void testDigest() throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        try (FileInputStream f3 = new FileInputStream("/tmp/test.txt");) {
            int i;
            while ((i = f3.read()) != -1) {
                md.update((byte) i);
            }
        }
        byte[] digest = md.digest();

        System.out.println(Base64.getEncoder().encodeToString(digest) + "  " + digest.length);
    }

    @Test
    public void testDecodeFromRSA() throws Exception {
        try (FileInputStream f1 = new FileInputStream("/tmp/public.key");
             FileInputStream f5 = new FileInputStream("/tmp/test.sig");
        ) {
            byte[] tmp = new byte[2];
            int i;

            byte[] publicKeyBytes = new byte[f1.available()];
            f1.read(publicKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            while ((i = f5.read()) != -1) {
                cipher.update(new byte[]{(byte) i});
            }
            byte[] bytes = cipher.doFinal();
            System.out.println(Base64.getEncoder().encodeToString(bytes) + "  " + bytes.length);

        }

    }

    @Test
    public void testSecure1() throws Exception {
        KeyFactory kf = KeyFactory.getInstance("RSA");
        try (FileInputStream f1 = new FileInputStream("/tmp/public.key");
             FileInputStream f2 = new FileInputStream("/tmp/private.key");
             FileInputStream f3 = new FileInputStream("/tmp/test.txt");
             FileInputStream f5 = new FileInputStream("/tmp/test.sig");
             // FileOutputStream f4 = new FileOutputStream("/tmp/test.sig");
        ) {
            byte[] publicKeyBytes = new byte[f1.available()];
            byte[] privateKeyBytes = new byte[f2.available()];
            byte[] sigBytes = new byte[f5.available()];
            f1.read(publicKeyBytes);
            f2.read(privateKeyBytes);
            f5.read(sigBytes);

            Signature signature = Signature.getInstance("SHA1withRSA");
            int i;
            PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
            signature.initVerify(publicKey);
            while ((i = f3.read()) != -1) {
                signature.update((byte) i);
            }
            System.out.println(signature.verify(sigBytes));

            // PrivateKey privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
            // signature.initSign(privateKey);

            // while ((i = f3.read()) != -1) {
            //     signature.update((byte) i);
            // }
            // byte[] sign = signature.sign();
            // f4.write(sign);
        }
    }

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

    private static void exam1() {
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

    private static void exam2() {
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

    @Test
    public void testScheduler() throws InterruptedException {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1);
        poolExecutor.scheduleWithFixedDelay(() -> {
            System.out.println("aaa");
            throw new RuntimeException("test");
        }, 1, 1, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);

    }

    @Test
    public void testEncoding() throws UnsupportedEncodingException {
        System.out.println(new String(new byte[]{(byte) 0b11100100, (byte) 0b10111000, (byte) 0b10101101}, "UTF-8"));
        System.out.println(new String(new byte[]{(byte) 0b11010110, (byte) 0b11010000}, "GBK"));
        System.out.println(new String(new byte[]{(byte) 0b11010110, (byte) 0b11010000}, "UTF-8"));

        System.out.println(URLEncoder.encode("#不爱学习的灰灰", "UTF-8"));
    }

    @Test
    public void testHexEncoding() {
        System.out.println(Hex.encodeHex(new byte[]{(byte) 0b11100100, (byte) 0b10111000, (byte) 0b10101101}));
    }

    @Test
    public void testBase64Encoding() {
        System.out.println(Base64.getEncoder().encodeToString(new byte[]{(byte) 0b11100100, (byte) 0b10111000, (byte) 0b10101101}));

    }

    @Test
    public void testAli() {
        System.out.println(Math.round(11.5));
        System.out.println(Math.round(-11.5));
        String s = (String) new Object();
        System.out.println(s);
    }

    @Test
    public void testCipher() throws Exception {
        // 原文:
        String message = "Hello, world!";
        System.out.println("Message: " + message);
        // 256位密钥 = 32 bytes Key:
        byte[] key = "1234567890abcdef1234567890abcdef".getBytes("UTF-8");
        // 加密:
        byte[] data = message.getBytes("UTF-8");
        Map<String, byte[]> encryptedResult = encrypt(key, data);
        byte[] ciphertext = encryptedResult.get("ciphertext");
        byte[] iv = encryptedResult.get("iv");
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(ciphertext));
        // 解密:
        byte[] decrypted = decrypt(key, iv, ciphertext);
        System.out.println("Decrypted: " + new String(decrypted, "UTF-8"));
    }

    // 加密:
    public static Map<String, byte[]> encrypt(byte[] key, byte[] plaintext) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        // CBC模式需要生成一个16 bytes的initialization vector:
        SecureRandom sr = SecureRandom.getInstanceStrong();
        byte[] iv = sr.generateSeed(16);
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);
        byte[] ciphertext = cipher.doFinal(plaintext);
        // IV不需要保密，把IV和密文一起返回:
        Map<String, byte[]> result = new HashMap<>();
        result.put("iv", iv);
        result.put("ciphertext", ciphertext);
        return result;
    }

    // 解密:
    public static byte[] decrypt(byte[] key, byte[] iv, byte[] ciphertext) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
        return cipher.doFinal(ciphertext);
    }

    @Test
    public void testRSA(){

    }

    @Override
    public String toString() {
        System.out.println("to string");
        return super.toString();
    }
}
