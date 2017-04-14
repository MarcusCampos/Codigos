package console.commandos;

import java.rmi.RemoteException;
import java.util.UUID;

public interface CommandInterface {

	/**
	 * Inicia execucao do comando
	 */
	public void execute() throws RemoteException;

	/**
	 * Inicia execucao do comando passando identificador da peca
	 *
	 * @param id Identificador unico da peca
	 */
	public void execute(UUID id) throws RemoteException;

	/**
	 * Insere parametro para a execucao
	 *
	 * @param name Parametro
	 */
	public void param(String name);
}