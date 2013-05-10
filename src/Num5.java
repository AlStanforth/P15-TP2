class Num5 {

    public static long[] signatureProf = {
        76310066, 2596885, 1136574, 15355199, 78110831,
        63789364, 24210018, 33518696, 41054765, 21115496
    };

    public static long
        nProf = 81072007,
        eProf = 9001;

    public static String signatureDecoded;


    static {
        long[] signatureDecrypt = new long[Num5.signatureProf.length];
        for (int i = 0; i < Num5.signatureProf.length; i++) {
            signatureDecrypt[i] = Num4.exponentiationRapide(Num5.signatureProf[i], eProf, nProf);
        }

        signatureDecoded = Outils_A_DISTRIBUER.decoder(signatureDecrypt);
    }

    public static void main(String[] args) {
        System.out.println(signatureDecoded);
    }

}
