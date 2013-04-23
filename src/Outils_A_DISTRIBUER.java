/**
 * @author Eric Gaul, 31 mars 2011
 */
public class Outils_A_DISTRIBUER {

    public static final int LONGUEUR_MORCEAU = 1;
    public static final int LONGUEUR_CODE_1_CAR = 2;

    /**
     * Fonction pour tester les fonctions proposées
     * @param args
     */
    public static void main(String[] args) {
        String texteClair = "PORTEZ CE VIEUX WHISKY AU JUGE BLOND QUI FUME";
        System.out.println(texteClair);
        long[] code = encoder(texteClair);
        System.out.println(toString(code));
        System.out.println(decoder(code));
    }

    /**
     * @param message Suite de blocs de nombres
     * @return Chaîne de caracteres pour affichage
     */
    public static String toString(long[] message) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < message.length; i++) {
            out.append(message[i]);
            out.append(' ');
        }
        return out.toString();
    }

    /**
     * @param message Le texte à encoder, qui contient seulement des
     *                lettres non accentuées et des espaces blancs
     * @return un tableau de nombres où chaque groupe de LONGUEUR_MORCEAU
     *                lettres de départ est devenu un nombre
     */
    public static long[] encoder(String message) {
        message = message.toUpperCase(); // Tout en majuscules
        // On comble le texte à la fin pour arriver à une longueur
        // multiple de LONGUEUR_MORCEAU
        while (message.length() % LONGUEUR_MORCEAU != 0) {
            message += ' ';
        }
        // On alloue l'esapce pour recueillir l'équivalent numérique
        // de chacun des morceaux de texte de LONGUEUR_MORCEAU caractères
        long[] morceaux = new long[message.length() / LONGUEUR_MORCEAU];
        int k = 0;
        for (int i = 0; i < message.length(); i += LONGUEUR_MORCEAU) {
            String morceau = message.substring(i, i + LONGUEUR_MORCEAU);
            String strEnc = "";
            // On encode chaque caractère du morceau
            for (int j = 0; j < LONGUEUR_MORCEAU; j++) {
                char c = morceau.charAt(j);
                int valeur = 1 + c - 'A';
                if (c == ' ') {
                    valeur = 27;
                }
                // On vérifie que chaque caractère pouvait être encodé
                if (valeur < 1 || 27 < valeur) {
                    throw new IllegalArgumentException(
                            "Le texte contient des caractères non gérés "
                            + "par le système d'encodage");
                }
                String carEnc = "" + valeur;
                // On s'assure que chaque nombre a deux chiffres
                if (carEnc.length() < LONGUEUR_CODE_1_CAR) {
                    carEnc = '0' + carEnc;
                }
                strEnc += carEnc;
            }
            morceaux[k] = Long.parseLong(strEnc);
            k++;
        }
        return morceaux;
    }

    /**
     *
     * @param message Un tableau de nombre où chaque nombre représente
     *               LONGUEUR_MORCEAU lettres d'un texte
     * @return Le texte encodé
     */
    public static String decoder(long[] message) {
        String texte = "";
        // On traite chaque bloc de message
        for (int k = 0; k < message.length; k++) {
            String strEnc = "" + message[k];
            // On ajuste la longueur en ajoutant des espaces à gauche
            while (strEnc.length() < LONGUEUR_CODE_1_CAR * LONGUEUR_MORCEAU) {
                strEnc = "0" + strEnc;
            }
            // On étudie chaque groupe de deux chiffres
            for (int j = 0; j < strEnc.length(); j += LONGUEUR_CODE_1_CAR) {
                String carEnc = strEnc.substring(j, j + LONGUEUR_CODE_1_CAR);
                int valeur = Integer.parseInt(carEnc);
                // On vérifie que chaque caractère pouvait être encodé
                if (valeur < 1 || 27 < valeur) {
                    throw new IllegalArgumentException(
                            "Le code contient des valeurs non gérées "
                            + "par le système de décodage");
                }
                char c = (char) (valeur - 1 + 'A');
                if (valeur == 27) {
                    c = ' ';
                }
                texte += c;
            }
        }
        return texte;
    }
}
