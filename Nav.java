import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;


public class Nav {
private int pageActuelle=1;
private Image image;


public Nav() {
	
	this.pageActuelle = 1;
	}

public int getPageActuelle() {
	return pageActuelle;
}
public void setPageActuelle(int pageActuelle) {
	this.pageActuelle = pageActuelle;
}
public void dessinerpageaccueil(Graphics g) throws SlickException {
	
	image = new Image("images/accueil1.jpg");
	g.drawImage(image, 0, 0);
	g.drawRoundRect(430, 280, 200, 100, 30);
	Font font = new Font("Verdana",Font.ITALIC,40);
	TrueTypeFont ttf = new TrueTypeFont(font, true);
	ttf.drawString(460, 300, "JOUER");
		
	
	
}
public void dessinerpagefinale(Graphics g) throws SlickException {
	
	image = new Image("images/lose.jpg");
	g.drawImage(image, 0, 0);
	
	
}
public void dessinerpagegagnante(Graphics g) throws SlickException {
	image= new Image("images/win1.png");
	g.drawImage(image, 0, 0);
	 
}


public void cliquer(int x,int y) {
	if(x>400 && x<700 &&  y>200 && y<400) {
		pageActuelle=2;
		
	}
}




}
