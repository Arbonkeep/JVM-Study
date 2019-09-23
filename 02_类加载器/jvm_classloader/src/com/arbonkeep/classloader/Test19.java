package com.arbonkeep.classloader;

public class Test19 implements Runnable{
    private Thread thread;

    public Test19() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader = this.thread.getContextClassLoader();

        this.thread.setContextClassLoader(classLoader);

        System.out.println("class: " + classLoader.getClass());

        System.out.println("parent: " + classLoader.getParent());
    }

    public static void main(String[] args) {
        new Test19();

        /*
        如果没有通过setContextClassLoader(ClassLoader c1)来进行设置的话，线程将继承其父线程的类加载器。
        Java应用运行时的初始线程的上下文类加载器是系统类加载器，在线程中运行的代码可以通过该类加载器来加载
        类与资源

        根据上述我们知道class为AppCalssLoader，Parent为ExtClassLoader
         */
    }
}
