package ctci.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CallInOrder implements Runnable {
//  Semaphore semaphore = new Semaphore(3);
  private Foo foo;
  private int id;
  public CallInOrder(Foo foo, int id){
    this.foo = foo;
    this.id = id;
  }

  @Override
  public void run() {
    while (!(this.foo.first && this.foo.second && this.foo.third)){
      runInOrder();
    }

  }

  private void runInOrder() {
    try{
      boolean aquired = this.foo.lock.tryLock();
      if(aquired){
        if(this.id == 0 && !this.foo.first){
          this.foo.first();
        }else if(this.id == 1 && this.foo.first && !this.foo.second){
          this.foo.second();
        } else if(this.id == 2 && this.foo.second && this.foo.first && !this.foo.third){
          this.foo.third();
        } else {
          throw new RuntimeException("wrong thread");
        }
      }
    }finally{
      this.foo.lock.unlock();
    }
  }

  private static class Foo {
    volatile boolean first = false;
    volatile boolean second = false;
    volatile boolean third = false;
    private Lock lock;
    public Foo(){
      lock = new ReentrantLock();
    }
    public void first(){
      first = true;
      System.out.println("RUNNING first");
    }
    public void second(){
      second = true;
      System.out.println("RUNNING second");
    }
    public void third(){
      third = true;
      System.out.println("RUNNING third");
    }
  }

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Foo foo = new Foo();
    executorService.submit(new CallInOrder(foo,0) );
    executorService.submit(new CallInOrder(foo,1) );
    executorService.submit(new CallInOrder(foo,2) );
    executorService.shutdown();

  }

}
