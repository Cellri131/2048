package vue;

public class GamePanel {

    int [][] tabJeu;
    public GamePanel(int[][] tabJeu) {
        this.tabJeu = tabJeu;        
    }
    
    @Override
    public String toString() {
        for (int i = 0; i < tabJeu.length; i++) 
        {
            for (int j = 0; j < tabJeu[i].length; j++) 
            {
                if(tabJeu[i][j] == 0)
                    System.out.print(". "); 
                else
                    System.out.print(tabJeu[i][j] + " ");
            }
            System.out.println();
        }

        return "";
    }
}
