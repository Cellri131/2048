package vue;

public class GamePanel {

    int [][] tabJeu;
    public GamePanel(int[][] tabJeu) {
        this.tabJeu = tabJeu;        
    }
    
    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder();
        
        // Ligne supérieure
        affichage.append("╔");
        for (int j = 0; j < tabJeu.length; j++) {
            affichage.append("══════");
            if (j < tabJeu.length - 1) affichage.append("╦");
        }
        affichage.append("╗\n");
        
        // Lignes du plateau
        for (int i = 0; i < tabJeu.length; i++) {
            affichage.append("║");
            for (int j = 0; j < tabJeu[i].length; j++) {
                if(tabJeu[i][j] == 0) {
                    affichage.append(String.format("%5s ", "."));
                } else {
                    affichage.append(String.format("%5d ", tabJeu[i][j]));
                }
                affichage.append("║");
            }
            affichage.append("\n");
            
            // Ligne de séparation (sauf après la dernière ligne)
            if (i < tabJeu.length - 1) {
                affichage.append("╠");
                for (int j = 0; j < tabJeu.length; j++) {
                    affichage.append("══════");
                    if (j < tabJeu.length - 1) affichage.append("╬");
                }
                affichage.append("╣\n");
            }
        }
        
        // Ligne inférieure
        affichage.append("╚");
        for (int j = 0; j < tabJeu.length; j++) {
            affichage.append("══════");
            if (j < tabJeu.length - 1) affichage.append("╩");
        }
        affichage.append("╝\n");
        
        System.out.print(affichage.toString());
        return "";
    }
}
