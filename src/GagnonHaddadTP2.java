/**
 * @author Alexandre Gagnon
 * @author Rodric Haddad
 */

class GagnonHaddadTP2 {

    public static void main(String[] args) {
        // 1a
        long p = prochainPremier(9239);
        long q = prochainPremier(5683);
        if (estPremier(p) && estPremier(q)) {
            System.out.println("1a) p et q sont premiers.");
        }
        // 1b
        long n = p * q;
        System.out.format("1b) p:%d * q:%d = n:%d%n", p, q, n);
        // 2
        long a = 662, b = 414;
        long[] cl = euclideEtendu(a, b);
        System.out.format("2) %d(%d) + %d(%d) = %d%n", cl[1], a, cl[2], b, cl[0]);
        // ((d*e) % phiN)
        // 3
        long phiN = phi(n);
        long e = trouverE(1139, phiN);
        long d = inverseModulo(e, phiN);
        a = 5;
        long m = 11;
        System.out.format("3) %d * %d == %d (mod %d)%n", a, inverseModulo(a, m), euclideEtendu(a, m)[0], m);
        // 4
        a = 256; b = 450; m = 777;
        long c = exponentiationRapide(a, b, m);
        System.out.format("4) %d ^ %d == %d (mod %d)%n", a, b, c, m);
        // 5
        long[] signatureProf = {
            76310066, 2596885, 1136574, 15355199, 78110831,
            63789364, 24210018, 33518696, 41054765, 21115496
        };
        long nProf = 81072007, eProf = 9001;
        System.out.println("5) " + verifSignature(signatureProf, nProf, eProf));
        // 6
        String notreMsg = "CETTE PHRASE EST COURTOISE";
        long[] msgCrypter = crypter(notreMsg, nProf, eProf);
        System.out.println("6) Notre Message : " + notreMsg);
        System.out.print("6) Crypter : { " + msgCrypter[0]);
        for (int i = 1; i < msgCrypter.length; i++) {
            System.out.print(", " + msgCrypter[i]);
        }
        System.out.print(" }\n");
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
        while (!estPremier(num)) {
            num += 2;
        }
        return num;
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

    public static long inverseModulo(long a, long m) {
        long[] euclide = euclideEtendu(a, m);
        if (a <= 1 || m <= 1 || euclide[0] != 1) throw new IllegalArgumentException();

        long x0 = euclide[1];
        while (x0 < 0) {
            x0 += m;
        }
        return x0;
    }

    public static long phi(long n) {
        long nbPremRelatif = 1; //on compte le `1`
        for(int i = 2; i < n; i++) {
            if (euclideEtendu(n, i)[0] == 1) {
                nbPremRelatif++;
            }
        }
        return nbPremRelatif;
    }

    public static long trouverE(long s, long phiN) {
        if (s <= 1 || phiN <= 1) throw new IllegalArgumentException();

        while (euclideEtendu(phiN, s)[0] != 1) {
            s++;
        }
        return s;
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

    public static String verifSignature(long[] signature, long n, long e) {
        long[] signatureDecrypt = new long[signature.length];
        for (int i = 0; i < signature.length; i++) {
            signatureDecrypt[i] = exponentiationRapide(signature[i], e, n);
        }
        return Outils_A_DISTRIBUER.decoder(signatureDecrypt);
    }

    public static long[] crypter(String msg, long n, long e) {
        long[] msgEncode = Outils_A_DISTRIBUER.encoder(msg);
        long[] msgCrypt = new long[msgEncode.length];
        for (int i = 0; i < msgEncode.length; i++) {
            msgCrypt[i] = exponentiationRapide(msgEncode[i], e, n);
        }
        return msgCrypt;
    }
}
