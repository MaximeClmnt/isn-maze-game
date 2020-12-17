import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Main {
	
		public static int labx;      // taille lab en X
		public static int laby;       // taille lab en Y
		public static int rapport;
		public static int nbCle;           // nombre de clé posséder par le joueur au cours de la partie 
		public static int nbCleG;          // nombre de clé a générer 
		

		public static int templabx = 10*2+1;      // taille lab en X
		public static int templaby = 7*2+1;       // taille lab en Y
		public static int temprapport =50;
		public static int tempnbCleG =3;
		
		public static int minlabx=3*2+1;
		public static int minlaby=2*2+1;
		public static int minrapport=5;
		public static int minnbCleG;
		
		public static int maxlabx=50*2+1;
		public static int maxlaby=40*2+1;
		public static int maxrapport=95;
		public static int maxnbCleG;
		
		public static double posx = 1;         // pos de perso en X
		public static double posy = 1;         // pos de perso en Y
		
		public static  double persx =0.5;      // taille boite de colision en X
		public static  double persy =0.5;      // taille boite de colision en Y
		
		
		public static Image img = null;        	// declaration de l'image des texture général 
		public static Image porte = null; 		// declaration de l'image des texture de porte et de clés 
		public static Image perso = null;      	// declaration de l'image des texture des personnages 
		public static Image fondchrono = null; 
		public static Image chiffre = null; 
		public static Image para = null; 
		public static Image fondmenu = null; 
		public static Image titremenu = null; 
		public static Image fleche = null; 
		public static Image info = null; 
		public static Image recommencer = null; 
		public static Image reprendre = null; 
		public static Image hauteur = null; 
		public static Image largeur = null; 
		public static Image textRapport = null; 
		public static Image nombrecles = null; 
		
		public static int[] etatPerso = new int[2];        // gestion annimation personnage (direction du perso) + avancement de l'animation 
		public static boolean marche = false;              // gestion annimation personnage ( marche/arret)
		
		public static int[][] lab = new int[1000][1000];     // initialisation tableau du labyrinthe 
		public static int[][] templab = new int[1000][1000];     // initialisation tableau du labyrinthe 
		public static int[][] textlab = new int[1000][1000]; //"filtre" pour determiné la texture à afficher
		
		public static Fenetre fen = new Fenetre("Ezzeria");  // création de la fenetre 
		
		public static boolean chronoActif = false;
		public static double chrono = 0;
		public static double[] valeurs = new double[10000];
		public static int tval = 0;
		public static double moy = 0;
		public static double min = 0;
		public static double max = 0;
		
		public static boolean fin = false;
		public static String phrases[] = {"Bravo!","Félicitation!","Quel talent!","Humm.. Un peu lent..","Plus vite!","Ma grand mère irait plus vite!",
				"Luc, je suis ton père!","Pardonnez? Je ne regardais pas.","Maxime CLEMENT est le plus fort","Lucas GUZMAN est le plus fort",
				"Phrase Aléatoire n°1664","J'ai toujours rêvé d'être un ornithorynque","Ma plus grande crainte est de perdre ma tête",
				"Ma mère m'a dit de rester ici pour me punir","Je suis sûr que ça fait 1h que j'y suis","Bertrand Renard est une rock star",
				"L'architecte de ces lieu à du utilisé un algorithme de premier choix","Ce donjon semble parfait","Ce labyrinthe était donc bien parfait",
				"Bernard Minet serait un robinet","L'hippopotomonstrosesquippedaliophobie est la peur des mots longs",
				"Il faut 1h pour terminer un labyrinthe 100x100","C'est véridique un homme trou peu vous aspiré","Vous ètes Thésée, fils d’Égée",
				"Je suis le minotaure mais je suis fatigué","Allez, du nerf!","Toujours plus haut toujours plus loin, tu parle on en sortira jamais ...",
				"Si seulement il y avait une pizzeria dans le coins","42!! Mais c'etait quoi la question déjà?","7,5 million d'année de calcul, ça fait beaucoup",
				"Erreur 404 : FILE NOT FOUND","1+1=10 Non, ce n'est pas faux","Si seulement j'avais un Fil d'Ariane","Le temps moyen est de 0.7*exp(sqrt(L*H)*log(1.42))",
				"Icare savait","Il est profond ce donjon!","kayak et été sont des palindromes","\"Ho oui oui oui\" Julien Lepres (2003)"};
				
		public static int randphrase;
		
		public static int menu = 0;
		/*
			0:Terrain de jeu.
			1:Menu
		 */

		
		public static int texturepers = 1;    // choix texture du personnage 
		public static int textureporte = 0;    // choix texture de la porte
		public static int textureterrain = 0;  // choix texture du terrain

		public static boolean MouseInPara;
	public static void main(String[] args) {
	// XXXX	chargement des images XXXX
		try {
				//img = ImageIO.read(new File("Texture/terrain.png"));
			    porte = ImageIO.read(new File("Texture/Terrain/porte.png"));
				img = ImageIO.read(new File("Texture/Terrain/terrain1.png"));
				perso = ImageIO.read(new File("Texture/perso/"+texturepers+".png"));
				fondchrono = ImageIO.read(new File("Texture/carte.png"));
				chiffre = ImageIO.read(new File("Texture/chiffre.png"));
				para = ImageIO.read(new File("Texture/para.png"));
				fondmenu = ImageIO.read(new File("Texture/fondmenu.png"));
				titremenu = ImageIO.read(new File("Texture/titremenu.png"));
				fleche = ImageIO.read(new File("Texture/fleche.png"));
				info = ImageIO.read(new File("Texture/info.png"));
				recommencer = ImageIO.read(new File("Texture/recommencer.png"));
				reprendre = ImageIO.read(new File("Texture/reprendre.png"));
				hauteur = ImageIO.read(new File("Texture/hauteur.png"));
				largeur = ImageIO.read(new File("Texture/largeur.png"));
				textRapport = ImageIO.read(new File("Texture/rapport.png"));
				nombrecles = ImageIO.read(new File("Texture/nombrecles.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		//XXXX Paramètres de la fenètre XXXX
		Paneau pan = new Paneau();
		fen.addKeyListener(pan);
		fen.addMouseListener(pan);
		fen.addMouseMotionListener(pan);
		fen.setContentPane(pan);
		//fen.setCursor(12);
		
		generer();
		genererPreview();
		int compAnim = 0;// on créer un compteur cyclique pour l'animation
		while(true){
			
			
			//XXXX Colision deplacement XXXX
			
			/*
			 * on teste si: 
			 * 		le joueur essaye d'aller a gauche
			 * 		le coins en haut a gauche peut se deplacer sans colision
			 * 		le coins en bas a gauche peut se deplacer sans colision
			 * 		
			*/
			if(Paneau.l&&lab[(int)(posx-0.1)][(int)posy]!=-1&&lab[(int)(posx-0.1)][(int)(posy+persy)]!=-1){
				posx-=0.05; 		// on déplace le personnage
				etatPerso[0]=3; 	// on change son orientation
				marche=true;		// on le met dans l'etat de marche (animation)
			}
			//même chose pour la droite
			if(Paneau.r&&lab[(int)(posx+persx+0.1)][(int)posy]!=-1&&lab[(int)(posx+persx+0.1)][(int)(posy+persy)]!=-1){
				posx+=0.05;         // on déplace le personnage
				etatPerso[0]=1;     // on change son orientation
				marche=true;        // on le met dans l'etat de marche (animation)
			}
			//même chose pour le haut
			if(Paneau.u&&lab[(int)posx][(int)(posy-0.1)]!=-1&&lab[(int)(posx+persx)][(int)(posy-0.1)]!=-1){
				posy-=0.05;         // on déplace le personnage
				etatPerso[0]=0;     // on change son orientation
				marche=true;        // on le met dans l'etat de marche (animation)
			}
			//même chose pour le bas
			if(Paneau.d&&lab[(int)posx][(int)(posy+persy+0.1)]!=-1&&lab[(int)(posx+persx)][(int)(posy+persy+0.1)]!=-1){
				posy+=0.05;         // on déplace le personnage
				etatPerso[0]=2;     // on change son orientation
				marche=true;        // on le met dans l'etat de marche (animation)
			}	
			
			if(!(Paneau.l||Paneau.u||Paneau.r||Paneau.d))
								//si le joueur n'appui sur aucune touche de deplacement 
				marche=false;	// on met le personnage dans l'etat de d'arret (animation)
			
			//XXX Animation de marche XXXX
			if(marche){//si le personnage est en etat de marche
				if(compAnim>=10){	//si le compteur est plus grand que 10
					etatPerso[1]++;	//on incrémente l'etat du perso
					compAnim=0;		//on réinitialise le compteur 
					if(etatPerso[1]>=3)	//si l'etat du perso est plus grand ou egal a 3
						etatPerso[1]=0;	//on réinitialise l'etat du perso
				}
				compAnim++; //on incrémente le compteur
			}
			else
				etatPerso[1]=1;//si le perso ne marche pas on le met en position d'arret
			
			
			
			//XXX Gestions clés XXXX
			if(lab[(int)posx][(int)posy]>=10){	//si la case aux coordonées du personage est un clé
				nbCle++;						//on incrémente le compteur de clé recupérées 
				lab[(int)posx][(int)posy]=0;	//on supprime la clé du labyrinthe
			}
			
			//XXX Gestion Fin XXXX
			if(nbCle==nbCleG && (int)posx==labx-2 && (int)posy==laby/4*2+1 && !fin){
					//si le personnage possède le nombre de clé requise et ce situe au coordonné de sortie (à coter de la porte)
				fin=true;
				//Calcul chrono
				chronoActif=false;
				valeurs[tval]=chrono/100;
				if(min==0||valeurs[tval]<min)
					min=valeurs[tval];
				if(min==0||valeurs[tval]>max)
					max=valeurs[tval];
				moy=0;
				for(int i = 0; i<=tval; i++)
					moy+=valeurs[i];
				moy/=(tval+1);
					
				tval++;
				System.out.println(tval+": "+valeurs[tval-1]+"\t min: "+min+"\t max: "+max+"\t moy: "+moy);  
				
				randphrase = (int)(Math.random()*phrases.length);

			}
			if(fin&&Paneau.space){
				generer();
			}
			
			if(chronoActif&&menu==0)
			chrono++;
			
			//on repeint l'ecran
			//on fait une pause de 10ms
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fen.repaint();
				fen.revalidate();
		}
	}
	
	public static void generer(){

		textureporte=(int)(Math.random()*3);   // choix aléatoire de la porte
		textureterrain=(int)(Math.random()*10);  // choix aléatoire du terrain
		
		
		
		labx=templabx;
		laby=templaby;
		rapport=temprapport;
		nbCleG=tempnbCleG;
		
		nbCleG = Math.min(nbCleG, (labx-1)/2*(laby-1)/2-1);
		nbCle = 0;
		chrono=0;
		chronoActif=false;
		boolean[][] incassable = new boolean[labx][laby]; //Creation de murs incassables 
		
		
		int comp=0; 	//Variable pour assignation d'un chiffre à chaques cases
		int murO=0; 	// Compteur de nombre de mur "ouvert"
		
		//Déclaration des variables de coordonées à génerer 
		int x;			
		int y;
		
		
		
		//XXX Initialisation XXXX
		
		//Creation de mur(-1) sur tout le tableau
		for(int j=0; j<laby ; j++){
			for(int i=0; i<labx ; i++){
					lab[i][j]=-1;
			}
		}
		
		//Creation de sol obligatoire 
		for(int j=1; j<laby ; j+=2){
			for(int i=1; i<labx ; i+=2){
				lab[i][j]=0;
			}
		}
		
		//XXX Initialisation personage XXXX
		texturepers=(int)(Math.random()*25+1);
		try {
			perso = ImageIO.read(new File("Texture/perso/"+texturepers+".png"));
			//on charge la texture correspondant à la nouvelle valeur
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		//On place le personnage au milieu à gauche
		posx=1;
		posy=laby/4*2+1;
		//on oriente le personage vers la droite
		etatPerso[0]=1;
		etatPerso[1]=1;
		
		
		//XXX Assignation d'un chiffre à chaques cases de sol XXXX
		for(int j=1; j<laby ; j+=2){
			for(int i=1; i<labx ; i+=2){
				lab[i][j]=comp;
				comp++;
			}
		}
		//XXX Creation de couloirs XXXX
		
		//XXX couloir d'entrée XXXX
		x=1;								//position en X du couloir
		y=laby/4*2+1;						//position en Y du couloir
		lab[x+1][y]=0;						//On "casse" le mur a droite
		incassable[x][y-1]=true;			//Mur en haut incassable
		incassable[x][y+1]=true;			//Mur en bas incassable
		remplacer(lab[x][y],lab[x+2][y]);	//remplacement du chiffre d'indentité des cases
		murO++;								//Incrementation du compteur de nombre de mur "ouvert"
		
		
		//XXX couloir de sortie XXXX
		x=labx-2;							//position en X du couloir
		y=laby/4*2+1;						//position en Y du couloir
		lab[x-1][y]=0;						//On "casse" le mur a droite
		incassable[x][y-1]=true;			//Mur en haut incassable
		incassable[x][y+1]=true;			//Mur en bas incassable
		remplacer(lab[x][y],lab[x-2][y]);	//remplacement du chiffre d'indentité des cases
		murO++;								//Incrementation du compteur de nombre de mur "ouvert"
		
		
		
		//XXX Génération du labyrinthe parfait XXXX
		
		while(murO<((labx-1)/2*(laby-1)/2)-1){
			/*
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fen.repaint();
			*/
			
	//tant que le nombre de mur ouvert est inferieur au nombre de mur qui doivent etre ouverts
			//XXX choix aléatoire de l'orientation du mur à "casser" XXXX
			//XXX mur verticaux XXXX
			if((int)(Math.random()*100+1)<=rapport){ 		
														//Generation aleatoire de coordonées
				x=(int) (Math.random()*((labx-1)/2-1))*2+2;	
				y=(int) (Math.random()*((laby-1)/2))*2+1;
				
				if(lab[x-1][y]!=lab[x+1][y]&&lab[x][y]!=0&&!incassable[x][y]){
						/*on test:	-si les cases autour du mur on une identité différente
						-si le mur n'est pas déja cassé
						-si le mur n'est pas incassable	*/
					lab[x][y]=0;						//on casse le mur	
					remplacer(lab[x-1][y],lab[x+1][y]); //on combine les identité des cases
					murO++;								//Incrementation du compteur de nombre de mur "ouvert"
				}
			}
			//XXX mur horizontaux XXXX
			else{										
														//Generation aleatoire de coordonées
				y=(int) (Math.random()*((laby-1)/2-1))*2+2;
				x=(int) (Math.random()*((labx-1)/2))*2+1;
				
				if(lab[x][y-1]!=lab[x][y+1]&&lab[x][y]!=0&&!incassable[x][y]){
					/*on test:	-si les cases autour du mur on une identité différente
								-si le mur n'est pas déja cassé
								-si le mur n'est pas incassable	*/
					lab[x][y]=0;						//on casse le mur
					remplacer(lab[x][y-1],lab[x][y+1]);	//on combine les identité des cases
					murO++;								//Incrementation du compteur de nombre de mur "ouvert"
				}
			}
		}
		//XXX Géneration des clés XXXX
			int k=0; 			//Déclaration et initialisation d'une variable (nombre de clé génerés) 
			while(k<nbCleG){ 	//tant que le nombre de clés génerés est inferieur au nombre de clés à generer:
				
				
				do{
					//On génere des nouvelles coordonées 
				x=(int)((Math.random()*labx)/2-1)*2+1;
				y=(int)((Math.random()*laby)/2-1)*2+1;
				}while(lab[x][y]!=0||(x==posx&&y==posy));
				//tant que la case aux coordonées génerées n'est pas egale a 0 (sol) 
				
				lab[x][y]=10+k;  	//on place la clé aux coordonées génerées
				k++;				//on augmente le compteur de clé generées
			}
		
			
			genererTextures();
			fin=false;
	}
	
	public static void genererPreview(){
		tempnbCleG = Math.min(tempnbCleG, (templabx-1)/2*(templaby-1)/2-1);
		boolean[][] incassable = new boolean[templabx][templaby]; //Creation de murs incassables 
		
		
		int comp=0; 	//Variable pour assignation d'un chiffre à chaques cases
		int murO=0; 	// Compteur de nombre de mur "ouvert"
		
		//Déclaration des variables de coordonées à génerer 
		int x;			
		int y;
		
		
		
		//XXX Initialisation XXXX
		
		//Creation de mur(-1) sur tout le tableau
		for(int j=0; j<templaby ; j++){
			for(int i=0; i<templabx ; i++){
				templab[i][j]=-1;
			}
		}
		
		//Creation de sol obligatoire 
		for(int j=1; j<templaby ; j+=2){
			for(int i=1; i<templabx ; i+=2){
				templab[i][j]=0;
			}
		}
		
		
		//XXX Assignation d'un chiffre à chaques cases de sol XXXX
		for(int j=1; j<templaby ; j+=2){
			for(int i=1; i<templabx ; i+=2){
				templab[i][j]=comp;
				comp++;
			}
		}
		//XXX Creation de couloirs XXXX
		
		//XXX couloir d'entrée XXXX
		x=1;								//position en X du couloir
		y=templaby/4*2+1;						//position en Y du couloir
		templab[x+1][y]=0;						//On "casse" le mur a droite
		incassable[x][y-1]=true;			//Mur en haut incassable
		incassable[x][y+1]=true;			//Mur en bas incassable
		tempremplacer(templab[x][y],templab[x+2][y]);	//remplacement du chiffre d'indentité des cases
		murO++;								//Incrementation du compteur de nombre de mur "ouvert"
		
		
		//XXX couloir de sortie XXXX
		x=templabx-2;							//position en X du couloir
		y=templaby/4*2+1;						//position en Y du couloir
		templab[x-1][y]=0;						//On "casse" le mur a droite
		incassable[x][y-1]=true;			//Mur en haut incassable
		incassable[x][y+1]=true;			//Mur en bas incassable
		tempremplacer(templab[x][y],templab[x-2][y]);	//remplacement du chiffre d'indentité des cases
		murO++;								//Incrementation du compteur de nombre de mur "ouvert"
		
		
		
		//XXX Génération du labyrinthe parfait XXXX
		
		while(murO<((templabx-1)/2*(templaby-1)/2)-1){
			
	//tant que le nombre de mur ouvert est inferieur au nombre de mur qui doivent etre ouverts
			//XXX choix aléatoire de l'orientation du mur à "casser" XXXX
			//XXX mur verticaux XXXX
			if((int)(Math.random()*100+1)<=temprapport){ 		
														//Generation aleatoire de coordonées
				x=(int) (Math.random()*((templabx-1)/2-1))*2+2;	
				y=(int) (Math.random()*((templaby-1)/2))*2+1;
				
				if(templab[x-1][y]!=templab[x+1][y]&&templab[x][y]!=0&&!incassable[x][y]){
						/*on test:	-si les cases autour du mur on une identité différente
						-si le mur n'est pas déja cassé
						-si le mur n'est pas incassable	*/
					templab[x][y]=0;						//on casse le mur	
					tempremplacer(templab[x-1][y],templab[x+1][y]); //on combine les identité des cases
					murO++;								//Incrementation du compteur de nombre de mur "ouvert"
				}
			}
			//XXX mur horizontaux XXXX
			else{										
														//Generation aleatoire de coordonées
				y=(int) (Math.random()*((templaby-1)/2-1))*2+2;
				x=(int) (Math.random()*((templabx-1)/2))*2+1;
				
				if(templab[x][y-1]!=templab[x][y+1]&&templab[x][y]!=0&&!incassable[x][y]){
					/*on test:	-si les cases autour du mur on une identité différente
								-si le mur n'est pas déja cassé
								-si le mur n'est pas incassable	*/
					templab[x][y]=0;						//on casse le mur
					tempremplacer(templab[x][y-1],templab[x][y+1]);	//on combine les identité des cases
					murO++;								//Incrementation du compteur de nombre de mur "ouvert"
				}
			}
		}
		//XXX Géneration des clés XXXX
			int k=0; 			//Déclaration et initialisation d'une variable (nombre de clé génerés) 
			while(k<tempnbCleG){ 	//tant que le nombre de clés génerés est inferieur au nombre de clés à generer:
				
				
				do{
					//On génere des nouvelles coordonées 
				x=(int)((Math.random()*templabx)/2-1)*2+1;
				y=(int)((Math.random()*templaby)/2-1)*2+1;
				}while(templab[x][y]!=0);
				//tant que la case aux coordonées génerées n'est pas egale a 0 (sol) 
				
				templab[x][y]=10+k;  	//on place la clé aux coordonées génerées
				k++;				//on augmente le compteur de clé generées
			}
		
	}
	
	
	public static void genererTextures(){
		boolean u,r,d,l; //initialisation de variable pour les textures connectées
		//on parcours tout le tableau
		for(int j=0; j<laby ; j++){
			for(int i=0; i<labx ; i++){
				
				if(lab[i][j]==-1){
					u=false;
					r=false;
					d=false;
					l=false;
					
					
					if(i!=0){
						if(lab[i-1][j]==-1)l=true;
					}
					if(j!=0){
						if(lab[i][j-1]==-1)u=true;
					}
					if(i<labx-1){
						if(lab[i+1][j]==-1)r=true;
					}
					if(j<laby-1){
						if(lab[i][j+1]==-1)d=true;
					}
					
					if(	!u	&&	r	&&	!d	&&	!l	)textlab[i][j]=0;
					if(	!u	&&	r	&&	!d	&&	l	)textlab[i][j]=1;
					if(	!u	&&	!r	&&	!d	&&	l	)textlab[i][j]=2;
					if(	!u	&&	!r	&&	d	&&	!l	)textlab[i][j]=3;
					if(	u	&&	!r	&&	d	&&	!l	)textlab[i][j]=4;
					if(	u	&&	!r	&&	!d	&&	!l	)textlab[i][j]=5;
					if(	!u	&&	r	&&	d	&&	!l	)textlab[i][j]=6;
					if(	!u	&&	!r	&&	d	&&	l	)textlab[i][j]=7;
					if(	u	&&	r	&&	!d	&&	!l	)textlab[i][j]=8;
					if(	u	&&	!r	&&	!d	&&	l	)textlab[i][j]=9;
					if(	u	&&	r	&&	d	&&	l	)textlab[i][j]=10;
					if(	!u	&&	r	&&	d	&&	l	)textlab[i][j]=11;
					if(	u	&&	!r	&&	d	&&	l	)textlab[i][j]=12;
					if(	u	&&	r	&&	d	&&	!l	)textlab[i][j]=13;
					if(	u	&&	r	&&	!d	&&	l	)textlab[i][j]=14;
				}
			}
			if(laby>3)
				textlab[labx-1][laby/4*2]=9;  //texture prédefini autoure de la porte
			else
				textlab[labx-1][laby/4*2]=2;
				
			if(laby>5)
				textlab[labx-1][laby/4*2+2]=7;
			else
				textlab[labx-1][laby/4*2+2]=2;
		}
	}
	
	private static void remplacer(int a, int b){
		int min= Math.min(a, b); 	//min prend la valeur minimum entre a et b
		
		//on parcours tout le tableau
			for(int j=0; j<laby ; j++){
				for(int i=0; i<labx ; i++){
					if(lab[i][j]==a||lab[i][j]==b)	//si la case est a ou b
					lab[i][j]=min;					//on remplace sa valeur pas le minimum
				}
			}
	}
	private static void tempremplacer(int a, int b){
		int min= Math.min(a, b); 	//min prend la valeur minimum entre a et b
		
		//on parcours tout le tableau
			for(int j=0; j<templaby ; j++){
				for(int i=0; i<templabx ; i++){
					if(templab[i][j]==a||templab[i][j]==b)	//si la case est a ou b
						templab[i][j]=min;					//on remplace sa valeur pas le minimum
				}
			}
	}
}
