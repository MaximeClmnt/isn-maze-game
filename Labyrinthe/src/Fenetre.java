import java.awt.Dimension;

import javax.swing.JFrame;


public class Fenetre extends JFrame{
	//Cr�ation de l'objet fenetre sans parametre
	public Fenetre(){

	    this.setTitle("Ma fen�tre");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setVisible(true); 

	}	
	//Cr�ation de l'objet fenetre avec le titre en parametre
	public Fenetre(String str){

	    this.setTitle(str);
	    this.setSize(1312+8, 800+34);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);   
	    this.setMinimumSize(new Dimension(720, 800));
	}	

}
