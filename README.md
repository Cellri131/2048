# 2048 - Java MVC

## Description

2048 est un jeu de puzzle solo où le joueur doit faire glisser des tuiles numérotées sur une grille 4x4 pour les combiner et atteindre la tuile 2048.

Ce projet est une implémentation du jeu en **Java** suivant le pattern **MVC (Modèle-Vue-Contrôleur)**.

## Architecture MVC

```
src/
├── model/
│   ├── Game.java          # Logique du jeu, grille, score
│   └── Tile.java          # Représentation d'une tuile
├── view/
│   ├── GameView.java      # Interface graphique principale
│   └── TileView.java      # Affichage des tuiles
├── controller/
│   └── GameController.java # Gestion des entrées utilisateur
└── Main.java              # Point d'entrée
```

## Règles du jeu

- Utilisez les **flèches directionnelles** pour déplacer les tuiles
- Deux tuiles de même valeur fusionnent en une seule
- Une nouvelle tuile (2 ou 4) apparaît après chaque mouvement
- **Objectif** : Créer une tuile 2048
- **Game Over** : Aucun mouvement possible

## Prérequis

- Java JDK 11 ou supérieur

## Installation

```bash
git clone https://github.com/votre-repo/2048-java.git
cd 2048-java
javac -d bin src/**/*.java
java -cp bin Main
```

## Contrôles

| Touche | Action |
|--------|--------|
| ↑ | Déplacer vers le haut |
| ↓ | Déplacer vers le bas |
| ← | Déplacer vers la gauche |
| → | Déplacer vers la droite |
| R | Recommencer |

## Auteur

R_BAR

## Licence

MIT License
