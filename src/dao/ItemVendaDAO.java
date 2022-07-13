package dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import connection.ConnectionFactory;
import model.ItemVenda;

public class ItemVendaDAO {
	
	
	public static void salvarVenda(ItemVenda itemVenda) {
		String sql = "INSERT INTO itemVenda(idVenda, codLanche, quant, valor) VALUES (?, ?, ?, ?)";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		
		try {
			//cria uma conexão com o bd
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criando uma PreparedStatement para exec uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores esperados pela query
			pstm.setInt(1, itemVenda.getVenda().getId());
			pstm.setInt(2, itemVenda.getLanche().getCodLanche());
			pstm.setInt(3, itemVenda.getQuant());
			pstm.setDouble(4, itemVenda.getValor());
			
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
	
	
	//Find
	public static void findPrice(Integer codLanche) {
		String sql = "SELECT preco FROM lanche WHERE codLanche = codLanche";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
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
	
	//Read
}
