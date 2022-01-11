import java.awt.*;
class X{
    public int x=0;
    synchronized public void incr(){//pas besoin de mettre static
        x++;
    }

}

public class Action_Exo2 extends Thread{
    static final int n=5000;
    public void run(){
        for (int i = 0; i <n; i++)
            Exo2.x.incr();
    }
}
class Exo2{
    static X x= new X();
    public static void main(String[] args) {
        Thread t1=new Action_Exo2();
        Thread t2=new Action_Exo2();
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
        System.out.println(x.x);//rajouter .x car sinon point objet X x la on veux la variable x de x donc x.x
    }
}
