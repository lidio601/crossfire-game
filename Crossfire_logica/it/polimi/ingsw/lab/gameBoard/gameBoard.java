/**
 * 
 */
package it.polimi.ingsw.lab.gameBoard;

import java.util.Set;

/**
 * Il mondo è costituito da un tabellone composto da un insieme di caselle.
 * La logica deve essere indipendente dalla particolare forma del tabellone.
 */
public /*@ pure @*/ class gameBoard {
	
	public Set<Cell> cells;
	/**
	 * il tabellone potrebbe avere un hashmap che possiede la
	 * coppia chiave-valore: personaggio-caselladovesitrova
	 * per non dover scorrere tutto il tabellone e per avere un riferimento
	 * principale quando si deve muovere, attaccare, etc.. un personaggio.
	 */
}
