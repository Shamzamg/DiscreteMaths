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
        return (Math.pow(2,(n - 1))%n == 1);
    }

    public static int TestPrimaliteNaif(){

        return 0;
    }

    public static void main(String[] args) {

        //Euclide etendu
        int retTab [] = EuclideEtendu(6497,3139);
        System.out.println(retTab[0] + " " + retTab[1] + " " + retTab[2]);

        //Exponentielle modulaire
        System.out.println(ExpMod(4,13,497));

        //Test primalite Fermat antique
        System.out.println(TestFermat(6));

    }

}
