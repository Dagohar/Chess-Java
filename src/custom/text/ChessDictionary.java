package custom.text;

// Klasa odpowiada za tłumaczenie poszczególnych zagadnień
public class ChessDictionary {
    // Metoda zamienia znak na pełną nazwę
    public static String TranslateSymbolToName(char pieceSymbol, PolishCases polishCase) {
        if(polishCase == PolishCases.mianownik) {
            switch (pieceSymbol) {
                case 'P': return "pionek";
                case 'W': return "wieża";
                case 'S': return "skoczek";
                case 'G': return "goniec";
                case 'H': return "hetman";
                case 'K': return "król";
            }
        }
        else if(polishCase == PolishCases.biernik) {
            switch (pieceSymbol) {
                case 'P': return "pionka";
                case 'W': return "wieżę";
                case 'S': return "skoczka";
                case 'G': return "gońca";
                case 'H': return "hetmana";
                case 'K': return "króla";
            }
        }

        return "NULL";
    }
}
