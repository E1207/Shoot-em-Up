import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.SlickException;



public class Main { 


	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub

		Accueil jeu = new Accueil("jeu");
		AppGameContainer app = new AppGameContainer(jeu,1080,720,false);
		app.setShowFPS(false);
		app.start();
		
	}

	
	
	

}
