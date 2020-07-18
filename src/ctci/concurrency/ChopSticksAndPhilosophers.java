package ctci.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopSticksAndPhilosophers {

  private static class Chopstick {
    private Lock lock;
    private int id;

    public Chopstick(int id) {
      lock = new ReentrantLock();
      id = id;
    }

    public void pickUp() {
      lock.lock();
    }

    public void putDown() {
      lock.unlock();
    }

    public int getNumber() {
      return this.id;
    }
  }

  private static class Philosopher implements Runnable {
    boolean eaten = false;
    private Chopstick lower, higher;
    private int id;

    public Philosopher(int id, Chopstick left, Chopstick right) {
      this.id = id;
      this.lower = left.getNumber() < right.getNumber() ? left : right;
      this.higher = left.getNumber() < right.getNumber() ? right : left;
    }

    public void eat() throws InterruptedException {
      boolean aquiredLeft = false;
      boolean aquiredRight = false;
      try {
        aquiredLeft = lower.lock.tryLock();
        if (aquiredLeft) {
          System.out.println("LEFT AVAILABLE " + this.id + Thread.currentThread().getName());
          aquiredRight = higher.lock.tryLock();
          if (aquiredRight) {
            System.out.println("RIGHT AVAILABLE " + this.id + Thread.currentThread().getName());
            Thread.sleep(1);
            this.eaten = true;
            System.out.println("Done eating " + this.id + Thread.currentThread().getName());
          } else {
            System.out.println(
                "right NOT available for " + this.id + Thread.currentThread().getName());
          }
        } else {
          System.out.println(
              "left NOT available for " + this.id + Thread.currentThread().getName());
        }
      } finally {
        if (aquiredRight) {
          higher.lock.unlock();
        }
        if (aquiredLeft) {
          lower.lock.unlock();
        }
      }
    }

    @Override
    public void run() {
      while (!eaten) {
        try {
          eat();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    Chopstick zero = new Chopstick(0);
    Chopstick one = new Chopstick(1);
    Chopstick two = new Chopstick(2);
    Chopstick three = new Chopstick(3);
    Chopstick four = new Chopstick(4);
    executorService.submit(new Philosopher(0, zero, one));
    executorService.submit(new Philosopher(1, one, two));
    executorService.submit(new Philosopher(2, two, three));
    executorService.submit(new Philosopher(3, three, four));
    executorService.submit(new Philosopher(4, four, zero));
    executorService.shutdown();
  }
}
