package base;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MulThread {
}
class CallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "上岸了！";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableTask task = new CallableTask();
        FutureTask<String> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}

class RunnableTask implements Runnable {
    public void run() {
        System.out.println("看完⼆哥的 Java 进阶之路，上岸了!");
    }
    public static void main(String[] args) {
        RunnableTask task = new RunnableTask();
        Thread thread = new Thread(task);
        thread.start();
    }
}

class ThreadTask extends Thread {
    public void run() {
        System.out.println("看完⼆哥的 Java 进阶之路，上岸了!");
    }
    public static void main(String[] args) {
        ThreadTask task = new ThreadTask();
        task.start();
    }
}

class ThreadLister {
    public static void main(String[] args) {
// 获取所有线程的堆栈跟踪
        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
        for (Thread thread : threads.keySet()) {
            System.out.println("Thread: " + thread.getName() + " (ID=" + thread.getId() +
                    ")");
        }
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start(); // 正确的⽅式，创建⼀个新线程，并在新线程中执⾏ run()
        t1.run(); // 仅在主线程中执⾏ run()，没有创建新线程
    }
}
