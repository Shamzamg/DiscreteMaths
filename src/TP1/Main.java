package TP1;

import java.util.Random;

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

    public static int InverseMod(int a, int b){
        int tab [] = EuclideEtendu(a, b);
        try{
            return Math.floorMod(tab[1],b);
        } catch(Exception e){
            System.out.println("pas inversible !");
        }
        return 0;
    }

    public static long ExpMod(long a, long k, long n){
        long p;
        for(p=1;k>0;k/=2){
            if(k%2 != 0) p=(p*a)%n;
            a = (a*a)%n;
        }
        return p;
    }

    public static boolean TestFermat(int n){
        if(n < 2) return false;
        if(n == 2) return true;
        return ExpMod(2, n-1, n) == 1;
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
        System.out.println("Taux d'erreur Fermat en % :");
        for(int i=1;i<=30;i++){
            double nb = 0;
            double erreur = 0;
            for(int j=(int)Math.pow(2,i);j<(int)Math.pow(2,i+1); j++){
                if(PrimaliteNaif(j) != TestFermat(j))
                    erreur++;
                nb++;
            }
            System.out.println("i = " + i + "; erreur = " + (erreur/nb)*100 + " %");
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

    //renvoie un entier premier de k bits choisi aléatoirement
    //c'est à dire un entier premier aléatoire dans [2^(k-1); 2^k - 1]
    public static void GenPremiers(int k){

        Random r = new Random();
        int min = (int)Math.pow(2,k-1);
        int max = (int) Math.pow(2,k) - 1;

        int premier = r.nextInt((max - min) + 1) + min;
        while(!PrimaliteNaif(premier))
            premier = r.nextInt((max - min) + 1) + min;

        System.out.println(premier);
    }

    //question 8
    //retourne la factorisation de n = pq produit de deux nombres premiers
    public static void PhiToFact(int n, int phi_n){
        int a = -1;
        int b = n + 1 - phi_n;
        int c = -n;
        int delta = b*b - 4*a*c;
        if (delta > 0) {
            int sol1 = (int) ((-b - Math.sqrt(delta)) / (2*a));
            int sol2 = (int) ((-b + Math.sqrt(delta)) / (2*a));
            System.out.println("delta = " + delta);
            System.out.println(sol1 + " * " + sol2);
            System.out.println("= " + sol1 * sol2);
            if (sol1 * sol2 == n) System.out.println("OK");
            else System.err.println("Mauvais résultat");
        } else if (delta == 0) {
            int sol = -b / (2*a);
            System.err.println("delta = 0");
            System.err.println(sol);
        } else {
            System.err.println("delta = " + delta);
            System.out.println("1 * " + n);
        }
    }

    //question 9

    //fonction Phi qui renvoie le nombre d'entiers dans [1, n] premiers avec n
    public static int Phi(int n){
        int phi_n = n;

        for(int i=1;i<=n;i++){
            for(int j=2; j <= i; j++)
            {
                if(n%j==0 && i%j==0) {
                    phi_n--;
                    break;
                };
            }
        }
        return phi_n;
    }


    //vérifie que Phi(pq) = (p-1)(q-1) lorsque p, q sont premiers
    public static boolean VerifPhi(int p, int q){
        //si p est premier
        if(PrimaliteNaif(p)){
            //si q est également premier
            if(PrimaliteNaif(q)){
                return (Phi(p*q) == (p-1)*(q-1));
            }
        }

        //si p ou q n'est pas premier
        return false;
    }

    //question 10


    //question 11
    public static long A1(int n1, int n2, int n3, int e, int M1, int M2, int M3){
        int d = InverseMod(e, Phi(n1));
        long m = ExpMod(M1,d,n1);

        return m;
    }

    public static void main(String[] args) {


        question5();
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
        question5();

        //compare asymptotiquement l'efficacité de TestFermat et PrimaliteNaif
        question6();*/

        //retourne un entier premier de k bits choisi aléatoirement (Question 7)
        //GenPremiers(4);
        //System.out.print(VerifPhi(3,5));
        //System.out.print(Phi(15));

        //on prend m = 21 (puisque 98=21^{3} mod 187)
        //System.out.print(A1(187, 0, 0, 3, 98, 0, 0));
    }

}
