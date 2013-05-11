/**
 * @author Alexandre Gagnon
 * @author Rodric Haddad
 */

class GagnonHaddadTP2 {

    public static void main(String[] args) {
        // 1
        echoHeader("Question 1");
        long p = prochainPremier(9239);
        long q = prochainPremier(5683);
        long n = p * q;
        System.out.format("p:%d * q:%d = n:%d%n", p, q, n);
        if (estPremier(p) && estPremier(q)) {
            echo("p et q sont premiers");
        } else {
            echoErr("p et/ou q ne sont pas premiers");
        }
        if (n > 27272727) {
            echo("n est superieur a 27272727");
        } else {
            echoErr("n n'est pas superieur a 27272727");
        }

        // 2
        echoHeader("Question 2");
        long a = 662, b = 414;
        long[] cl = euclideEtendu(a, b);
        System.out.format("Test avec %d et %d: %d(%d) + %d(%d) = %d%n", a, b, cl[1], a, cl[2], b, cl[0]);
        if ((a * cl[1] + b * cl[2]) == cl[0]) {
            echo("Euclide Etendu marche");
        } else {
            echoErr("Euclide Etendu ne marche pas");
        }
        // ((d*e) % phiN)

        // 3
        echoHeader("Question 3");
        long phiN = phi(n);
        long e = trouverE(1139, phiN);
        long d = inverseModulo(e, phiN);
        a = 5;
        long m = 11;
        echo("Le phi(n) trouvee est : " + phiN);
        echo("Le e trouvee est : " + e);
        echo("Le d trouvee est : " + d);
        System.out.format("%d * %d == %d (mod %d)%n", d, e, 1, phiN);
        if ( ((d*e) % phiN) == 1 ) {
            echo("Le d est bon");
        } else {
            echoErr("Le d n'est pas bon");
        }

        // 4
        echoHeader("Question 4");
        //a = 256; b = 450; m = 777; trop gros pour le Math.pow
        a = 3; b = 11; m = 5;
        long c = exponentiationRapide(a, b, m);
        System.out.format("Test expo. rapide: %d ^ %d == %d (mod %d)%n", a, b, c, m);
        if (Math.pow(a, b) % m == c) {
            echo("L'exponentiation rapide marche");
        } else {
            echoErr("L'exponentiation rapide ne marche pas");
        }

        // 5
        echoHeader("Question 5");
        long[] signatureProf = {
            76310066, 2596885, 1136574, 15355199, 78110831,
            63789364, 24210018, 33518696, 41054765, 21115496
        };
        long nProf = 81072007, eProf = 9001;
        echo("Equipe 19, signature/message du prof: " + verifSignature(signatureProf, nProf, eProf));

        // 6
        echoHeader("Question 6");
        String notreMsg = "CETTE PHRASE EST COURTOISE";
        long[] msgCrypter = crypter(notreMsg, nProf, eProf);
        echo("Notre message secret : " + notreMsg);
        System.out.print("Message crypte avec la cle publique du prof :\n{ " + msgCrypter[0]);
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

    //aide
    public static void echo(String msg) {
        System.out.println(msg);
    }

    public static void echoHeader(String msg) {
        echo("\n========== " + msg + " ==========");
    }

    public static void echoErr(String msg) {
        echo("!! " + msg + " !!");
    }
}
