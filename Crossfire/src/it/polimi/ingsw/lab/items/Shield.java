package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.Ability;

public class Shield extends Item{
	public Shield(String name) {
		super(name);
		this.modificator.put("destrezza",new Integer(-10));
		//diminuisce attacchi subiti di dieci bonus
	}
	
	//*@ requires
	//*@ ensures 
	public boolean isEquipable(Character c) {
		return true;
	}
}
