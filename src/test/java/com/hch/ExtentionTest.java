package com.hch;

/**
 * @author hch
 * @since 2020/8/30
 */
public class ExtentionTest {
    public static void main(String[] args) {
        new C().m();
    }

}

class P {
    P() {
        System.out.println("P");
    }

    public void m() {
        System.out.println(this.getClass() + " m");
    }
}

class C extends P {
    C() {
        System.out.println("C");
    }

    @Override
    public void m() {
        System.out.println("before");
        super.m();
        System.out.println("after");

    }
}
