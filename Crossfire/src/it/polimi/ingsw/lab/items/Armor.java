/**
 * 
 */
package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;

/**
 * @author fabio
 *
 */
public abstract class Armor extends Item {

	public Armor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.lab.items.Item#clone()
	 */
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.lab.items.Item#isEquipable(it.polimi.ingsw.lab.characters.Character)
	 */
	public boolean isEquipable(Character c) {
		// TODO Auto-generated method stub
		return false;
	}

}
