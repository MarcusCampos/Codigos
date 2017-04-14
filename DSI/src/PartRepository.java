import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author Leonardo Rocha <leonardo.lsrocha@gmail.com>
 */
public class PartRepository extends UnicastRemoteObject
	implements PartRepositoryInterface {

	/**
	 * Identificador da versao da classe
	 */
	private static final long serialVersionUID = 3214325684970649889L;

	/**
	 * Nome do repositorio
	 */
	private String name;

	/**
	 * Mapa indexado pelo codigo das pecas armazenadas
	 */
	private HashMap<UUID, Part> parts = new HashMap<UUID, Part>();

	/**
	 * 
	 * @param name Nome do repositorio
	 * @throws RemoteException
	 */
	public PartRepository(String name) throws RemoteException {
		super();
		this.name = name;
	}

	@Override
	public void addPart(Part part) throws RemoteException {
		this.parts.put(part.getCode(), part);
	}

	@Override
	public int countParts() throws RemoteException {
		return this.parts.size();
	}

	@Override
	public Part getPart(UUID id) throws RemoteException {
		return this.parts.get(id);
	}

	@Override
	public String getRepositoryName() throws RemoteException {
		return this.name;
	}

	@Override
	public ArrayList<String> listAllParts() throws RemoteException {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Part> list = new ArrayList<Part>(this.parts.values());
		Iterator<Part> iterator = list.iterator();

		while (iterator.hasNext()) {
			Part part = iterator.next();
			names.add(part.name + " | " + part.getCode().toString());
		}

		Collections.sort(names);

		return names;
	}
}