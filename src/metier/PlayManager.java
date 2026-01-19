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

    public int deplacement(){
        //retourne un int en fonction de la direction du déplacement
        KeyHandle keyHandle = new KeyHandle();
        if(KeyHandle.upPressed)return 0;
        if(KeyHandle.rightPressed)return 1;
        if(KeyHandle.downPressed)return 2;
        if(KeyHandle.leftPressed)return 3;
        
        return 9;
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
