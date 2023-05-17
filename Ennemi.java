import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ennemi {
	private Image image;
	private float x;
	private float y;
	private float v;
	private int vie;
	private int type;

	public Ennemi(float x, float y, int type, GameContainer gc) throws SlickException {

		if (this.x >= 0 && this.x < gc.getWidth() && this.y >= 0 && this.y < gc.getHeight()) {
			this.x = x;
			this.y = y;
			// this.vie=3;
			this.type = type;
			// this.v=200;
			// this.image = image;
		}
		if (type == 1) {
			this.image = new Image("images/VE1.png");
			this.v = 100;
			this.vie = 2;
		}
		if (type == 2) {
			this.image = new Image("images/VE2.png");
			this.v = 200;
			this.vie = 4;
		}
		if (type == 3) {
			this.image = new Image("images/VE3.png");
			this.v = 300;
			this.vie = 6;
		}
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public void setX(float x, GameContainer gc) {
		if (x > 0 && x < gc.getWidth())
			this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y, GameContainer gc) {
		if (y < gc.getHeight() / 2f)
			this.y = y;
	}

	public void dessiner(Graphics g) {
		g.drawImage(image, x, y);
	}

	public void droite(int delta, GameContainer gc) {
		if (this.x < gc.getWidth() - image.getWidth()) {
			this.x += v * delta / 1000f;
		}
	}

	public void gauche(int delta, GameContainer gc) {
		if (this.x > 0) {
			this.x -= v * delta / 1000f;
		}
	}

	public void bas(int delta, GameContainer gc) {
		if (this.y < gc.getHeight() / 2f) {
			this.y += v * delta / 1000f;
		}
	}

	public void haut(int delta, GameContainer gc) {
		if (this.y > 0) {
			this.y -= v * delta / 1000f;
		}
	}

	// collision vers le haut avec un autre ennemi
	public boolean collisionhaut(GameContainer gc, ArrayList<Ennemi> Ve, int ind) {
		int c = 0;
		for (int i = 0; i < Ve.size(); i++) {
			if (i != ind && ((Ve.get(i).getY() > this.y + this.image.getHeight()
					|| (Ve.get(i).getY() + Ve.get(i).image.getHeight() + 1 < this.y)
					|| (Ve.get(i).getX() + image.getWidth() < this.x
							|| Ve.get(i).getX() > this.x + image.getWidth())))) {
				c++;

			}
		}
		if (c == Ve.size() - 1) {
			return false;
		}
		return true;
	}

	public boolean collisionbas(GameContainer gc, ArrayList<Ennemi> Ve, int ind) {

		int c = 0;
		for (int i = 0; i < Ve.size(); i++) {
			if (i != ind && ((Ve.get(i).getY() > this.y + this.image.getHeight() + 1
					|| (Ve.get(i).getY() + Ve.get(i).image.getHeight() < this.y)
					|| (Ve.get(i).getX() + image.getWidth() < this.x
							|| Ve.get(i).getX() > this.x + image.getWidth())))) {
				c++;

			}
		}
		if (c == Ve.size() - 1) {
			return false;
		}
		return true;
	}

	public boolean collisiondroite(GameContainer gc, ArrayList<Ennemi> Ve, int ind) {

		int c = 0;
		for (int i = 0; i < Ve.size(); i++) {
			if (i != ind && ((Ve.get(i).getX() > this.x + this.image.getWidth() + 1
					|| (Ve.get(i).getX() + Ve.get(i).image.getWidth() < this.x)
					|| (Ve.get(i).getY() + image.getHeight() < this.y
							|| Ve.get(i).getY() > this.y + image.getHeight())))) {
				c++;

			}
		}
		if (c == Ve.size() - 1) {
			return false;
		}
		return true;
	}

	public boolean collisiongauche(GameContainer gc, ArrayList<Ennemi> Ve, int ind) {

		int c = 0;
		for (int i = 0; i < Ve.size(); i++) {
			if (i != ind && ((Ve.get(i).getX() > this.x + this.image.getWidth()
					|| (Ve.get(i).getX() + Ve.get(i).image.getWidth() + 1 < this.x)
					|| (Ve.get(i).getY() + image.getHeight() < this.y
							|| Ve.get(i).getY() > this.y + image.getHeight())))) {
				c++;

			}
		}
		if (c == Ve.size() - 1) {
			return false;
		}
		return true;
	}

	public void tirer(ArrayList<Projectile> p) {

		p.add(new Projectile(false, this, null));

	}

}
