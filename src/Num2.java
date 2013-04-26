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
        return new long[] {x, x0, y0};
    }
}