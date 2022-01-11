class Max extends Thread {

    private static int[] tableau;
    private int nbThread, numThread;
    private int max;


    public Max(int[] tableau ,int nbThread, int numThread){
        this.tableau= tableau;
        this.nbThread=nbThread;
        this.numThread=numThread;
    }

    public int getMax() {
        return this.max;
    }

    public static int[] getTableau() {
        return tableau;
    }

    @Override
    public void run() {
        int m=-1;
        for (int i=((tableau.length-1)/nbThread)*numThread;i<=((tableau.length-1)/nbThread)*(numThread+1);i++){
            if (tableau[i]>m)m=tableau[i];
        }
        max=m;
    }


    public static void main(String[] args) {
        int[] tabInt=Tableau.creerTableau(5);
        Tableau.afficher(tabInt);
        final int nbThread=4;
        Max[] tabThreads = new Max[nbThread];
        for (int i=0;i<nbThread;i++){
            tabThreads[i] =new Max(tabInt, nbThread,i);
        }

        for (int i=0;i<nbThread;i++){
            tabThreads[i].start();
        }
        for (int i=0;i<nbThread;i++){
            try {
                tabThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i=0;i<nbThread;i++){
            System.out.println(tabThreads[i].getMax());
        }
    }
}