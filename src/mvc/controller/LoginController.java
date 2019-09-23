package mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.Cadastro;
import mvc.model.LoginDAO;

@Controller
public class LoginController {
	@RequestMapping("/")
	public String execute() {
		return "login";
	}

	@RequestMapping("Login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("Cadastrar")
	public String cadastrar() {
		return "cadastrar";
	}

	@RequestMapping(value = "fazCadastro", method = RequestMethod.POST)
	public String upload(Cadastro usuario) throws IOException {
		LoginDAO dao = new LoginDAO();
		dao.adiciona(usuario);
		return "login";
	}

	@RequestMapping("fazLogin")
	public String efetuaLogin(Cadastro usuario, HttpSession session) {
		if (new LoginDAO().verifica(usuario)) {
			session.setAttribute("user", usuario.getUser());
			System.out.println("ENTROUU");
			return "Cadastrado";
		}
		return "NaoCadastrado";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}