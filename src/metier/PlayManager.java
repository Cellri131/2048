package metier;

import java.util.Scanner;
import vue.GamePanel;

public class PlayManager {

    int[][] tabJeu;
    boolean gameOver = false;
    static int score = 0;
    Scanner scanner;

    public PlayManager(int taille) {
        tabJeu = new int[taille][taille];
        // Initialisation avec quelques valeurs pour tester
        tabJeu[0][0] = 4;
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
            
            // Vérifie si l'utilisateur a saisi au moins un caractère
            if(input.length() > 0) {
                char touche = input.charAt(0);
                int direction = deplacement(touche);
                
                // Si une touche valide a été pressée
                if(direction != 9) {
                    System.out.println("\n=== Déplacement: " + getDirectionName(direction) + " ===");
                    // Réaffichage du plateau après le déplacement
                    System.out.println(gamePanel.toString());
                    System.out.print(this.score);
                }
            }
            newVal();
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

    private void newVal(){}

    public int deplacement(char touche){
        //retourne un int en fonction de la direction du déplacement
        if(touche == 'Z') // HAUT
        {
            for(int col = 0; col < tabJeu.length; col++) 
            {
                // Étape 1 : Déplacer toutes les tuiles vers le haut
                for(int row = 1; row < tabJeu.length; row++) 
                {
                    if(tabJeu[row][col] != 0) 
                    {
                        int currentRow = row;
                        while(currentRow > 0 && tabJeu[currentRow - 1][col] == 0) 
                        {
                            tabJeu[currentRow - 1][col] = tabJeu[currentRow][col];
                            tabJeu[currentRow][col] = 0;
                            currentRow--;
                        }
                    }
                }
                
                // Étape 2 : Fusionner les tuiles identiques adjacentes
                for(int row = 0; row < tabJeu.length - 1; row++) 
                {
                    if(tabJeu[row][col] != 0 && tabJeu[row][col] == tabJeu[row + 1][col]) 
                    {
                        tabJeu[row][col] = tabJeu[row][col] * 2;
                        tabJeu[row + 1][col] = 0;
                        
                        // Redéplacer les tuiles après fusion
                        for(int r = row + 1; r < tabJeu.length - 1; r++) 
                        {
                            tabJeu[r][col] = tabJeu[r + 1][col];
                            tabJeu[r + 1][col] = 0;
                        }
                    }
                }
            }
            return 0;
        }
        
        if(touche == 'D') // DROITE
        {
            for(int row = 0; row < tabJeu.length; row++) 
            {
                // Étape 1 : Déplacer toutes les tuiles vers la droite
                for(int col = tabJeu.length - 2; col >= 0; col--) 
                {
                    if(tabJeu[row][col] != 0) 
                    {
                        int currentCol = col;
                        while(currentCol < tabJeu.length - 1 && tabJeu[row][currentCol + 1] == 0) 
                        {
                            tabJeu[row][currentCol + 1] = tabJeu[row][currentCol];
                            tabJeu[row][currentCol] = 0;
                            currentCol++;
                        }
                    }
                }
                
                // Étape 2 : Fusionner les tuiles identiques adjacentes
                for(int col = tabJeu.length - 1; col > 0; col--) 
                {
                    if(tabJeu[row][col] != 0 && tabJeu[row][col] == tabJeu[row][col - 1]) 
                    {
                        tabJeu[row][col] = tabJeu[row][col] * 2;
                        tabJeu[row][col - 1] = 0;
                        
                        // Redéplacer les tuiles après fusion
                        for(int c = col - 1; c > 0; c--) 
                        {
                            tabJeu[row][c] = tabJeu[row][c - 1];
                            tabJeu[row][c - 1] = 0;
                        }
                    }
                }
            }
            return 1;
        }
        
        if(touche == 'S') // BAS
        {
            for(int col = 0; col < tabJeu.length; col++) 
            {
                // Étape 1 : Déplacer toutes les tuiles vers le bas
                for(int row = tabJeu.length - 2; row >= 0; row--) 
                {
                    if(tabJeu[row][col] != 0) 
                    {
                        int currentRow = row;
                        while(currentRow < tabJeu.length - 1 && tabJeu[currentRow + 1][col] == 0) 
                        {
                            tabJeu[currentRow + 1][col] = tabJeu[currentRow][col];
                            tabJeu[currentRow][col] = 0;
                            currentRow++;
                        }
                    }
                }
                
                // Étape 2 : Fusionner les tuiles identiques adjacentes
                for(int row = tabJeu.length - 1; row > 0; row--) 
                {
                    if(tabJeu[row][col] != 0 && tabJeu[row][col] == tabJeu[row - 1][col]) 
                    {
                        tabJeu[row][col] = tabJeu[row][col] * 2;
                        tabJeu[row - 1][col] = 0;
                        
                        // Redéplacer les tuiles après fusion
                        for(int r = row - 1; r > 0; r--) 
                        {
                            tabJeu[r][col] = tabJeu[r - 1][col];
                            tabJeu[r - 1][col] = 0;
                        }
                    }
                }
            }
            return 2;
        }
        
        if(touche == 'Q') // GAUCHE
        {
            for(int row = 0; row < tabJeu.length; row++) 
            {
                // Étape 1 : Déplacer toutes les tuiles vers la gauche
                for(int col = 1; col < tabJeu.length; col++) 
                {
                    if(tabJeu[row][col] != 0) 
                    {
                        int currentCol = col;
                        while(currentCol > 0 && tabJeu[row][currentCol - 1] == 0) 
                        {
                            tabJeu[row][currentCol - 1] = tabJeu[row][currentCol];
                            tabJeu[row][currentCol] = 0;
                            currentCol--;
                        }
                    }
                }
                
                // Étape 2 : Fusionner les tuiles identiques adjacentes
                for(int col = 0; col < tabJeu.length - 1; col++) 
                {
                    if(tabJeu[row][col] != 0 && tabJeu[row][col] == tabJeu[row][col + 1]) 
                    {
                        tabJeu[row][col] = tabJeu[row][col] * 2;
                        tabJeu[row][col + 1] = 0;
                        
                        // Redéplacer les tuiles après fusion
                        for(int c = col + 1; c < tabJeu.length - 1; c++) 
                        {
                            tabJeu[row][c] = tabJeu[row][c + 1];
                            tabJeu[row][c + 1] = 0;
                        }
                    }
                }
            }
            return 3;
        }
        
        return 9;
    }
    
    public int suite(int val1, int val2){
        int res = val1 + val2;
        score(res, val1);
        return res;
    }

    public void score(int val, int mult){
        this.score += val * (mult/2);
    }
}
