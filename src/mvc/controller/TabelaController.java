package mvc.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.Tabela;
import mvc.model.TabelaDAO;

public class TabelaController {

	@RequestMapping("adicionar")
	public String adicionar(Tabela tarefa) {
		TabelaDAO dao = new TabelaDAO();
		try {
			dao.setTarefa(tarefa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Cadastrado";
	}

	@RequestMapping("editar")
	public String editaGet(Tabela tarefa, HttpSession session) throws ParseException {
		session.setAttribute("name", tarefa.getUser());
		String date = new TabelaDAO().getData(tarefa);
		Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		Calendar dataEntrega = Calendar.getInstance();
		dataEntrega.setTime(data);
		session.setAttribute("data", dataEntrega);
		session.setAttribute("importancia", tarefa.getImportancia());
		session.setAttribute("tarefa", tarefa.getTarefa());
		session.setAttribute("categoria", tarefa.getCategoria());
		session.setAttribute("id", tarefa.getId());

		return "Editar";
	}

	@RequestMapping("deletar")
	public String remove(Tabela tarefa) {
		TabelaDAO dao = new TabelaDAO();
		try {
			dao.deleteTarefa(String.valueOf(tarefa.getId()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Cadastrado";
	}

	@RequestMapping("filtrar")
	public String ordernar(Tabela tarefa, HttpSession session, @ModelAttribute("variable") String variable) {
		String filtro = null;
		if (variable.contentEquals("importancia")) {
			filtro = "importancia";
		} else {
			filtro = "categoria";
		}
		session.setAttribute("filtro", filtro);
		return "Cadastrado";
	}

}
