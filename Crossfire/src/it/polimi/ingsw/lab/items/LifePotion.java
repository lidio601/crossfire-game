package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;
import java.util.Map;

public class LifePotion extends Potions {
	
	public LifePotion(String name) {
		super(name);
	}
	
	//*@ 
	//*@
	public boolean isEquipable(Character c) {
		//decidere come fare la vita e in base a quello implemetare
		return false;
	}

	public boolean isEquipable(Map c) {
		return false;
	}



}
