
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Leonardo Rocha <leonardo.lsrocha@gmail.com>
 */
public interface PartRepositoryInterface extends Remote {

	/**
	 * Insere uma nova peca ao repositorio
	 *
	 * @param part	Objeto da peca a ser inserida
	 */
	public void addPart(Part part) throws RemoteException;

	/**
	 * Conta o numero de pecas contidas no repositorio
	 *
	 * @return Numero de pecas
	 */
	public int countParts() throws RemoteException;

	/**
	 * Busca uma peca do repositorio com base no id dela
	 *
	 * @param id	Identificador da peca a ser buscada 
	 * @return		Objeto da Part ou null, caso contrario
	 */
	public Part getPart(UUID id) throws RemoteException;

	/**
	 * Obtem o nome do repositorio
	 *
	 * @return Nome do repositorio
	 */
	public String getRepositoryName() throws RemoteException;

	/**
	 * Lista todas as pecas contidas no repositorio
	 *
	 * @return Lista contendo nome e identificador das pecas
	 */
	public ArrayList<String> listAllParts() throws RemoteException;
}