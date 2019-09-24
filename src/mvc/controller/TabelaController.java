package mvc.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.Tabela;
import mvc.model.TabelaDAO;


@Controller
public class TabelaController {

	@RequestMapping("adicionar")
	public String adicionar(Tabela tarefa,@ModelAttribute("data") String date, HttpSession session) throws ParseException {
		TabelaDAO dao = new TabelaDAO();
		
		Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		Calendar dataa = Calendar.getInstance();
		dataa.setTime(data);
		tarefa.setData(dataa);
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
		
		session.setAttribute("user", tarefa.getUser());
		session.setAttribute("data", dataEntrega);
		session.setAttribute("importancia", tarefa.getImportancia());
		session.setAttribute("tarefa", tarefa.getTarefa());
		session.setAttribute("categoria", tarefa.getCategoria());
		session.setAttribute("id", tarefa.getId());

		return "Editar";
	}
	
	@RequestMapping("editarreal")
	public String editaPost(Tabela tarefa,@ModelAttribute("data") String date,@ModelAttribute("user") String user, HttpSession session) throws ParseException {
		TabelaDAO dao = new TabelaDAO();
		Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		Calendar dataa = Calendar.getInstance();
		dataa.setTime(data);
		tarefa.setData(dataa);
		session.setAttribute("user", user);
		try {
			dao.editTarefa(tarefa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Cadastrado";
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
	public String ordernar(Tabela tarefa, HttpSession session, @ModelAttribute("filtro") Integer filtro, @ModelAttribute("user") String user) {
		
		Integer filtro2;
		if (filtro == 1) {
			filtro2 = 1;
		} else {
			filtro2 = 2;
		}
		session.setAttribute("filtro", filtro2);
		session.setAttribute("user", user);
		return "Cadastradrofiltrado";
	}

}
