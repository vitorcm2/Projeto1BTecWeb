<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página Inicial</title>
</head>
<body>


	<%@ page import="java.util.*,mvc.model.TabelaDAO"%>
	<%@ page import="java.util.*,mvc.model.Tabela"%>
	<%
		TabelaDAO dao = new TabelaDAO();
		String usuario = (String) session.getAttribute("user");
		List<Tabela> pessoas = dao.getLista(usuario);
	%>
	<br>
	<table border='1'>
		<tr>

			<form method="post" action="adicionar">
				Tarefa: <input type="text" required='required' name="tarefa"> <a>&nbsp;
					&nbsp; &nbsp; &nbsp;</a> Data: <input type="date" required='required' name="data">
				<a>&nbsp; &nbsp; &nbsp; &nbsp;</a><input type="hidden"
					value="<%=usuario%>" name="user"> Importância: <select
					name="importancia">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select> <a>&nbsp; &nbsp; &nbsp; &nbsp;</a> Categoria: <select
					name="categoria">
					<option value="Insper">Insper</option>
					<option value="Trabalho">Trabalho</option>
					<option value="Pessoal">Pessoal</option>
				</select> <a>&nbsp; &nbsp; &nbsp; &nbsp;</a> <input type="submit"
					value="Adicionar"> <a>&nbsp; &nbsp; &nbsp; &nbsp;
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
				<a>&nbsp; &nbsp; &nbsp; &nbsp;</a> <input type="submit"
					value="Logout">
			</form>
		</tr>
		<br>
		<br>
		<tr>
			Ordenar por:
			<a>&nbsp; &nbsp;</a>
			<form method="post" action="filtrar">
				<input type="submit" value="Data" /><input type="hidden"
					value="<%=usuario%>" name="user"><input type="hidden" value=<%=1%>
					name="filtro" />
			</form>
			<a>&nbsp; &nbsp; </a>
			<form method="post" action="filtrar">
				<input type="submit" value="Importancia" /><input type="hidden"
					value="<%=usuario%>" name="user"><input type="hidden"
					value=<%=2%> name="filtro" />
			</form>
		</tr>
		<br>
		<br>
		<tr>
			<td>Tarefa</td>
			<td>Data de entrega</td>
			<td>Categoria</td>
			<td>Importância</td>
		</tr>

		<%
			for (Tabela pessoa : pessoas) {
		%>
		<tr>
			<td><%=pessoa.getTarefa()%></td>
			<td><fmt:formatDate value="<%=pessoa.getData().getTime()%>"
					pattern="dd-MM-yyyy" /></td>
			<td><%=pessoa.getCategoria()%></td>
			<td><%=pessoa.getImportancia()%></td>
			<td><form method="get" action="editar">
					<input type="hidden" name="user" value=<%=usuario%>>
					<input type="hidden" name="id" value=<%=pessoa.getId()%>> <input
						type="hidden" name="tarefa" value=<%=pessoa.getTarefa()%>>
					<input type="hidden" name="data"
						value=<fmt:formatDate value="<%=pessoa.getData().getTime()%>"
					pattern="yyyy-MM-dd" />>
					<input type="hidden" name="categoria"
						value=<%=pessoa.getCategoria()%>> <input type="hidden"
						name="importancia" value=<%=pessoa.getImportancia()%>> <input
						type="submit" value="Editar">
				</form></td>
			<td><form method="post" action="deletar">
					<input type="hidden" name="id" value=<%=pessoa.getId()%>> <input
						type="hidden" value="<%=usuario%>" name="user"> <input
						type="submit" value="Deletar">
				</form></td>
		</tr>
		<%
			}
		%>
	</table>

</body>

</body>
</html>
