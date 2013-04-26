class Num1 {

    public long p, q, n;

    public static void main(String[] args) {
        Num1 self = new Num1();
        System.out.format("p:%d * q:%d = n:%d%n", self.p, self.q, self.n);
    }
    
    public Num1() {
        p = prochainPremier(9234); // 9239
        q = prochainPremier(5678); // 5683
        n = p * q;                 // 52_505_237
    }

    public boolean estPremier(long num) {
        if (num <= 1) throw new IllegalArgumentException();
        
        double limite = Math.floor(Math.sqrt(num));
        for (int i = 2; i <= limite; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public long prochainPremier(long num) {
        if (num <= 1) throw new IllegalArgumentException();
        
        if (num != 2 && num % 2 == 0) {
            num++;
        }
        while (!estPremier(num)) {
            num += 2;
        }
        return num;
    }
}
