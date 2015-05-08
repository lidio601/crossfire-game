package it.polimi.ingsw.lab.characters;

public class Ogre extends Character{

	//*@ requires name!=null && !name.equals("")
	//*@
	public Ogre(String name) {
		super(name);
	}

}
