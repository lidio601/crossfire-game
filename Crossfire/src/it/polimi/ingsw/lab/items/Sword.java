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
public /*@ pure @*/ abstract class Sword extends Weapon {

	/**
	 * @param name
	 */
	public Sword(String name) {
		super(name);
	}

	/** (non-Javadoc)
	 * @see it.polimi.ingsw.lab.items.Weapon#isEquipable(it.polimi.ingsw.lab.characters.Character)
	 */
	// Metodo che controlla se uno strumento pu� essere equipaggiato o meno
	// richiama la specifica dell'oggetto item
	public abstract boolean isEquipable(Character c) throws NotFoundException;
	
	// Continuo della toString di Item
	// AF(c) = (* ritorna la descrizione di questo oggetto *)
	public String toString() {
		String toRet = super.toString();
		toRet += "ed in particolare alla sottocategoria spade"+ System.getProperty("line.separator");
		return toRet;
	}
}
