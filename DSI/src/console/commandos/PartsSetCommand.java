package console.commandos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import br.usp.each.PartRepositoryInterface;

public class PartsSetCommand implements CommandInterface {

	/**
	 * Repositorio de pecas que sera manipulado
	 */
	private PartRepositoryInterface repository;

	/**
	 * Inicializa o interpretador de comandos que manipulara o repositorio
	 * recebido por parametro
	 *
	 * @param repository Objeto do repositorio de pecas
	 */
	public PartsSetCommand(PartRepositoryInterface repository) {
		this.repository = repository;
	}

	@Override
	public void execute() throws RemoteException {
		ArrayList<String> list = this.repository.listAllParts();

		if (list.isEmpty()) {
			System.out.println("\nNao há peças no repositório\n");
			return;
		}

		System.out.println();
		Iterator<String> iterator = list.iterator();
		int i = 0;

		while (iterator.hasNext()) {
			System.out.println(++i + ": " + iterator.next());
		}

		System.out.println();
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
