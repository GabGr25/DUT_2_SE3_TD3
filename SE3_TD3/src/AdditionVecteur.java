import java.util.Random;

class Tableau {
    public static int MIN_VALUE = 1;
    public static int MAX_VALUE = 200;
    public static Random rnd = new Random();
    public static int[] creerTableau (int taille, int min , int max) {
        int[] tableau = new int[taille];
        int diff = max-min;
        for(int i=0;i<tableau.length;i++)
            tableau[i]=rnd.nextInt(diff)+min;
        return tableau;
    }
    public static int[] creerTableau (int taille) {
        return creerTableau(taille,MIN_VALUE, MAX_VALUE);
    }
    public static void afficher(int [] tab) {
        for (int i=0;i<tab.length;i++) {
            System.out.println("tab["+i+"]="+tab[i]);
        }
    }
}

public class AdditionVecteur extends Thread {
    protected int beg, der, posMax, max;
    protected int[] tab;

    public AdditionVecteur(int beg, int der, int[] tab) {
        this.beg = beg;
        this.der = der;
        this.posMax = 0;
        this.tab = tab;
    }

    public int getPosMax() {
        return posMax;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        for (int i = beg; i < der; i++) {
            if (tab[i] > tab[posMax]) {
                posMax = i;
            }
        }
        max = tab[posMax];
    }

    public static void main(String[] args) {
        final int tabSize = 15;
        final int nbThread = 4;

        AdditionVecteur[] threads = new AdditionVecteur[nbThread];

        try{
            int[] tab = Tableau.creerTableau(tabSize, 1, 200);
            //Tableau.afficher(tab);
            int beg = 0;
            int end = 0;
            for (int i = 0; i < nbThread; i++) { // Création des threads
                beg = end;
                int sliceSize = tab.length / nbThread;
                if(i<tab.length%nbThread) {
                    sliceSize++;
                }
                end = beg + sliceSize;
                threads[i] = new AdditionVecteur(beg, end, tab);
            }

            for (int i = 0; i < nbThread; i++) { // Lancement
                threads[i].start();
            }

            for (int i = 0; i < nbThread; i++) { // Terminaison
                threads[i].join();
            }

            int posMax = threads[0].getPosMax();
            System.out.println("Thread n°"+0+" max="+tab[threads[0].getPosMax()]+" pos=" + threads[0].getPosMax());
            for (int i = 1; i < nbThread; i++) {
                System.out.println("Thread n°"+i+" max="+threads[i].getMax()+" pos=" + threads[i].getPosMax());
                if (tab[threads[i].getPosMax()] > tab[posMax]) {
                    posMax = threads[i].getPosMax();
                }
            }
            System.out.println("posMax= " + posMax + " avec max= " + tab[posMax]);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}