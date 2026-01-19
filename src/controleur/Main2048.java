package controleur;

import metier.*;

public class Main2048 {
    
    int[][] tabJeu;

    public Main2048(int taille) {

        this.tabJeu = new int[taille][taille];
        PlayManager playManager = new PlayManager();
    }
}
