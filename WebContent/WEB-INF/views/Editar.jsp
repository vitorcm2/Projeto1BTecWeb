<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.*,mvc.model.TabelaDAO"%>
	<%@ page import="java.util.*,mvc.model.Tabela"%>
	<%@ page import="java.util.*, java.text.SimpleDateFormat"%>
	<%
		TabelaDAO dao = new TabelaDAO();
		String usuario = (String) session.getAttribute("user");
		String tarefa = (String) session.getAttribute("tarefa");
		String data;
		Calendar data2 = (Calendar) session.getAttribute("data");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setTimeZone(data2.getTimeZone());
		data = dateFormat.format(data2.getTime());
		
		Integer id = (Integer) session.getAttribute("id");
		Integer importancia = (Integer) request.getAttribute("importancia");
		String categoria = (String) request.getAttribute("categoria");
		List<Tabela> pessoas = dao.getLista(usuario);
	%>
	<br>
	
	<table >
		<tr>

			<form method="post" action="editarreal">

				Tarefa: <input type="text" name="tarefa" value="<%=tarefa%>">
				<a>&nbsp; &nbsp; &nbsp; &nbsp;</a> Data: <input type="date"
					name="data" value="<%=data%>"> <a>&nbsp; &nbsp; &nbsp;
					&nbsp;</a><input type="hidden" value="<%=usuario%>" name="user">
					<input type="hidden" value="<%=id%>" name="id">
				Importância: <select name="importancia">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select> <a>&nbsp; &nbsp; &nbsp; &nbsp;</a> Categoria: <select
					name="categoria">
					<option value="Insper">Insper</option>
					<option value="Trabalaho">Trabalho</option>
					<option value="Pessoal">Pessoal</option>
				</select> <a>&nbsp; &nbsp; &nbsp; &nbsp;</a> <input type="submit"
					value="Editar"> <a>&nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</a>
			</form>
			<form method="post" action="logout">
				Usuário:
				<%=usuario%>
				<%System.out.println(usuario); %>
				<a>&nbsp; &nbsp; &nbsp; &nbsp;</a> <input type="submit"
					value="Logout">
			</form>
		</tr>
	</table>
</body>
</html>