/**
 * 
 */
package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.NotFoundException;

/**
 * 
 *
 */
public abstract class MagicItem extends Weapon {
	
	
	/**
	 * Costruttore di MagicItem
	 * @param name
	 */
	// Costruttore che richiama quello del padre
	//@ requires name!=null && !name.equals("");
	//@ ensures this.name.equals(name);
	public MagicItem(String name) {
		super(name);
	}

	/** 
	 * @see it.polimi.ingsw.lab.items.Item#isEquipable(it.polimi.ingsw.lab.characters.Character)
	 */
	// Metodo che controlla se uno strumento può essere equipaggiato o meno
	// richiama la specifica dell'oggetto item
	public abstract boolean isEquipable(Character c) throws NotFoundException;


	// Continuo della toString di Item
	// AF(c) = (* ritorna la descrizione di questo oggetto *);
	public String toString() {
		String toRet = super.toString();
		toRet += "Appartiene alla categoria oggetti magici"+ System.getProperty("line.separator");
		return toRet;
	}
}
