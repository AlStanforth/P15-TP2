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
