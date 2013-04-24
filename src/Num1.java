class Num1 {

    public static void main(String[] args) {
    }

    public static boolean estPremier(long n) {
        if (n <= 1) {
            throw new IllegalArgumentException();
        }
        double limite = Math.floor(Math.sqrt(n));
        for (int i = 2; i <= limite; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long prochainPremier(long n) {
        if (n <= 1) {
            throw new IllegalArgumentException();
        }
        if (n != 2 && n % 2 == 0) {
            n++;
        }
        while (!estPremier(n)) {
            n += 2;
        }
        return n;
    }
}
