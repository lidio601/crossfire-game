/**
 * 
 */
package it.polimi.ingsw.lab.items;

import java.util.Map;

/** 
 * @author fabio
 */
public abstract class Potions extends Item {

	/**
	 * @param name
	 */
	public Potions(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.lab.items.Item#isEquipable(java.util.Map)
	 */
	public boolean isEquipable(Map c) {
		// TODO Auto-generated method stub
		return false;
	}

}
