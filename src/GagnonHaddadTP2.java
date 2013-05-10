/**
 * @author Alexandre Gagnon
 * @author Rodric Haddad
 */

class TP2_AlexandreGagnon_RodricHaddad {
    public static void main(String[] args) {

    }
}
class Num1 {

    public static long p, q, n;

    static {
        p = 9239; //Num1.prochainPremier(9234);
        q = 5683; //Num1.prochainPremier(5678);
        n = p * q; // 52_505_237
    }

    public static void main(String[] args) {
        System.out.format("p:%d * q:%d = n:%d%n", Num1.p, Num1.q, Num1.n);
    }

    public static boolean estPremier(long num) {
        if (num <= 1) throw new IllegalArgumentException();

        double limite = Math.floor(Math.sqrt(num));
        for (int i = 2; i <= limite; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long prochainPremier(long num) {
        if (num <= 1) throw new IllegalArgumentException();

        if (num != 2 && num % 2 == 0) {
            num++;
        }
        while (!Num1.estPremier(num)) {
            num += 2;
        }
        return num;
    }
}
class Num2 {

    public static void main(String[] args) {
        long a = 662, b = 414;
        long[] cl = Num2.euclideEtendu(a, b);
        System.out.format("%d(%d) + %d(%d) = %d%n", cl[1], a, cl[2], b, cl[0]);
    }

    public static long[] euclideEtendu(long x, long y) {
        if (x <= 1 || y <= 1) throw new IllegalArgumentException();

        long r, q, xTemp, yTemp,
            x0 = 1, x1 = 0,
            y0 = 0, y1 = 1;

        while (y != 0) {
            r = x % y;
            q = x / y;
            x = y; y = r;
            xTemp = x0 - q * x1; yTemp = y0 - q * y1;
            x0 = x1; y0 = y1;
            x1 = xTemp; y1 = yTemp;
        }
        return new long[] {x, x0, y0}; //pgcd, x, y
    }
}
class Num3 {

    public static long e, d, phiN;

    static {
        phiN = 52490316; //phi(Num1.n);
        e = 1139; //trouverE(1139, phiN);
        d = 4838879; //inverseModulo(e, phiN);
        //System.out.println(((d*e) % phiN));
    }

    public static void main(String[] args) {
        long a = 5, m = 11;
        System.out.format("%d * %d == %d (mod %d)%n", a, inverseModulo(a, m), Num2.euclideEtendu(a, m)[0], m);
    }

    public static long inverseModulo(long a, long m) {
        if (a <= 1 || m <= 1 || Num2.euclideEtendu(a, m)[0] != 1) throw new IllegalArgumentException();

        long x0 = Num2.euclideEtendu(a, m)[1];
        while (x0 < 0) {
            x0 += m;
        }
        return x0;
    }

    public static long phi(long n) {
        long nbPremRelatif = 1; //on compte le `1`
        for(int i = 2; i < n; i++) {
            if (Num2.euclideEtendu(n, i)[0] == 1) {
                nbPremRelatif++;
            }
        }
        return nbPremRelatif;
    }

    public static long trouverE(long s, long phiN) {
        if (s <= 1 || phiN <= 1) throw new IllegalArgumentException();

        while (Num2.euclideEtendu(phiN, s)[0] != 1) {
            s++;
        }
        return s;
    }
}
class Num4 {

    public static void main(String[] args) {
        long a = 256, b = 450, m = 777;
        long c = exponentiationRapide(a, b, m);
        System.out.format("%d ^ %d == %d (mod %d)%n", a, b, c, m);
    }

    public static long exponentiationRapide(long a, long b, long m) {
         if (a < 1 || b < 1 || m <= 1) throw new IllegalArgumentException();

         long produit = 1, puissance = a, quotient = b;

         do {
             if (quotient % 2 != 0) {
                 produit = produit * puissance;
                 produit = produit % m;
             }
             puissance = puissance * puissance;
             puissance = puissance % m;
             quotient = quotient / 2;
         } while(quotient != 0);

         return produit;
    }
}
class Num5 {

    public static long[] signatureProf = {
        76310066, 2596885, 1136574, 15355199, 78110831,
        63789364, 24210018, 33518696, 41054765, 21115496
    };

    public static long
        nProf = 81072007,
        eProf = 9001;

    public static String signatureDecoded;


    static {
        long[] signatureDecrypt = new long[Num5.signatureProf.length];
        for (int i = 0; i < Num5.signatureProf.length; i++) {
            signatureDecrypt[i] = Num4.exponentiationRapide(Num5.signatureProf[i], eProf, nProf);
        }

        signatureDecoded = Outils_A_DISTRIBUER.decoder(signatureDecrypt);
    }

    public static void main(String[] args) {
        System.out.println(signatureDecoded);
    }

}
class Num6 {

    public static long
        nProf = Num5.nProf,
        eProf = Num5.eProf;

    public static String notreMsg = "CETTE PHRASE EST COURTOISE";
    public static long[]
        msgEncoded = Outils_A_DISTRIBUER.encoder(notreMsg),
        msgCrypter = new long[msgEncoded.length];

    static {
        for (int i = 0; i < msgEncoded.length; i++) {
            msgCrypter[i] = Num4.exponentiationRapide(msgEncoded[i], eProf, nProf);
        }
    }

    public static void main(String[] args) {
        System.out.println("Notre Message : " + notreMsg);
        System.out.print("Crypter : { " + msgCrypter[0]);
        for (int i = 1; i < msgCrypter.length; i++) {
            System.out.print(", " + msgCrypter[i]);
        }
        System.out.print(" }\n");
    }

}
