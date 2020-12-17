import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Paneau extends JPanel implements KeyListener, MouseListener, MouseMotionListener{
	//on declare les valeurs booléennes associées au touche de déplacement
	public static boolean l;
	public static boolean r;
	public static boolean u;
	public static boolean d;
	public static boolean space;
	public static boolean escape;
	
	public static boolean click;
	
	//on initialise la taille en pixel d'une case
	private double taille = 30;
	private int taillecadre = 10; //taille du cadre autour du labyrinthe
	
	public int cursor = 0;
	
	public int x =0;	//position en x de la souris
	public int y =0;	//position en y de la souris
	
	public static int xb =0;	//position temporaire en x d'un bouton;
	public static int yb =0;	//position temporaire en y d'un bouton;
	public static int txb =0;	//taille temporaire en x d'un bouton;
	public static int tyb =0;	//taille temporaire en y d'un bouton;
	
	public void paintComponent(Graphics g){
		if(Main.menu==1){
			
			g.drawImage(Main.fondmenu,0,0,this.getWidth()	, this.getHeight()	, null);

			taille=Math.min(this.getWidth()*45/100/Main.templabx,this.getHeight()*75/100/Main.templaby);
			
			//On parcours tout le labyrinthe
			for(int j=0; j<Main.templaby ; j++){
				for(int i=0; i<Main.templabx ; i++){
					if(Main.templab[i][j]==-1){	//si la case est un mur
						g.setColor(new Color(48,28,7,100));
						g.fillRect((int)(i*taille+this.getWidth()/2-taille*Main.templabx/2),
								(int)(j*taille+this.getHeight()*60/100-taille*Main.templaby/2),
								(int)(taille), (int)(taille));
					}
					else{
						g.setColor(new Color(250,250,250,100));

						g.fillRect((int)(i*taille+this.getWidth()/2-taille*Main.templabx/2),
								(int)(j*taille+this.getHeight()*60/100-taille*Main.templaby/2),
								(int)(taille), (int)(taille));
					}
					if(Main.templab[i][j]>=10){	//si la case est un mur
						g.setColor(new Color(200,50,50,150));
						g.fillOval((int)(i*taille+this.getWidth()/2-taille*Main.templabx/2),
								(int)(j*taille+this.getHeight()*60/100-taille*Main.templaby/2),
								(int)(taille), (int)(taille));
					}
				}
			}
			
			
			//menu
			txb=Math.min(this.getWidth()/3,500);
			tyb=txb*Main.titremenu.getHeight(null)/Main.titremenu.getWidth(null);
			xb=this.getWidth()/2-txb/2;
			yb=this.getHeight()/100;
			g.drawImage(Main.titremenu,xb,yb,txb,tyb, null);
			//XXX recommencer

			txb=Math.min(this.getWidth()/3,400);
			tyb=txb*Main.recommencer.getHeight(null)/Main.recommencer.getWidth(null);
			xb=this.getWidth()/6-txb/2;
			yb=this.getHeight()*15/100;
			
			g.drawImage(Main.recommencer,xb,yb,txb,tyb, null);
			
			if(x>xb&& x<xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.generer();
					Main.menu=0;
				}
			}
			
			//XXX reprendre
			txb=Math.min(this.getWidth()/3,400);
			tyb=txb*Main.reprendre.getHeight(null)/Main.reprendre.getWidth(null);
			xb=this.getWidth()*5/6-txb/2;
			yb=this.getHeight()*15/100;
			
			g.drawImage(Main.reprendre,xb,yb,txb,tyb, null);
			
			if(x>xb&& x<xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.menu=0;
				}
			}

			
			//XXX hauteur
			txb=Math.min(this.getWidth()/5,200);
			tyb=txb*Main.hauteur.getHeight(null)/Main.hauteur.getWidth(null);
			xb=this.getWidth()*1/6-txb/2;
			yb=this.getHeight()*30/100;
			
			g.drawImage(Main.hauteur,xb,yb,txb,tyb, null);
			

			txb=Math.min(this.getWidth()/10,100);
			tyb=txb*Main.fleche.getHeight(null)/Main.fleche.getWidth(null);
			xb=this.getWidth()*1/6+txb/2;
			yb=this.getHeight()*38/100;
			
			g.drawImage(Main.fleche,xb,yb,txb,tyb, null);
			
			if(x>xb&& x<xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.templaby+=2;
					if(Main.templaby>Main.maxlaby)
						Main.templaby=Main.maxlaby;
				}
			}
			
			txb=-Math.min(this.getWidth()/10,100);
			tyb=-txb*Main.fleche.getHeight(null)/Main.fleche.getWidth(null);
			xb=this.getWidth()*1/6+txb/2;
			yb=this.getHeight()*38/100;
			
			g.drawImage(Main.fleche,xb,yb,txb,tyb, null);
			
			if(x<xb&& x>xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.templaby-=2;
					if(Main.templaby<Main.minlaby)
						Main.templaby=Main.minlaby;
				}
			}
			g.setColor(new Color(48,28,7,255));
			g.setFont(new Font("Impact",Font.PLAIN,tyb*2));
			g.drawString(""+((Main.templaby-1)/2), this.getWidth()/6-tyb, this.getHeight()*38/100+tyb);
			
			
			//XXX largeur

			txb=Math.min(this.getWidth()/5,200);
			tyb=txb*Main.largeur.getHeight(null)/Main.largeur.getWidth(null);
			xb=this.getWidth()*5/6-txb/2;
			yb=this.getHeight()*30/100;
			
			g.drawImage(Main.largeur,xb,yb,txb,tyb, null);
			

			txb=Math.min(this.getWidth()/10,100);
			tyb=txb*Main.fleche.getHeight(null)/Main.fleche.getWidth(null);
			xb=this.getWidth()*5/6+txb/2;
			yb=this.getHeight()*38/100;
			
			g.drawImage(Main.fleche,xb,yb,txb,tyb, null);
			
			if(x>xb&& x<xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.templabx+=2;
					if(Main.templabx>Main.maxlabx)
						Main.templabx=Main.maxlabx;
				}
			}
			
			txb=-Math.min(this.getWidth()/10,100);
			tyb=-txb*Main.fleche.getHeight(null)/Main.fleche.getWidth(null);
			xb=this.getWidth()*5/6+txb/2;
			yb=this.getHeight()*38/100;
			
			g.drawImage(Main.fleche,xb,yb,txb,tyb, null);
			
			if(x<xb&& x>xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.templabx-=2;
					if(Main.templabx<Main.minlabx)
						Main.templabx=Main.minlabx;
				}
			}
			g.setColor(new Color(48,28,7,255));
			g.setFont(new Font("Impact",Font.PLAIN,tyb*2));
			g.drawString(""+((Main.templabx-1)/2), this.getWidth()*5/6-tyb, this.getHeight()*38/100+tyb);
			
			

			//XXX nbCleG
			
			txb=Math.min(this.getWidth()/5,200);
			tyb=txb*Main.largeur.getHeight(null)/Main.largeur.getWidth(null);
			xb=this.getWidth()*1/6-txb/2;
			yb=this.getHeight()*60/100;
			
			g.drawImage(Main.nombrecles,xb,yb,txb,tyb, null);
			

			txb=Math.min(this.getWidth()/10,100);
			tyb=txb*Main.fleche.getHeight(null)/Main.fleche.getWidth(null);
			xb=this.getWidth()*1/6+txb/2;
			yb=this.getHeight()*68/100;
			
			g.drawImage(Main.fleche,xb,yb,txb,tyb, null);
			
			if(x>xb&& x<xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.maxnbCleG=(Main.templabx-1)/2*(Main.templaby-1)/2-1;
					Main.tempnbCleG+=1;
					if(Main.tempnbCleG>Main.maxnbCleG)
						Main.tempnbCleG=Main.maxnbCleG;
				}
			}
			
			txb=-Math.min(this.getWidth()/10,100);
			tyb=-txb*Main.fleche.getHeight(null)/Main.fleche.getWidth(null);
			xb=this.getWidth()*1/6+txb/2;
			yb=this.getHeight()*68/100;
			
			g.drawImage(Main.fleche,xb,yb,txb,tyb, null);
			
			if(x<xb&& x>xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.tempnbCleG-=1;
					if(Main.tempnbCleG<Main.minnbCleG)
						Main.tempnbCleG=Main.minnbCleG;
				}
			}
			g.setColor(new Color(48,28,7,255));
			g.setFont(new Font("Impact",Font.PLAIN,tyb*2));
			g.drawString(""+(Main.tempnbCleG), this.getWidth()*1/6-tyb, this.getHeight()*68/100+tyb);
			
			
			
			//XXX rapport
			
			txb=Math.min(this.getWidth()/5,200);
			tyb=txb*Main.largeur.getHeight(null)/Main.largeur.getWidth(null);
			xb=this.getWidth()*5/6-txb/2;
			yb=this.getHeight()*60/100;
			
			g.drawImage(Main.textRapport,xb,yb,txb,tyb, null);
			

			txb=Math.min(this.getWidth()/10,100);
			tyb=txb*Main.fleche.getHeight(null)/Main.fleche.getWidth(null);
			xb=this.getWidth()*5/6+txb/2;
			yb=this.getHeight()*68/100;
			
			g.drawImage(Main.fleche,xb,yb,txb,tyb, null);
			
			if(x>xb&& x<xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.temprapport+=5;
					if(Main.temprapport>Main.maxrapport)
						Main.temprapport=Main.maxrapport;
				}
			}
			
			txb=-Math.min(this.getWidth()/10,100);
			tyb=-txb*Main.fleche.getHeight(null)/Main.fleche.getWidth(null);
			xb=this.getWidth()*5/6+txb/2;
			yb=this.getHeight()*68/100;
			
			g.drawImage(Main.fleche,xb,yb,txb,tyb, null);
			
			if(x<xb&& x>xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.temprapport-=5;
					if(Main.temprapport<Main.minrapport)
						Main.temprapport=Main.minrapport;
				}
			}
			g.setColor(new Color(48,28,7,255));
			g.setFont(new Font("Impact",Font.PLAIN,tyb*2));
			g.drawString(""+(Main.temprapport), this.getWidth()*5/6-tyb, this.getHeight()*68/100+tyb);
			
			if(click)
				Main.genererPreview();
		}
		if(Main.menu==0){
				
			taille=Math.min((this.getWidth()-2*taillecadre)*4/5/Main.labx,(this.getHeight()-2*taillecadre)/Main.laby);
			
			
			//on dessine un rectangle sur la totalité de la fenètre
			g.setColor(new Color(98,98,98));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			
			g.translate((int)(this.getWidth()*4/5-Main.labx*taille-2*taillecadre)/2, (int)(this.getHeight()-Main.laby*taille-2*taillecadre)/2);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, (int)(Main.labx*taille+2*taillecadre), (int)(Main.laby*taille+2*taillecadre));
			
			g.translate(taillecadre, taillecadre);
			
			//XXX Terrain XXXX
			//On parcours tout le labyrinthe
			for(int j=0; j<Main.laby ; j++){
				for(int i=0; i<Main.labx ; i++){
					if(Main.lab[i][j]==-1){	//si la case est un mur
			//g.setColor(Color.black);
			//g.fillRect((int)(i*taille), (int)(j*taille), (int)(taille), (int)(taille));
						
						
						
						g.drawImage(Main.img, (int)(i*taille), (int)(j*taille), (int)(taille+i*taille), (int)(taille+j*taille),
								17*Main.textlab[i][j], 0+17*Main.textureterrain ,16+17*(Main.textlab[i][j]),16+17*Main.textureterrain,null) ;
						
						
						
						
						
					}else{ //sinon (la case est un sol)
						g.drawImage(Main.img, (int)(i*taille), (int)(j*taille), (int)(taille+i*taille), (int)(taille+j*taille),
								323, 0+17*Main.textureterrain, 339, 16+17*Main.textureterrain, this); //on dessine la texture du sol
					}
					/*
					g.setColor(Color.WHITE);
					g.setFont(new Font("Impact",Font.PLAIN,15));
					g.drawString(""+Main.lab[i][j], (int)(i*taille)+10, (int)(j*taille)+25);
					*/
					
					//XXX Clé XXXX
					if(Main.lab[i][j]>=10){//si la case est une clé
						int textcle = (Main.lab[i][j]-10)%3;
						g.drawImage(Main.porte, (int)(i*taille), (int)(j*taille), (int)(taille+i*taille), (int)(taille+j*taille),
								34, 0+17*textcle, 51, 17+17*textcle, null); //on dessine la texture de la clé par dessu le sol
					}
				}
			}
			//g.setColor(Color.yellow);
			//g.fillRect((int)((0)*taille), (int)((laby/4*2+1)*taille), (int)(taille), (int)(taille));
			
			//
			
			//XXX Entrée XXXX
			g.drawImage(Main.img, (int)(0*taille), (int)((Main.laby/4*2+1)*taille-0.4*taille), (int)(taille+0*taille), (int)(taille+(Main.laby/4*2+1)*taille),
					 305, 0+17*Main.textureterrain , 289, 16+17*Main.textureterrain,this);
	
			
			
			/*g.drawImage(Main.img, (int)((Main.labx-1)*taille), (int)((Main.laby/4*2+1)*taille), (int)(taille+(Main.labx-1)*taille), (int)(taille+(Main.laby/4*2+1)*taille),
					70, 228, 86, 244, this);
	*/
			//XXX Perso XXXX
			g.drawImage(Main.perso, (int)((Main.posx-0.3)*taille), (int)((Main.posy-0.8)*taille), (int)((Main.posx+Main.persx+0.3)*taille), (int)((Main.posy+Main.persy+0.1)*taille),
					Main.etatPerso[1]*24, Main.etatPerso[0]*32, (Main.etatPerso[1]+1)*24, (Main.etatPerso[0]+1)*32, this);
			//g.fillRect((int)(Main.posx*taille), (int)(Main.posy*taille), (int)(taille*Main.persx+0.1), (int)(taille*Main.persy));
	
			
			//XXX Sol (sous la porte) XXXX
			g.drawImage(Main.img, (int)((Main.labx-1)*taille), (int)((Main.laby/4*2+1)*taille), (int)(taille+(Main.labx-1)*taille), (int)(taille+(Main.laby/4*2+1)*taille),
				323, 0+17*Main.textureterrain, 339, 16+17*Main.textureterrain, this);
			
			//XXX Porte XXXX
			if(Main.nbCle<Math.min(Main.nbCleG,(Main.labx-1)/2*(Main.laby-1)/2-1)){//Si le nombre de clés recupérées est inferieur au nombre de clé générées
				//porte fermée
				g.drawImage(Main.porte, (int)((Main.labx-1)*taille), (int)((Main.laby/4*2+1)*taille-0.1*taille), (int)(taille+(Main.labx-1)*taille), (int)(taille+(Main.laby/4*2+1)*taille),
						0, 0+17*Main.textureporte, 16, 16+17*Main.textureporte, this);
			}
			else{
				//porte ouverte
				g.drawImage(Main.porte, (int)((Main.labx-1)*taille), (int)((Main.laby/4*2+1)*taille-0.1*taille), (int)(taille+(Main.labx-1)*taille), (int)(taille+(Main.laby/4*2+1)*taille),
						17, 0+17*Main.textureporte, 33, 16+17*Main.textureporte, this);
			}
			
			
			
			
			//on redécale l'origine
			g.translate(-(int)(this.getWidth()*4/5-Main.labx*taille-2*taillecadre)/2, -(int)(this.getHeight()-Main.laby*taille-2*taillecadre)/2);
			g.translate(-taillecadre, -taillecadre);
			//on dessinge le fond du chrono
			g.drawImage(Main.fondchrono, this.getWidth()*4/5, 0, this.getWidth()*1/5, this.getHeight(), null);
			
			int xchrono=this.getWidth()*4/5+this.getWidth()/45;
			int ychrono=this.getHeight()/20;
			int lchrono=this.getWidth()/20;
			int hchrono=this.getWidth()/20*62/42;
			int decalage=this.getWidth()/200;
			
			int unchrono= (int)(Main.chrono/100)%10;
			int dixchrono= (int)(Main.chrono/1000)%10;
			int centchrono= (int)(Main.chrono/10000)%10;
			
			g.drawImage(Main.chiffre, xchrono, ychrono, xchrono+lchrono,ychrono+hchrono, 
					42*centchrono, 0, 42+42*centchrono, 62, null);
			g.drawImage(Main.chiffre, xchrono+(decalage+lchrono), ychrono, xchrono+lchrono+(decalage+lchrono),ychrono+hchrono,
					42*dixchrono, 0, 42+42*dixchrono, 62, null);
			g.drawImage(Main.chiffre, xchrono+2*(decalage+lchrono), ychrono, xchrono+lchrono+2*(decalage+lchrono),ychrono+hchrono,
					42*unchrono, 0, 42+42*unchrono, 62, null);
			
			
			
			
			
			//info
			txb=this.getWidth()/8;
			tyb=txb*Main.info.getHeight(null)/Main.info.getWidth(null);
			xb=this.getWidth()*9/10-txb/2;
			yb=this.getHeight()*45/100-tyb/2;
			
			g.drawImage(Main.info,xb,yb,txb,tyb, null);
			
			//Bouton Menu
			txb=this.getWidth()/8;
			tyb=txb*Main.para.getHeight(null)/Main.para.getWidth(null);
			xb=this.getWidth()*9/10-txb/2;
			yb=this.getHeight()-tyb*5/4;
			
			g.drawImage(Main.para,xb,yb,txb,tyb, null);
			
			if(x>xb&& x<xb+txb && y>yb && y<yb+tyb){
				cursor=12;
				if(click){
					Main.menu=1;
				}
			}
			
			
			if(Main.fin){
				g.setColor(new Color(0,0,0,150));
				g.fillRect(0, 0, this.getWidth()*4/5, this.getHeight());
			g.setColor(Color.white);
			g.setFont(new Font("Impact",Font.PLAIN,this.getWidth()/40));
			if(System.currentTimeMillis()%1000>300)
			g.drawString("Appuiez sur [espace] pour continuer",(int)(this.getWidth()*2/5),(int)(this.getHeight()-10));
			g.setFont(new Font("Impact",Font.PLAIN,this.getWidth()*4/5/(Main.phrases[Main.randphrase].length()*2/3)));

				g.drawString(Main.phrases[Main.randphrase],(int)(this.getWidth()*4/5*1/2-this.getWidth()*3/14),(int)(this.getHeight()/2)+this.getWidth()*4/5/(Main.phrases[Main.randphrase].length()*4/3));
			
			}
		}

		click = false;
		Main.fen.setCursor(cursor);
		cursor=0;
	}

	//XXX Listeners XXXX
	
	public void keyPressed(KeyEvent e) {
		if(!Main.fin){
		//si la touche préssée correspond a une touche de direction
		//on passe la variable correspondante à true
		if(e.getKeyCode()==37){l=true;	Main.chronoActif=true;}
		if(e.getKeyCode()==38){u=true;	Main.chronoActif=true;}
		if(e.getKeyCode()==39){r=true;	Main.chronoActif=true;}
		if(e.getKeyCode()==40){d=true;	Main.chronoActif=true;}
		}
		if(e.getKeyCode()==32){space=true;}
		
	}

	public void keyReleased(KeyEvent e) {
		
		//si la touche relachée correspond a une touche de direction
		//on passe la variable correspondante à false
		
		if(e.getKeyCode()==37){l=false;}
		if(e.getKeyCode()==38){u=false;}
		if(e.getKeyCode()==39){r=false;}
		if(e.getKeyCode()==40){d=false;}

		if(e.getKeyCode()==32){space=false;}
		
		if(e.getKeyCode()==27){
			if(Main.menu==0)
				Main.menu=1;
			else
				Main.menu=0;
		}
	}
	public void keyTyped(KeyEvent e) {
		}
	
	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {
		x=e.getX()-8;
		y=e.getY()-34;
		click=true;
	}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		x=e.getX()-8;
		y=e.getY()-34;
			
	}

}
