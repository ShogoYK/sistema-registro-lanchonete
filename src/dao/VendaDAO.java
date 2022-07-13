package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import connection.ConnectionFactory;
import model.Venda;

public class VendaDAO {

	//Create
	public static void save(Venda venda) {
		String sql = "INSERT INTO venda(dataVenda, valortotal, formapagamento) VALUES (?, ?, ?)";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		
		try {
			//cria uma conexão com o bd
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criando uma PreparedStatement para exec uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//Adicionar os valores esperados pela query
			pstm.setDate(1, venda.getDataVenda());
			pstm.setDouble(2, venda.getValorTotal());
			pstm.setString(3, venda.getFormaPagamento());
			
			//Executar a query
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			rs.next();
			int idVenda = rs.getInt(1);
			System.out.println(idVenda);
			
			venda.setId(idVenda);
					
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
	public static void findPrice(Integer codLanche) {
		String sql = "SELECT * FROM lanche WHERE codLanche = "+codLanche;
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
		String sql = "DELETE FROM venda where id = ?";
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
