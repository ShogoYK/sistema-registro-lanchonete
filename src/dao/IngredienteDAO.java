package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import connection.ConnectionFactory;
import model.Ingrediente;

public class IngredienteDAO {
	public static void save(Ingrediente ingrediente) {
		String sql = "INSERT INTO ingrediente(nomeIngrediente) VALUES (?)";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			//cria uma conexão com o bd
			conn = ConnectionFactory.createConnectionToMySQL();

			//Criando uma PreparedStatement para exec uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

			//Adicionar os valores esperados pela query
			pstm.setString(1, ingrediente.getNomeIngrediente());

			//Executar a query
			pstm.execute();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
		}try {
			if(pstm!=null) {
				pstm.closeQuery();				
				if(conn!=null) {
					conn.close();
				}	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Read
	public static Ingrediente read(Integer codIngrediente) {
		String sql = "SELECT * FROM ingrediente WHERE codIngrediente = " + codIngrediente;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				Ingrediente ingrediente = new Ingrediente();
				ingrediente.setCodIngrediente(rs.getInt(1));
				return ingrediente;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
		}try {
			if(pstm!=null) {
				pstm.closeQuery();				
				if(conn!=null) {
					conn.close();
				}	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Ingrediente> readAll() {
		String sql = "SELECT * FROM ingrediente";
		
		ArrayList<Ingrediente> lista = new ArrayList<>();
		
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Ingrediente ingrediente = new Ingrediente();
				ingrediente.setCodIngrediente(rs.getInt(1));
				ingrediente.setNomeIngrediente(rs.getString(2));
				lista.add(ingrediente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar as conexões
		}try {
			if(pstm!=null) {
				pstm.closeQuery();				
				if(conn!=null) {
					conn.close();
				}	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
