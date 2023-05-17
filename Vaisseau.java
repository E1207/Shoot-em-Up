import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Vaisseau {

	private Image image;
	private float x;
	private float y;
	private int vieJ;
	private boolean estvivant;

	public Vaisseau(Image image, float x, float y,GameContainer gc) {
		
		this.image = image;
		if(this.x>=0 && this.x<gc.getWidth()&& this.y>=0 && this.y<gc.getHeight())
		this.x = x;
		this.y = y;
		this.vieJ=4;
		estvivant=true;
		
	}

	

	public boolean isEstvivant() {
		return estvivant;
	}

	public void setEstvivant(boolean estvivant) {
		this.estvivant = estvivant;
	}
	

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public float getX() {
		return x;
	}

	public void setX(float x,GameContainer gc) {
		if(x>=0 && x<gc.getWidth())
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y,GameContainer gc) { 
		if(y > gc.getHeight() /2f && y <gc.getHeight())
		this.y = y;
	}
	
	public int getVieJ() {
		return vieJ;
	}

	public void setVieJ(int vieJ) {
		this.vieJ = vieJ;
	}
	

	public void dessiner(Graphics g) {
		g.drawImage(image, x, y);
	}
	public void droite(int delta,GameContainer gc) {
		if(this.x>=0 && this.x<gc.getWidth()-image.getWidth()) {
			this.x += delta;
		}
	}
	public void bas(int delta,GameContainer gc) {
		if(this.y>=gc.getHeight() /2f && this.y<gc.getHeight()-image.getHeight()) {
			this.y += delta;
		}
	}
	
	
	
	public void tirer (ArrayList<Projectile> p) {
		
		p.add(new Projectile(true,null,this));
			
	}
	
}
