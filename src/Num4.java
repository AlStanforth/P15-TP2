
class Num4 {

	public static void main(String[] args) {
		System.out.println(exponentiationRapide(3, 11, 5));
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