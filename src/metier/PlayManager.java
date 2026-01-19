package metier;

import java.util.Scanner;
import vue.GamePanel;

public class PlayManager {

    int[][] tabJeu;
    boolean gameOver = false;
    Scanner scanner;

    public PlayManager(int taille) {
        tabJeu = new int[taille][taille];
        // Initialisation avec quelques valeurs pour tester
        tabJeu[0][0] = 2;
        tabJeu[1][1] = 4;
        
        scanner = new Scanner(System.in);
        
        // Appel du toString de GamePanel dans le package vue
        GamePanel gamePanel = new GamePanel(tabJeu);
        
        // Affichage initial du plateau
        System.out.println("=== Jeu 2048 - Utilisez ZQSD pour jouer ===");
        System.out.println(gamePanel.toString());
        
        // Boucle principale du jeu
        while(!gameOver) {
            System.out.print("\nEntrez une direction (Z=haut, Q=gauche, S=bas, D=droite) : ");
            String input = scanner.nextLine().toUpperCase();
            
            if(input.length() > 0) {
                char touche = input.charAt(0);
                int direction = deplacement(touche);
                
                // Si une touche valide a été pressée
                if(direction != 9) {
                    System.out.println("\n=== Déplacement: " + getDirectionName(direction) + " ===");
                    // Réaffichage du plateau après le déplacement
                    System.out.println(gamePanel.toString());
                }
            }
        }
        scanner.close();
    }
    
    private String getDirectionName(int direction) {
        switch(direction) {
            case 0: return "HAUT (Z)";
            case 1: return "DROITE (D)";
            case 2: return "BAS (S)";
            case 3: return "GAUCHE (Q)";
            default: return "INCONNUE";
        }
    }

    public int deplacement(char touche){
        //retourne un int en fonction de la direction du déplacement
        if(touche == 'Z')
        {
            for(int col = 0; col < tabJeu.length; col++) 
            {
                for(int row = 1; row < tabJeu.length; row++) 
                {
                    if(tabJeu[row][col] != 0) 
                    {
                        int currentRow = row;
                        while(currentRow > 0 && tabJeu[currentRow - 1][col] == 0) 
                        {
                            // Vérifier si la case suivante contient la même valeur
                            if(currentRow > 0 && tabJeu[currentRow - 1][col] == tabJeu[currentRow][col]) {
                                tabJeu[currentRow - 1][col] = suite(tabJeu[currentRow - 1][col], tabJeu[currentRow][col]);
                                tabJeu[currentRow][col] = 0;
                                break;
                            }
                            tabJeu[currentRow - 1][col] = tabJeu[currentRow][col];
                            tabJeu[currentRow][col] = 0;
                            currentRow--;
                        }
                    }
                }
            }
            return 0;
        }
        if(touche == 'D')
        { 
            return 1;
        }
        if(touche == 'S') return 2;
        if(touche == 'Q') return 3;
        
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
