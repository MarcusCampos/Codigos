package console.commandos;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import br.usp.each.AggregatedPart;
import br.usp.each.Part;
import br.usp.each.PartRepositoryInterface;

public class PartCommand implements CommandInterface {

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
	public PartCommand(PartRepositoryInterface repository) {
		this.repository = repository;
	}

	/**
	 * 
	 * @param part
	 * @param repo
	 * @param UUID
	 */
	private void insertPart(AggregatedPart part, String repo, String UUID,
		int amount) throws RemoteException {
		Part primitive = this.getRemotePart(repo, UUID);
		part.addSubcomponent(primitive, amount);
	}

	@Override
	public void execute() throws RemoteException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("\nAdicionar nova pe�a");
		System.out.print("Nome da pe�a: ");
		String name = scanner.nextLine();

		System.out.print("Descri��o: ");
		String description = scanner.nextLine();
		String isPrimitive;

		do {
			System.out.print("� primitiva? (S/N) ");
			isPrimitive = scanner.nextLine().toLowerCase();
		} while (!isPrimitive.equals("s") && !isPrimitive.equals("n"));

		String thisRepository = this.repository.getRepositoryName();
		if (isPrimitive.equals("s")) {
			Part part = new Part(name, description, thisRepository);
			this.repository.addPart(part);
			System.out.println();
			return;
		}

		AggregatedPart part = new AggregatedPart(name, description,
			thisRepository);
		String more = "n";

		do {
			System.out.print("\nReposit�rio: ");
			String repositoryName = scanner.nextLine();

			System.out.print("UUID: ");
			String UUIDString = scanner.nextLine();

			System.out.print("Quantidade: ");
			int amount = Integer.parseInt(scanner.nextLine());

			do {
				System.out.print("Adicionar mais subpe�as? (S/N) ");
				more = scanner.nextLine().toLowerCase();
			} while (!more.equals("s") && !more.equals("n"));

			this.insertPart(part, repositoryName, UUIDString, amount);
		} while (more.equals("s"));

		this.repository.addPart(part);
		System.out.println();
	}

	@Override
	public void execute(UUID id) throws RemoteException {
		Part part = this.repository.getPart(id);

		if (part == null) {
			System.out.println("\nPe�a n�o encontrada neste reposit�rio\n");
			return;
		}

		System.out.println("\nPe�a");
		System.out.println("Nome: " + part.getName());
		System.out.println("Descri��o: " + part.getDescription());

		String type = part.isPrimitive() ? "primitiva" : "composta";
		System.out.println("Tipo: " + type);	

		if (!part.isPrimitive()) {
			AggregatedPart aggregated = (AggregatedPart) part;
			int subcomponents = aggregated.countSubcomponents();
			System.out.println("Quantidade de subpe�as: " + subcomponents);
			this.listSubcomponents(aggregated);
		}

		System.out.println();
	}

	/**
	 * 
	 * @param repositoryName
	 * @param code
	 * @return
	 * @throws RemoteException
	 */
	public Part getRemotePart(String repositoryName, String code)
		throws RemoteException {
		PartRepositoryInterface repository = null;

		if (repositoryName.equals(this.repository.getRepositoryName())) {
			repository = this.repository;
		} else {
			try {
				repository = (PartRepositoryInterface) Naming.lookup(
					repositoryName);
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}

		return repository.getPart(UUID.fromString(code));
	}

	/**
	 * 
	 * @param aggregated
	 */
	public void listSubcomponents(AggregatedPart aggregated) {
		if (aggregated == null) return;
		HashMap<Part, Integer> components = aggregated.getSubcomponents();

		Set<Part> parts = components.keySet();
		Iterator<Part> iterator = parts.iterator();
		int i = 1;

		System.out.println("\nSubpe�as:");
		while (iterator.hasNext()) {
			Part part = iterator.next();
			int amount = components.get(part);

			String item = i++ + ": "
				+ part.getName() + " | "
				+ part.getRepository() + " | "
				+ amount + " unidade(s) | "
				+ part.getCode();

			System.out.println(item);
		}
	}

	@Override
	public void param(String name) {
		// TODO Auto-generated method stub
	}
}
