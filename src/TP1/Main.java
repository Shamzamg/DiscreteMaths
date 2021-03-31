package TP1;

public class Main {


    public static int[] EuclideEtendu(int a, int b){
        if(b==0){
            int retTab [] = {a,1,0};
            return retTab;
        }

        int tempTab [] = EuclideEtendu(b,a%b);

        int retTab [] = {tempTab[0], tempTab[2], tempTab[1] - a/b*tempTab[2]};
        return retTab;
    }

    public static int ExpMod(int a, int k, int n){

        int p;
        for(p=1;k>0;k/=2){

            if(k%2 != 0) p = (p*a)%n;

            a = (a*a)%n;

        }

        return p;
    }

    public static boolean TestFermat(int n){
        if(n == 2)
            return true;
        return (Math.pow(2,(n - 1))%n == 1);
    }

    public static boolean PrimaliteNaif(int n){
        for(int i=2;i<n;i++){
            if(n%i == 0)
                return false;
        }
        return true;
    }

    public static void question4(){
        for(int i=1;i<=30;i++){
            double nb = 0;
            double prem = 0;
            for(int j=(int)Math.pow(2,i);j<(int)Math.pow(2,i+1); j++){
                if(PrimaliteNaif(j))
                    prem++;
                nb++;
            }
            System.out.println(prem/nb);
        }
    }

    public static void question5(){
        for(int i=1;i<=30;i++){
            double nb = 0;
            double premNaif = 0;
            double premFermat = 0;
            for(int j=(int)Math.pow(2,i);j<(int)Math.pow(2,i+1); j++){
                if(PrimaliteNaif(j))
                    premNaif++;
                if(TestFermat(j))
                    premFermat++;
                nb++;
            }
            System.out.println(100*(1 - ((premNaif/nb) / (premFermat/nb))));
        }
    }

    public static void question6(){
        //on prend des puissances de 2 "relativement" élevées

        for(int i=15;i<=17;i++){
            long startNaif, endNaif, startFermat, endFermat;
            long totalNaif = 0, totalFermat = 0;
            for(int j=(int)Math.pow(2,i);j<(int)Math.pow(2,i+1); j++){
                startNaif = System.currentTimeMillis();
                PrimaliteNaif(j);
                endNaif = System.currentTimeMillis();

                startFermat = System.currentTimeMillis();
                TestFermat(j);
                endFermat = System.currentTimeMillis();

                totalNaif += (endNaif - startNaif);
                totalFermat += (endFermat - startFermat);
            }
            System.out.println("Pour i=" + i + " , temps total Naif: " + totalNaif + " ms;  temps total Fermat: " + totalFermat + " ms");
        }
    }

    public static void main(String[] args) {

        /*
        //Euclide etendu
        int retTab [] = EuclideEtendu(6497,3139);
        System.out.println(retTab[0] + " " + retTab[1] + " " + retTab[2]);

        //Exponentielle modulaire
        System.out.println(ExpMod(4,13,497));

        //Test primalite Fermat antique
        System.out.println(TestFermat(5));

        //Test primalite naif
        System.out.println(PrimaliteNaif(5));

        //proportions de nombres premiers sur N inter (2^i; 2^(i+1)) pour i=1,...,30
        //on constate que cette proportion décroit au fur et à mesure que i augmente
        //les calculs sont égalements de plus en plus longs
        question4();

        //taux d'erreur du TestFermat, à partir de PrimaliteNaif
        question5();*/

        //compare asymptotiquement l'efficacité de TestFermat et PrimaliteNaif
        question6();
    }

}
