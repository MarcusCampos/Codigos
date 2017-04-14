import java.rmi.Naming;

/**
 * @author Leonardo Rocha <leonardosrocha@outlook.com>
 */
public class Server {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Nome do servidor é parâmetro obrigatório");
			System.exit(1);
		}

		String name = args[0];

		try {
			System.out.print("Iniciando servidor... ");
			PartRepository repository = new PartRepository(name);
			Naming.rebind(name, repository);
			System.out.print("OK\n");
		} catch (Exception ex) {
			System.err.print("FALHOU\n");
			System.err.println(ex.getMessage());
		}
	}
}