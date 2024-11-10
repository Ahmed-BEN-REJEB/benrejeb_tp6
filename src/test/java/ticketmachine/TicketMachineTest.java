package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		// Les montants ont été correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	// S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
	void nImprimePasSiBalanceInsuffisante(){
		machine.insertMoney(PRICE-1);
		assertFalse(machine.printTicket(PRICE-1), "Pas assez d'argent, on ne doit pas imprimer");
	}

	@Test
	// S4 :  on imprime le ticket si le montant inséré est suffisant
	void ImprimeSiBalancesuffisante(){
		machine.insertMoney(PRICE);
		assertTrue(machine.printTicket(PRICE), "Imprimer le ticket");
	}

	@Test
	// S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void decrementeBalance(){
		machine.insertMoney(PRICE);
		machine.printTicket(PRICE);
		assertEquals(machine.getBalance(), 0, "La balance n'est pas correctement mise à jour");
	}

	@Test
	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void mettreAJourApresImpression(){
		machine.insertMoney(PRICE);
		int prix1 = machine.getTotal();
		machine.printTicket(PRICE);
		assertEquals(machine.getTotal(), prix1+PRICE, "Operation incorrecte");
	}

	@Test
	// S7 : refund() rend correctement la monnaie
	void refundRendCorrectementMonnaie(){
		machine.insertMoney(PRICE+10);
		machine.printTicket(PRICE);
		// int prix2 = machine.getBalance();
		int monnaieRendue = machine.refund();
		assertEquals(monnaieRendue/*prix2 */, 10, "Monnaie non rendu correctement");
	}

	@Test
	// S8 : refund() remet la balance à zéro
	void refundRendBalanceAZero(){
		machine.insertMoney(PRICE);
		machine.refund();
		int refund = machine.getBalance();
		assertEquals(refund, 0, "Operation non aboutie correctement");
	}

	// @Test
	// // S9 : On ne peut pas insérer un montant négatif
	// void verifierMontant(){
	// 	int valeurNegatif = -1 * PRICE;
	// 	machine.insertMoney(valeurNegatif);
	// 	machine.refund(PRICE);
	// 	int refund = machine.getBalance();
	// 	assertEquals(refund, 0, "Operation non aboutie correctement");
	// }

	// @Test
	// // S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	// void (){
	// 	
	//
	// 	assertEquals(refund, 0, "Operation non aboutie correctement");
	// }
}
