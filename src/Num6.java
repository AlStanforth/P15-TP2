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