package multithreading;
class Task1 extends Thread{
    public void run(){
        System.out.print("Task1 Started\n");
        for(int i=101;i<=199;i++)
            System.out.print(i+ " ");

        System.out.print("\n Task1 Done");
    }
}

class Task2 implements Runnable{

    @Override
    public void run(){
        System.out.print("Task2 Started\n");
        for(int i=201;i<=299;i++)
            System.out.print(i+ " ");

        System.out.print("\n Task2 Done");
    }
}
public class Multithreadin1 {
    public static void main(String[] args) throws InterruptedException {
        //when we have multiple task, for example 3 for loop each task will run one after the another
        //there is chance while running one task our cpu might not be fully utilized but even through
        //it will wait for first task to complete then it will complete the next task.

       // Threads is something which will help us to achieve parallelization.
        /* 2 ways to create threads-
          * extends thread
          * implements Runnable
         */

        Task1 task1 = new Task1();
         /*
         Setting priority
         task1.setPriority();
        it doesn't will always set it, it's just we are requesting it unto them whether it will set it or not
         */
        task1.setPriority(10);
        task1.start(); //task1.run() then is will run as normal method, will not run as parallelism
        Task2 task2 = new Task2();
        Thread thread = new Thread(task2);
        thread.start();

        //wait for task1 to complete
        task1.join();
        System.out.print("\n Task3 Started");
        for(int i=301;i<=399;i++)
            System.out.print(i+ " ");

        System.out.print("\n Task3 Done");

        /*
        Different state of Threads
        NEW; - Thread is ready but not started/executed(Ready to run but nobody has told to run)
        RUNNABLE; - if we are running multiple task they are waiting for CPU but kinda running
        RUNNING; - executing the thread means it is running
        BLOCKED/WAITING; - if we're calling the db and db is not connected then we are blocked,
         also waiting for other thread to complete the processing
        TERMINATED; - If the task is completed, it is dead and done executing

         */
        /*
        Thread.sleep();
        Thread.yield(); // I am ok to go into waiting state if the cpu is high
        synchronised - if a piece of data is shared b/w two threads - so any method is synchronised, and one thread is
        calling that method then other thread will until that method run is over by that thread.
        */


    }
}
