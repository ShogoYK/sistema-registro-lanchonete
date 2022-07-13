package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import connection.ConnectionFactory;
import model.Compra;

public class CompraDAO {

	//Create
	public static void save(Compra compra) {
		String sql = "INSERT INTO compra(dataCompra, valorTotal) VALUES (?, ?)";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		
		try {
			//cria uma conexão com o bd
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criando uma PreparedStatement para exec uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//Adicionar os valores esperados pela query
			
			pstm.setDate(1, compra.getDataCompra());
			pstm.setDouble(2, compra.getValorTotal());
			
			//Executar a query
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			rs.next();
			int idCompra = rs.getInt(1);
			System.out.println(idCompra);
			
			compra.setIdCompra(idCompra);
					
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
	public static void findPrice(Integer codIngrediente) {
		String sql = "SELECT * FROM ingrediente WHERE codIngrediente = "+codIngrediente;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
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
	}
	
	
	
	//Delete
	public static void deleteById(Integer id) {
		String sql = "DELETE FROM compra where id = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1,id);
			
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
}
