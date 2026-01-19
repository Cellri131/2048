package metier;

import vue.GamePanel;

public class PlayManager {

    int[][] tabJeu;
    boolean gameOver = false;

    public PlayManager(int taille) {
        tabJeu = new int[taille][taille];
        // Appel du toString de GamePanel dans le package vue
        GamePanel gamePanel = new GamePanel(tabJeu);
        System.out.println(gamePanel.toString());
        /*while(!gameOver){
            // logique du jeu ici

            
        }*/
    }


    
    public int suite(int val1, int val2){
        int res = val1 + val2;
        return res;
    }

    public int score(int val1, int val2){
        int res = val1 + val2;
        return res;
    }
}
