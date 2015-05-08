/**
 * 
 */
package it.polimi.ingsw.lab.items;

import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.utils.NotFoundException;

/**
 * Classe astratta che definisce la catgoria weapon
 */
public /*@ pure @*/ abstract class Weapon extends Item {

	/**
	 * Costruttore di weapon
	 * @param name
	 */
	// Costruttore che richiama quello del padre
	public Weapon(String name) {
		super(name);
	}

	
	/** (non-Javadoc)
	 * @see it.polimi.ingsw.lab.items.Item#isEquipable(it.polimi.ingsw.lab.characters.Character)
	 */
	// Metodo che controlla se uno strumento può essere equipaggiato o meno
	// richiama la specifica dell'oggetto item
	public abstract boolean isEquipable(Character c) throws NotFoundException;

	
	// Continuo della toString di Item
	// AF(c) = (* ritorna la descrizione di questo oggetto *);
	public String toString() {
		String toRet = super.toString();
		toRet += "Appartiene alla categoria armi"+ System.getProperty("line.separator");
		return toRet;
	}




}
