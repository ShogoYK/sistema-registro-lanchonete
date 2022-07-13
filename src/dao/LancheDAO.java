package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.jdbc.JdbcPreparedStatement;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import connection.ConnectionFactory;
import model.Lanche;

public class LancheDAO {
	public static void save(Lanche lanche) {
		String sql = "INSERT INTO lanche(nomeLanche, preco) VALUES (?,?)";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			//cria uma conexão com o bd
			conn = ConnectionFactory.createConnectionToMySQL();

			//Criando uma PreparedStatement para exec uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

			//Adicionar os valores esperados pela query
			pstm.setString(1, lanche.getNomeLanche());
			pstm.setDouble(2, lanche.getPreco());

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
	public static Lanche read(Integer codLanche) {
		String sql = "SELECT * FROM lanche WHERE codLanche = " + codLanche;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				Lanche lanche = new Lanche();
				lanche.setCodLanche(rs.getInt(1));
				lanche.setNomeLanche(rs.getString(2));
				lanche.setPreco(rs.getDouble(3));
				return lanche;
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
	
	public static ArrayList<Lanche> readAll() {
		String sql = "SELECT * FROM lanche";
		
		ArrayList<Lanche> lista = new ArrayList<>();
		
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Lanche lanche = new Lanche();
				lanche.setCodLanche(rs.getInt(1));
				lanche.setNomeLanche(rs.getString(2));
				lanche.setPreco(rs.getDouble(3));
				lista.add(lanche);
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
