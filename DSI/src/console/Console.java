package console;

import java.rmi.Naming;
import java.util.Scanner;
import java.util.UUID;
import console.commandos.*;



/**
 * @author Leonardo Rocha <leonardo.lsrocha@gmail.com>
 */
public class Console {

	private static PartRepositoryInterface repository;

	/**
	 * Fragmenta a linha de comando recebida
	 *
	 * @param line	Linha capturada pelo teclado
	 * @return		Vetor de parametros
	 */
	private static String[] explodeLine(String line) {
		return line.split(" ");
	}

	/**
	 * Identifica o comando solicitado
	 *
	 * @param commandName	Nome do comando
	 * @return				Objeto para execucao do comando identificado
	 */
	private static CommandInterface identifyCommand(String command) {
		if (command == null || command.isEmpty()) return null;

		switch (command) {
			case "repositorio":
				return new RepositoryCommand(Console.repository);
			case "pecas":
				return new PartsSetCommand(Console.repository);
			case "peca":
				return new PartCommand(Console.repository);
			case "sair":
				return new ExitCommand();
		}

		return null;
	}

	/**
	 * Exibe o manual do console
	 */
	private static void help() {
		System.out.println("\n===========");
		System.out.println("== Ajuda ==");
		System.out.println("===========\n");

		System.out.println("repositorio");
		System.out.println("\tLista informa��es do reposit�rio atual\n");

		System.out.println("pecas");
		System.out.println("\tLista pe�as contidas no reposit�rio atual\n");

		System.out.println("peca");
		System.out.println("\tInsere uma nova pe�a ao reposit�rio\n");

		System.out.println("peca [UUID da pe�a]");
		System.out.println("\tLista informa��es de uma pe�a espec�fica\n");

		System.out.println("sair");
		System.out.println("\tSair da aplica��o\n");
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Nome do reposit�rio � par�metro obrigat�rio");
			System.exit(1);
		}

		String repositoryName = args[0];

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Conectando ao reposit�rio... ");
			Console.repository = (PartRepositoryInterface) Naming.lookup(
				repositoryName);
			System.out.println("OK\n");
		} catch (Exception ex) {
			System.err.print("FALHOU\n");
			System.err.println(ex.getMessage());
			System.exit(1);
		}

		System.out.println("Aguardando comando... Se necess�rio, "
			+ "digite \"help\".");
		System.out.print("$ ");

		while (true) {
			if (!scanner.hasNextLine()) continue;
			String line = scanner.nextLine();

			String[] components = Console.explodeLine(line);
			if (components.length == 0) continue;

			CommandInterface command = Console.identifyCommand(components[0]);

			if (command == null) {
				Console.help();
				System.out.print("$ ");
				continue;
			}

			try {
				if (components.length == 2) {
					command.execute(UUID.fromString(components[1]));
				} else {
					command.execute();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}

			System.out.print("$ ");
		}
	}
}