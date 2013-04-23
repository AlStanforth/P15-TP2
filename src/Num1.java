class Num1 {

    public static void main(String[] args) {
        System.out.print(estPremier(11));
        System.out.print(estPremier(12));
        System.out.print(estPremier(31));
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
}
