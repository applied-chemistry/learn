package base;

public class Sleepwait {
}

class SleepDoesNotReleaseLock {
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread sleepingThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1 会继续持有锁，并且进⼊睡眠状态");
                try {
                    Thread.sleep(5000);
//                    lock.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1 醒来了，并且释放了锁");
            }
        });
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 进⼊同步代码块");
            }
        });
        sleepingThread.start();
        Thread.sleep(1000);
        waitingThread.start();

    }
}

class WaitReleasesLock {
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1 持有锁，准备等待 5 秒");
                    lock.wait(5000);
                    System.out.println("Thread 1 醒来了，并且退出同步代码块");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread notifyingThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 尝试唤醒等待中的线程");
                lock.notify();
                System.out.println("Thread 2 执⾏完了 notify");
            }
        });
        waitingThread.start();
        Thread.sleep(1000);
        notifyingThread.start();
    }
}
