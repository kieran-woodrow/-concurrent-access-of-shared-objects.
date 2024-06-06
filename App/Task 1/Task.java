import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class Task {
   
    public String nameOfTask;
    public Task next;
    public Lock Rlock = new ReentrantLock();
    public int key;
    
    public Task(String taskName){

     nameOfTask=taskName;

    }

    public void lock(){
        Rlock.lock();
    }

    public void unlock(){

       Rlock.unlock();

    }

    public String getTaskName(){

        return nameOfTask;
    }


    public Task getNextTask()
    {
        return next;
    
    }

    public void setNextTask(Task nextTask)
    {
        next=nextTask;
    }

}
