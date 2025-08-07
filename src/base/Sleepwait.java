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
