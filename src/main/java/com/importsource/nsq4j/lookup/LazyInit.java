package com.importsource.nsq4j.lookup;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class LazyInit
{

  protected AtomicBoolean once = new AtomicBoolean(false);

  protected ReentrantLock lock = new ReentrantLock();

  public boolean start()
  {
    if (!this.once.get())
    {
      this.lock.lock();

      if (this.once.get()) {
        this.lock.unlock();
      }
    }
    return !this.once.get();
  }

  public void end() {
    this.once.set(true);
    this.lock.unlock();
  }

  public void reset() {
    this.once.set(false);
  }

  public boolean isInited()
  {
    if (this.once.get()) {
      return true;
    }
    if (this.lock.isLocked()) {
      return true;
    }
    return this.once.get();
  }
}