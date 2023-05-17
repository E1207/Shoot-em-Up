import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Projectile {
 private  float x,y;
 private Color couleur;
 private int vy;
 boolean tirerpar;
 boolean en_cours=true;
 
 
public Projectile(boolean tirerpar,Ennemi e, Vaisseau v) {
	if(v!=null) {
		this.x = v.getX()+v.getImage().getWidth()/2;
		this.y = v.getY();
		this.vy =500;
		this.couleur = new Color (Color.red);
		this.tirerpar= tirerpar;
	}else if(e !=null) {
		this.x = e.getX()+e.getImage().getWidth()/2;
		this.y = e.getY()+e.getImage().getHeight();
		this.vy =500;
		this.couleur = new Color (Color.green);
		this.tirerpar= tirerpar;
	}	
}
 public float getX() {
	return x;
}

public void setX(float x) {
	this.x = x;
}


public float getY() {
	return y;
}


public void setY(float y) {
	this.y = y;
}


public Color getCouleur() {
	return couleur;
}


public void setCouleur(Color couleur) {
	this.couleur = couleur;
}



public boolean isEn_cours() {
	return en_cours;
}


public void setEn_cours(boolean en_cours) {
	this.en_cours = en_cours;
}


public int getVy() {
	return vy;
}


public void setVy(int vy) {
	this.vy = vy;
}


public boolean isTirerpar() {
	return tirerpar;
}


public void setTirerpar(boolean tirerpar) {
	this.tirerpar = tirerpar;
}


public void dessiner(Graphics g) {
	 g.setColor(couleur);
	 g.fillRoundRect(x, y, 4, 5, 3);
 }
 public void deplacer (int delta) {
	 if(tirerpar==true) {
		 y -=   vy * delta/1000f; //deplacement du projectile du joueur
	 }else {
		 y +=   vy * delta/1000f; //deplacement du projectile ennemi
	 }
	 
 }
 // collision du projectile avec un vaisseau ou avec l'ennemi
 public boolean collision (Vaisseau v, Ennemi e) {
	 if(v!=null) {
	 if(!tirerpar &&( x>v.getX() && x<v.getX()+v.getImage().getWidth())) {
		if(y>v.getY()&&y<v.getY()+v.getImage().getHeight()) {
			return true;
		}	
	 }
	 }else if(e!=null) {
		 if(tirerpar &&( x>e.getX() && x<e.getX()+e.getImage().getWidth())) {
				if(y<e.getY()+e.getImage().getHeight()&&y>e.getY()) {
					return true;
				}
				
			 }
	 }
	 return false;
 }

}
