package it.polimi.ingsw.lab.characters;

public class Magician extends Character{

	
	//*@ requires name!=null && !name.equals("")
	//*@
	public Magician(String name) {
		super(name);
	}

}
