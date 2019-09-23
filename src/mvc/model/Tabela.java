package mvc.model;
import java.util.Calendar;

public class Tabela {

	private Integer id;
	private String user;
	private String tarefa;
	private Calendar data;
	private int importancia;
	private String categoria;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTarefa() {
		return this.tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public Calendar getData() {
		return this.data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getImportancia() {
		return this.importancia;
	}

	public void setImportancia(int importancia) {
		this.importancia = importancia;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
