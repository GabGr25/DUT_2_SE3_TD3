import java.awt.*;

public class Action extends Thread{
    static final int n=5000;
    public void run(){
        for (int i = 0; i <n; i++) Exo1.x++;

    }
}
class Exo1{
    static int x=0;
    public static void main(String[] args) {
        Thread t1=new Action();
        Thread t2=new Action();
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }
        catch (Exception e ){
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(x);
    }
}
