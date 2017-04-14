import java.io.Serializable;
import java.util.UUID;

/**
 * @author Leonardo Rocha <leonardo.lsrocha@gmail.com>
 */
public class Part implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2687902096905291987L;

	/**
	 * Codigo unico de identificacao da peca
	 */
	protected final UUID code;

	/**
	 * Nome da peca
	 */
	protected final String name;

	/**
	 * Descricao da peca
	 */
	protected final String description;

	/**
	 * A peca eh primitiva? Caso nao seja, significa que a peca eh
	 * uma agregacao de outras pecas primitivas 
	 */
	protected boolean isPrimitive;

	/**
	 * Nome do repositorio em que a peca esta inserida
	 */
	private String repositoryName;

	/**
	 * Cria uma nova peca primitiva
	 * 
	 * @param name			Nome da peca
	 * @param description	Descricao da peca
	 * @param repository	Nome do repositorio
	 */
	public Part(String name, String description, String repository) {
		this.name = name;
		this.description = description;
		this.isPrimitive = true;
		this.code = UUID.randomUUID();
		this.repositoryName = repository;
	}

	/**
	 * Obtem o codigo unico de identificacao da peca
	 *
	 * @return UUID da peca
	 */
	public UUID getCode() {
		return this.code;
	}

	/**
	 * Obtem a descricao da peca
	 *
	 * @return Texto de descricao da peca
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Obtem nome da peca
	 *
	 * @return Nome da peca
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Obtem o nome do repositorio em que a peca esta inserida
	 *
	 * @return Nome do repositorio
	 */
	public String getRepository() {
		return this.repositoryName;
	}

	/**
	 * A peca eh primitiva?
	 *
	 * @return True/false 
	 */
	public boolean isPrimitive() {
		return this.isPrimitive;
	}
}