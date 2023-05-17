import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Accueil extends BasicGame {

	private Vaisseau Vj;
	private ArrayList<Projectile> p = new ArrayList<Projectile>();
	ArrayList<Ennemi> Ve = new ArrayList<Ennemi>();
	int[] deplacementE;
	private int tempstir;
	int timer;
	int timer1;
	private Music music;
	private int c;
	private Nav nav;
	private int cpt;
	Image image;
	float image_y = 0;
	Image reverse;
	float reverse_y = 0;
	private int score;

	public Accueil(String title) {
		super(title);

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Ve.clear();
		timer = 0;
		tempstir = 0;
		cpt = 0;
		score = 0;
		nav = new Nav();
		music = new Music("images/music.ogg");
		music.loop();

		// image de fond
		image = new Image("images/background2.jpg");
		reverse = new Image("images/background2.jpg");
		// initialisation du vaisseau joueur
		Vj = new Vaisseau(new Image("images/VJ1.png"), 540, 600, gc);

		// initialisation du vaisseau ennemi
		Image image1 = new Image("images/VE1.png");

		for (int i = 0; i < 5; i++) {
			Ve.add(new Ennemi(i * (image1.getWidth() + 100), -100, 1, gc));

		}

		deplacementE = new int[Ve.size()];

		reverse = image.getFlippedCopy(false, true);
		image_y = 0;
		reverse_y = image_y - image.getHeight() + 3;

	}

	public void render(GameContainer gc, Graphics g) throws SlickException {

		if (nav.getPageActuelle() == 1) {
			nav.dessinerpageaccueil(g);
		} else if (nav.getPageActuelle() == 2) {
			// image de defilement en background
			g.drawImage(reverse, 0, reverse_y, new Color(200, 200, 200, 255));
			g.drawImage(image, 0, image_y, new Color(200, 200, 200, 255));
			// affichage du score
			Font font = new Font("Verdana", Font.ITALIC, 20);
			TrueTypeFont ttf = new TrueTypeFont(font, true);
			ttf.drawString(950, 20, "SCORE:" + score);
			ttf.drawString(950, 40, "VIE:" + Vj.getVieJ());
		
			// Dessiner le vaisseau joueur
			if (Vj.getVieJ() > 0) {
				Vj.dessiner(g);
			}
			// Dessin des projectiles du joueur
			if (Vj.getVieJ() > 0) {
				for (int i = 0; i < p.size(); i++) {
					if (p.get(i) != null && p.get(i).en_cours)
						p.get(i).dessiner(g);
				}
			}

			// affichage des vaisseaux ennemis
			for (int i = 0; i < Ve.size(); i++) {
				if (Ve.get(i).getVie() > 0 && Ve.get(i) != null) {
					Ve.get(i).dessiner(g);
				}

			}

			// affichage des pages de navigation
		} else if (nav.getPageActuelle() == 3) {
			nav.dessinerpagefinale(g);
			Font font = new Font("Verdana", Font.ITALIC, 40);
			TrueTypeFont ttf = new TrueTypeFont(font, true);
			ttf.drawString( 550, 330, "SCORE:" + score);
		} else if (nav.getPageActuelle() == 4) {
			nav.dessinerpagegagnante(g);
			Font font = new Font("Verdana", Font.ITALIC, 40);
			TrueTypeFont ttf = new TrueTypeFont(font, true);
			ttf.drawString(440, 110, "SCORE:" + score);
		}
		
	}

	public void update(GameContainer gc, int delta) throws SlickException {

		// Mise à jour du temps
		timer += delta;
		tempstir += delta;

		// acceder à la page principale avec un clic
		Input inp = gc.getInput();
		if(nav.getPageActuelle()==1) {
			if (inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {

				nav.cliquer(inp.getMouseX(), inp.getMouseY());
			}
			
		}
		
		
		// Déplacement du joueur
		if (inp.isKeyDown(Input.KEY_LEFT)) {
			Vj.setX(Vj.getX() - delta, gc);
		}
		if (inp.isKeyDown(Input.KEY_RIGHT)) {
			Vj.droite(delta, gc);
		}
		if (inp.isKeyDown(Input.KEY_UP)) {
			Vj.setY(Vj.getY() - delta, gc);
		}
		if (inp.isKeyDown(Input.KEY_DOWN)) {
			Vj.bas(delta, gc);
		}

		if (nav.getPageActuelle() == 2) {
			// deplacement des projectiles
			for (int i = 0; i < p.size(); i++) {
				p.get(i).deplacer(delta);
			}
			// Tir des projectiles par le vaisseau joueur
			if (inp.isKeyPressed(Input.KEY_SPACE)) {
				if (Vj.getVieJ() > 0) {
					p.add(new Projectile(true, null, Vj));
				}
			}

			// Mise en place des ennemis au debut jeu
			for (int i = 0; i < Ve.size(); i++) {
				if (Ve.get(i).getY() < 0) {
					Ve.get(i).bas(delta, gc);
				}
			}

			// Deplacemengt des ennemis

			if (timer >= 1500) {

				for (int i = 0; i < Ve.size(); i++) {

					deplacementE[i] = (int) (Math.random() * 4);
				}

				timer = 0;
			}

			for (int i = 0; i < Ve.size(); i++) {

				if (Ve.get(i).getY() < 0) {
					Ve.get(i).bas(delta, gc);
				} else {
					if (deplacementE[i] == 0 && !(Ve.get(i).collisionhaut(gc, Ve, i))) {
						Ve.get(i).haut(delta, gc);
					} else if ((Ve.get(i).collisionhaut(gc, Ve, i))) {
						Ve.get(i).bas(delta, gc);
					}
					if (deplacementE[i] == 1 && !(Ve.get(i).collisionbas(gc, Ve, i))) {
						Ve.get(i).bas(delta, gc);
					} else if ((Ve.get(i).collisionbas(gc, Ve, i))) {
						Ve.get(i).haut(delta, gc);
					}
					if (deplacementE[i] == 2 && !(Ve.get(i).collisiondroite(gc, Ve, i))) {
						Ve.get(i).droite(delta, gc);
					} else if ((Ve.get(i).collisiondroite(gc, Ve, i))) {
						Ve.get(i).gauche(delta, gc);
					}
					if (deplacementE[i] == 3 && !(Ve.get(i).collisiongauche(gc, Ve, i))) {

						Ve.get(i).gauche(delta, gc);
					} else if ((Ve.get(i).collisiongauche(gc, Ve, i))) {
						Ve.get(i).droite(delta, gc);
					}
				}
 
			}

			// tir des projectiles par les vaisseau ennemis
			if (tempstir > 1700) { 
				for (int i = 0; i < Ve.size(); i++) {

					tempstir += (int) (Math.random() * (6 + 4) - 4) * 1000;
					if (Ve.get(i).getVie() > 0) {
						Ve.get(i).tirer(p);
					}

				}
				tempstir = 0;

			}

			// collision projectile avec le vaisseau joueur

			for (int i = 0; i < p.size(); i++) {
				if (p.get(i).collision(Vj, null) && p.get(i).en_cours) {
					Vj.setVieJ(Vj.getVieJ() - 1);
					p.get(i).en_cours = false;
				}
			}

			// collision entre le projectiles et les vaisseaux ennemis

			for (int j = 0; j < Ve.size(); j++) {
				for (int i = 0; i < p.size(); i++) {
					if (p.get(i).collision(null, Ve.get(j)) && Ve.get(j) != null && p.get(i) != null
							&& p.get(i).en_cours) {
						Ve.get(j).setVie(Ve.get(j).getVie() - 1);
						p.get(i).en_cours = false;
						if (Ve.get(j).getType() == 1) {
							score += 1;
						} else if (Ve.get(j).getType() == 2) {
							score += 3;
						} else {
							score += 5;
						}

						break;

					}
				}

			}
			// Appel des ennemis de niveau superieur

			for (int i = 0; i < Ve.size(); i++) {
				if (Ve.get(i).getVie() <= 0) {
					c = Ve.get(i).getType();
					Ve.remove(i);
					if (c + 1 <= 3) {
						Ve.add(new Ennemi(100 * (int) (Math.random() * i), -300, c + 1, gc));
					}
					i--;
				}
			}

			// apparition du deuxieme joueur

			if (Vj.getVieJ() <= 0) {
				Vj = new Vaisseau(new Image("images/VJ2.png"), 600, 600, gc);
				cpt++;
			}
			// page de nav game over
			if (cpt == 2) {
				nav.setPageActuelle(3);
			}

			// page de nav winner
			if (Ve.size() == 0) {
				nav.setPageActuelle(4);
			}

			// defilement de l'ecran

			image_y = image_y + 300f * delta / 1000f;
			reverse_y = reverse_y + 300f * delta / 1000f;

			if (image_y > image.getHeight()) {
				image_y = reverse_y - image.getHeight();
			}
			if (reverse_y > image.getHeight()) {
				reverse_y = image_y - image.getHeight();
			}

		}
	}
}
