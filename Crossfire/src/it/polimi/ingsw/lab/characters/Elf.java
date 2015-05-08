package it.polimi.ingsw.lab.characters;

public class Elf extends Character{

	//*@ requires name!=null && !name.equals("")
	//*@
	public Elf(String name) {
		super(name);
	}

}
