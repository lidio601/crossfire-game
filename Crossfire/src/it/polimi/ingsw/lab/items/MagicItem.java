/**
 * 
 */
package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;

/**
 * @author fabio
 *
 */
public abstract class MagicItem extends Weapon {
	public MagicItem(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.lab.items.Weapon#isEquipable(it.polimi.ingsw.lab.characters.Character)
	 */
	public boolean isEquipable(Character c) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.lab.items.Item#clone()
	 */
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
