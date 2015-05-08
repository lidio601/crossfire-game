/**
 * 
 */
package it.polimi.ingsw.lab.gameBoard;

import it.polimi.ingsw.lab.characters.Character;
import it.polimi.ingsw.lab.items.*;

import java.util.*;

public abstract class Cell {
	/**
	 * ogni cella ha un riferimento alle sue vicine
	 */
	private /*@ spec_public non_null @*/ Set<Cell> nextCells;

	/**
	 * Su ogni casella può risiedere un solo personaggio
	 */
	private /*@ spec_public @*/ Character who;
	
	/**
	 * Su ogni casella può risiedere un numero arbitrario di oggetti
	 */
	private /*@ spec_public non_null @*/ Set<Item> what;
	
	/**
	 * Constructors
	 */
	
	/**
	 * Costruttore della classe Cell
	 * @param nextCells
	 * @param what
	 */
	public Cell(Set<Cell> nextCells, Set<Item> what) {
		if(nextCells.size() == 0) {
			this.nextCells = new HashSet<Cell>();
		}
		else {
			this.nextCells = nextCells;
		}
		this.who = null;
		if(what.size() == 0) {
			this.what = new HashSet<Item>();
		}
		else {
			this.what = what;			
		}
	}
	
	/**
	 * Costruttore della classe Cell
	 * @param nextCells
	 */
	public Cell(Set<Cell> nextCells) {
		if(nextCells.size() == 0) {
			this.nextCells = new HashSet<Cell>();
		}
		else {
			this.nextCells = nextCells;
		}
		this.who = null;
		this.what = new HashSet<Item>();
	}
	
	/**
	 * Costruttore della classe Cell
	 * @param what
	 */
//	public Cell(Set<Item> what) {
//		this.nextCells = new HashSet<Cell>();
//		this.who = null;
//		if(what.size() == 0) {
//			this.what = new HashSet<Item>();
//		}
//		else {
//			this.what = what;			
//		}
//	}
	
	/**
	 * Getters
	 */

	/**
	 * @return Set<Cell> di Celle con i riferimenti alle sue vicine
	 */
	public Set<Cell> getNextCells() {
		return this.nextCells;
	}
	
	/**
	 * Setters
	 */
	
	/**
	 * @param next Cell da aggiungere alla collezione di celle vicine
	 * alla cella su cui viene chiamato questo metodo
	 * @return nothing
	 */
	//@ requires true;
	//@ ensures this.isNextCell(next);
	public void setNextCells(Cell next) {
		if(!this.isNextCell(next)) {
			next.setNextCells(this);
		}
	}
	
	/**
	 * Observers
	 */
	
	/**
	 * è una cella vicina
	 * @param next Cell da confrontare
	 * @return boolean true se la cella passata come parametro è vicina
	 */
	//@ requires true;
	//@ ensures (\result == true) <==>
	//@ this.nextCells.contains(next)
	//@ also
	//@ (\result == false) <==>
	//@ !this.nextCells.contains(next);
	private /*@ pure @*/ boolean isNextCell(Cell next) {
		return this.nextCells.contains(next);
	}
	
	/**
	 * In questa classe implemento i controlli di base; nelle
	 * classi che mi ereditano implementerò dei controlli più
	 * raffinati, in base alla tipologia di Cell.
	 * @return boolean true se il personaggio passato come parametro è
	 * spostabile in questa casella.
	 */
	//@ requires newCharacter != null;
	//@ ensures !this.who.equals(newCharacter)
	//@ && ;
	public boolean isMoveableTo(Character newCharacter) {
		boolean toRet = false;
		/**
		 * non è che mi stai passando lo stesso personaggio
		 * che già è in questa cella?!
		 */
		toRet = toRet & !this.who.equals(newCharacter);
		/**
		 * se è già presente un Character non posso
		 * spostarne un altro su questa cella
		 */
		toRet = toRet & this.who != null;
		/**
		 * ritorno al chiamante
		 */
		return toRet;
	}
	
	/**
	 * Modificators
	 */
	
	/**
	 * @param who
	 * @param where
	 * @return
	 */
	public boolean moveCharacter(Character who, Cell where) {
		if(where.isMoveableTo(who))	return false;
		if(isNextCell(where)) where.moveCharacter(who);
		//tolgo who dalla mia casella
		return true;
	}
	
	/**
	 * @param who
	 * @return
	 */
	public boolean moveCharacter(Character who) {
		return true;
	}
}
