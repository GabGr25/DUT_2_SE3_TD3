import javax.print.attribute.standard.JobKOctets;

public class AfficheCaratères {
    protected final int repeat=20;
    protected char first,last;
    static Object mutex_x=new Object();

    public AfficheCaratères(char first, char last) {
        this.first = first;
        this.last = last;
    }

    public void run(){
        try{
            for (int i = 0; i < repeat; i++) {
                synchronized (mutex_x) {
                    for (char c = first; c <= last; c++) {
                        System.out.print(c);
                        Thread.sleep(1);
                    }
                    System.out.println();
                    Thread.sleep(1);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
