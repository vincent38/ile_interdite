package util;

import java.awt.Color;

/**
 *
 * @author IUT2-Dept Info
 */
public class Parameters {
    
    // ====================================================================================
    // Paramètres NF
    public static final Boolean LOGS = false ; // Afficher des traces par System.out.println()
    public static final Boolean ALEAS = true ; // Attribuer les aventuriers aléatoirement ou non, mélanger les défausses et les pioches
    public static final Boolean SIMPLIFIED = true ; // Processus simplifié (pas d'interruption pendant les tours)
    
    // ====================================================================================
    // Paramètres IHM
    public static final Boolean UNDECORATED = true ; // Indique si la barre de fenêtre doit être affichée
    public static final Integer DECORATION_HEIGHT = 25 ; // Taille de la barre de fenêtre
    public static final Boolean RESIZABLE = false ; // Indique si les fenêtres peuvent être redimensionnées
    public static final Integer SWING_BORDERS_HEIGHT = 4 ; // Taille des bordures de côté d'une fenêtre
    public static final Integer TOP_VUE_AVENTURIER = UNDECORATED ? 30 : 0 ; // Position des fenêtres aventuriers
    public static final Integer TOP_AUTRES_VUES = 265 - (UNDECORATED ? 0 : DECORATION_HEIGHT) ; // Position des fenêtres Niveau, Plateau et Message en NoPic
    public static final Integer HAUTEUR_VUE_AVENTURIER = 200 ; // Hauteur des fenêtres Aventurier
    public static final Integer HAUTEUR_AUTRES_VUES = 491 + (UNDECORATED ? 0 : DECORATION_HEIGHT) ; // Hauteur des fenêtres Niveau, Plateau et Message

    // ====================================================================================
    // Jeu
    public static final Integer NB_INONDATIONS_INITIALES = (SIMPLIFIED ? 6 : 6) ; // Nombre de tuiles inondées au démarrage du jeu
    public static final Integer NB_MONTEES_DES_EAUX = (SIMPLIFIED ? 2 : 3) ; // Nombre de cartes Montées des Eaux en jeu simplifié ou non
    public static final Integer NB_HELICOPTERES = 3 ; // Nombre de cartes Hélicoptère
    public static final Integer NB_SACS_DE_SABLE = 2 ; // Nombre de cartes Sacs de Sable

    // ====================================================================================
    // COULEURS
    public static Color PLATEAU_BG = new Color(67, 119, 204); // Couleur de fond du plateau 
    public static Color TUILE_ASSECHEE_BG = Color.WHITE; // Couleur de fond d'une tuile asséchée
    public static Color TUILE_INONDEE_BG = new Color(138, 169, 204); // Couleur de fond d'une tuile inondée
    public static Color COULEUR_TUILE_ASSECHEE_GRISEE = new Color(192, 192, 192); // Couleur de fond d'une tuile asséchée non cliquable
    public static Color COULEUR_TUILE_INONDEE_GRISEE = new Color(175, 192, 192); // Couleur de fond d'une tuile asséchée non cliquable
    public static Color COULEUR_TUILE_PAIRE = new Color(230, 180, 181) ; // Couleur de fond du titre d'une tuile (1/2)
    public static Color COULEUR_TUILE_IMPAIRE = new Color(184, 208, 138); // Couleur de fond du titre d'une tuile (2/2)

    // ====================================================================================
    // Chemins vers les images
    public static final String ROOT = System.getProperty("user.dir") ;
    public static final String IMAGES = ROOT + "/images/" ;
    public static final String PIONS = ROOT + "/images/pions/" ;
    public static final String TRESORS = ROOT + "/images/tresors/" ;
    public static final String TUILES = ROOT + "/images/tuiles/" ;
    public static final String CARTES = ROOT + "/images/cartes/" ;
    public static final String ICONS = ROOT + "/images/icones/" ;

    // ====================================================================================
    // Icones pour les boutons en mode images
    public static final String ICON_MOVE = ICONS + "iconMove.png" ;
    public static final String ICON_MOVE_DISABLED = ICONS + "iconMove_disabled.png" ;
    public static final Integer ICON_MOVE_WIDTH = 24 ;

    public static final String ICON_DRY = ICONS + "iconDry.png" ;
    public static final String ICON_DRY_DISABLED = ICONS + "iconDry_disabled.png" ;
    public static final Integer ICON_DRY_WIDTH = 24 ;

    public static final String ICON_GIVE = ICONS + "iconGive.png" ;
    public static final String ICON_GIVE_DISABLED = ICONS + "iconGive_disabled.png" ;
    public static final Integer ICON_GIVE_WIDTH = 24 ;

    public static final String ICON_GET = ICONS + "iconGet.png" ;
    public static final String ICON_GET_DISABLED = ICONS + "iconGet_disabled.png" ;
    public static final Integer ICON_GET_WIDTH = 24 ;

    public static final String ICON_RECEIVE = ICONS + "iconTarget.png" ;
    public static final String ICON_RECEIVE_DISABLED = ICONS + "iconTarget_disabled.png" ;
    public static final Integer ICON_RECEIVE_WIDTH = 17 ;

    public static final String ICON_DONE = ICONS + "iconDone.png" ;
    public static final String ICON_DONE_DISABLED = ICONS + "iconDone_disabled.png" ;
    public static final Integer ICON_DONE_WIDTH = 26 ;

    public static final String ICON_SHIFT = ICONS + "iconShift.png" ;
    public static final String ICON_SHIFT_DISABLED = ICONS + "iconShift_disabled.png" ;
    public static final Integer ICON_SHIFT_WIDTH = 24 ;
    
}
