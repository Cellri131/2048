package metier;

import java.util.Scanner;
import vue.GamePanel;

public class PlayManager {

    int[][] tabJeu;
    int maxNbReach;
    boolean gameOver = false;
    static int score = 0;
    Scanner scanner;

    public PlayManager(int taille) {
        tabJeu = new int[taille][taille];
        // Initialisation avec quelques valeurs pour tester
        tabJeu[0][0] = 1;
        tabJeu[1][1] = 1;
        
        scanner = new Scanner(System.in);
        
        // Appel du toString de GamePanel dans le package vue
        GamePanel gamePanel = new GamePanel(tabJeu);
        
        // Affichage initial du plateau
        clearScreen();
        System.out.println("=== Jeu 2048 - Utilisez ZQSD pour jouer ===");
        System.out.println(gamePanel.toString());
        System.out.println("Score: " + this.score);

        maxNbReach = 0;
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
                    clearScreen();
                    System.out.println("=== Jeu 2048 - Utilisez ZQSD pour jouer ===");
                    System.out.println("Déplacement: " + getDirectionName(direction));
                    System.out.println(gamePanel.toString());
                    System.out.println("Score: " + this.score + " | Plus grand nombre: " + maxNbReach);
                }
            }
            
            if(!gameOver) {
                newVal();
                end();
            }
        }
        scanner.close();
    }
    
    private void clearScreen() {
        // Utilise les codes ANSI pour effacer l'écran (fonctionne sur Windows 10+, Linux, Mac)
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

    private void end(){
        // Vérifie si le tableau est plein
        boolean plateauPlein = true;
        for(int i = 0; i < tabJeu.length && plateauPlein; i++) {
            for(int j = 0; j < tabJeu.length && plateauPlein; j++) {
                if(tabJeu[i][j] == 0) {
                    plateauPlein = false;
                }
            }
        }

        // Si le plateau est plein, vérifie s'il reste des mouvements possibles
        if(plateauPlein) {
            boolean mouvementPossible = false;
            
            // Vérifie les fusions possibles horizontalement et verticalement
            for(int i = 0; i < tabJeu.length && !mouvementPossible; i++) {
                for(int j = 0; j < tabJeu.length && !mouvementPossible; j++) {
                    // Vérifie fusion possible à droite
                    if(j < tabJeu.length - 1 && tabJeu[i][j] == tabJeu[i][j + 1]) {
                        mouvementPossible = true;
                    }
                    // Vérifie fusion possible en bas
                    if(i < tabJeu.length - 1 && tabJeu[i][j] == tabJeu[i + 1][j]) {
                        mouvementPossible = true;
                    }
                }
            }
            
            // Si aucun mouvement n'est possible, game over
            if(!mouvementPossible) {
                gameOver = true;
                System.out.println("\n=== GAME OVER ===");
                System.out.println("Score final : " + score);
                System.out.println("Plus grand nombre atteint : " + maxNbReach);
            }
        }
    }

    private void newVal(){//nom de merde à changer
        int nbRandomMax; //nom de merde à changer
        int nbMaxLuck;//nom de merde à changer

        // Détermine la valeur maximale aléatoire à générer selon le plus grand nombre atteint
        if (maxNbReach < 8) {
            nbRandomMax = 4;
        } else {
            nbRandomMax = maxNbReach / 4;
        }
        // System.out.println("M :" + nbRandomMax);

        // System.out.println("p"+maxNbReach);
        // Génère soit la valeur maxNbReach avec une probabilité de 1/maxNbReach, soit une valeur aléatoire entre 1 et nbRandomMax
        nbMaxLuck = (Math.random() < 1.0 / maxNbReach) ? maxNbReach : (int)(Math.random() * nbRandomMax) + 1;

        // Si ce n'est pas la valeur maxNbReach, on a une chance sur deux d'avoir un 2 ou un 4
        if (nbMaxLuck != maxNbReach) {
            nbMaxLuck = (Math.random() < 0.5) ? 2 : 4;
        }
        
        // System.out.println("m"+nbMaxLuck);

        //placement random des deux valeurs
        // Trouver toutes les positions libres
        
        // Création d'un tableau 2D pour stocker toutes les positions vides possibles
        // - Première dimension : tabJeu.length * tabJeu.length = nombre maximum de cases (ex: 4*4 = 16 cases maximum)
        // - Deuxième dimension : [2] = chaque position contient 2 informations [ligne, colonne]
        // Exemple : positionsLibres[0] = [1, 3] signifie que la case en ligne 1, colonne 3 est vide
        int[][] positionsLibres = new int[tabJeu.length * tabJeu.length][2];
        int nbPositionsLibres = 0;

        for(int i = 0; i < tabJeu.length; i++) {
            for(int j = 0; j < tabJeu.length; j++) {
                if(tabJeu[i][j] == 0) {
                    positionsLibres[nbPositionsLibres][0] = i;
                    positionsLibres[nbPositionsLibres][1] = j;
                    nbPositionsLibres++;
                }
            }
        }

        // Placer nbMaxLuck si des positions libres existent
        if(nbPositionsLibres > 0) {
            int indexAleatoire = (int)(Math.random() * nbPositionsLibres);
            int row = positionsLibres[indexAleatoire][0];
            int col = positionsLibres[indexAleatoire][1];
            tabJeu[row][col] = nbMaxLuck;
        }

        
    }

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
                        tabJeu[row][col] = suite(tabJeu[row][col], tabJeu[row + 1][col]);
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
                        tabJeu[row][col] = suite(tabJeu[row][col], tabJeu[row][col - 1]);
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
                        tabJeu[row][col] = suite(tabJeu[row][col], tabJeu[row - 1][col]);
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
                        tabJeu[row][col] = suite(tabJeu[row][col], tabJeu[row][col + 1]);
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

        if (res > maxNbReach) {
            maxNbReach = res;
            System.out.println("Nouveau nombre atteint : " + maxNbReach);
        }
        
        return res;
    }

    public void score(int val, int mult){
        this.score += val * (mult/2);
    }
}
