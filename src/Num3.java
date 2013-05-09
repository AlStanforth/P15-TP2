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
