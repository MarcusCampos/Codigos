package console.commandos;

import java.rmi.RemoteException;
import java.util.UUID;

import br.usp.each.PartRepositoryInterface;

public class RepositoryCommand implements CommandInterface {

	/**
	 * Repositorio de pecas que sera manipulado
	 */
	private PartRepositoryInterface repository;
	
	/**
	 * Parametro para exibir somente o nome do repositorio
	 */
	boolean paramName = false;
	
	/**
	 * Parametro para exibir somente o numero de pecas contidas no repositorio
	 */
	boolean paramNumParts = false;

	/**
	 * Inicializa o interpretador de comandos que manipulara o repositorio
	 * recebido por parametro
	 *
	 * @param repository Objeto do repositorio de pecas
	 */
	public RepositoryCommand(PartRepositoryInterface repository) {
		this.repository = repository;
	}

	@Override
	public void execute() throws RemoteException {
		System.out.println("\nReposit�rio de pe�as");
		System.out.println("Nome: " + this.repository.getRepositoryName());
		System.out.println("Quantidade de pe�as: "
			+ this.repository.countParts() + "\n");
	}

	@Override
	public void execute(UUID id) throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void param(String name) {
		// TODO Auto-generated method stub
	}
}