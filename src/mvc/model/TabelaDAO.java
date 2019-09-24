package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mvc.model.Tabela;

public class TabelaDAO {
	private Connection connection = null;
	String url = System.getenv("mysql_url");
	String user = System.getenv("mysql_user");
	String password = System.getenv("mysql_password");

	public TabelaDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,user,password);
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/meusdados", "root", "Insper119960");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Tabela> getLista(String usuario) throws SQLException {

		List<Tabela> pessoas = new ArrayList<Tabela>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM crud WHERE user = ?");
		stmt.setString(1, usuario);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Tabela pessoa = new Tabela();
			pessoa.setId(rs.getInt("id"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			pessoa.setData(data);
			pessoa.setTarefa(rs.getString("tarefa"));
			pessoa.setImportancia(rs.getInt("importancia"));
			pessoa.setCategoria(rs.getString("categoria"));
			pessoas.add(pessoa);
		}
		rs.close();
		stmt.close();
		return pessoas;
	}

	public void setTarefa(Tabela tabela) throws SQLException {
		String sql = " INSERT INTO crud (user, tarefa, data,importancia,categoria)\r\n" + "VALUES (?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tabela.getUser());
		stmt.setString(2, tabela.getTarefa());
		stmt.setDate(3, new java.sql.Date(tabela.getData().getTimeInMillis()));
		stmt.setInt(4, tabela.getImportancia());
		stmt.setString(5, tabela.getCategoria());
		stmt.execute();
		stmt.close();
	}

	public void deleteTarefa(String id) throws SQLException {
		String sql = " DELETE FROM crud WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.execute();
		stmt.close();
	}

	public void editTarefa(Tabela tabela) throws SQLException {
		String sql = " UPDATE crud SET user=?,tarefa=?,data=?,importancia=?,categoria=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tabela.getUser());
		stmt.setString(2, tabela.getTarefa());
		stmt.setDate(3, new java.sql.Date(tabela.getData().getTimeInMillis()));
		stmt.setInt(4, tabela.getImportancia());
		stmt.setString(5, tabela.getCategoria());
		stmt.setInt(6, tabela.getId());

		stmt.execute();
		stmt.close();
	}

	public List<Tabela> doFiltro(String usuario, Integer filtro) throws SQLException {

		List<Tabela> pessoas = new ArrayList<Tabela>();
		if (filtro == 1) {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM crud WHERE user=? ORDER BY data");
			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tabela pessoa = new Tabela();
				pessoa.setId(rs.getInt("id"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				pessoa.setData(data);
				pessoa.setTarefa(rs.getString("tarefa"));
				pessoa.setImportancia(rs.getInt("importancia"));
				pessoa.setCategoria(rs.getString("categoria"));
				pessoas.add(pessoa);

			}
			rs.close();
			stmt.close();
		} else {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM crud WHERE user =? ORDER BY importancia");
			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Tabela pessoa = new Tabela();
				pessoa.setId(rs.getInt("id"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				pessoa.setData(data);
				pessoa.setTarefa(rs.getString("tarefa"));
				pessoa.setImportancia(rs.getInt("importancia"));
				pessoa.setCategoria(rs.getString("categoria"));
				pessoas.add(pessoa);

			}
			rs.close();
			stmt.close();
		}

		return pessoas;

	}

	public String getData(Tabela tarefa) {
		String sql = "SELECT data FROM crud WHERE id=?";
		PreparedStatement stmt;
		String data = null;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, tarefa.getId());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				data = rs.getString("data");
			}

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;

	}

}
