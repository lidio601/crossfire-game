package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.utils.Ability;
import it.polimi.ingsw.lab.utils.NotFoundException;

import java.util.Map;
import it.polimi.ingsw.lab.characters.Character;

public /*@ pure @*/ class MagicWand extends Weapon{
	public MagicWand() {
		super("MaqicWand");
		this.requisiti.put("abilitàmagiche",new Integer (70));
	}

	//*@ requires
	//*@ ensures 
	public boolean isEquipable(Character c) {
		Ability am = null;
		try {
			am = c.getAbility("abilitàmagiche");
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int req =  (Integer) requisiti.get("abilitàmagiche");
		
		if(am.getValue()<=req)
			return false;
		return true;
	}

}
