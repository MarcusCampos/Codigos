
import java.util.HashMap;

/**
 * @author Leonardo Rocha <leonardo.lsrocha@gmail.com>
 */
public class AggregatedPart extends Part {

	/**
	 * 
	 */
	private static final long serialVersionUID = -333822058697911636L;

	/**
	 * Mapa armazenando a quantidade de cada peca primitivas que compoem
	 * a peca agregada
	 */
	private HashMap<Part, Integer> subcomponents;

	/**
	 * Cria uma nova peca agregada, composta por subcomponentes primitivos
	 *
	 * @param name			Nome da peca
	 * @param description	Descricao da peca
	 * @param repository	Nome do repositorio
	 */
	public AggregatedPart(String name, String description, String repository) {
		super(name, description, repository);
		this.isPrimitive = false;
		this.subcomponents = new HashMap<Part, Integer>();
	}

	/**
	 * Adiciona uma nova subpeca
	 *
	 * @param part		Peca primitiva
	 * @param amount	Quantidade encontrada na peca agregada
	 */
	public void addSubcomponent(Part part, int amount) {
		if (this.subcomponents == null) return;
		this.subcomponents.put(part, amount);
	}

	/**
	 * 
	 * @return
	 */
	public int countSubcomponents() {
		return this.subcomponents.size();
	}

	/**
	 * Obtem os subcomponentes que compoem a peca agregada
	 *
	 * @return Mapa de subcomponentes
	 */
	public HashMap<Part, Integer> getSubcomponents() {
		return this.subcomponents;
	}
}