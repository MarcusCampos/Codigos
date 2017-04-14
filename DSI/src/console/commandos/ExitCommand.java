package console.commandos;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;

public class ExitCommand implements CommandInterface {

	@Override
	public void execute() throws RemoteException {
		Scanner scanner = new Scanner(System.in);
		scanner.close();
		System.exit(0);
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