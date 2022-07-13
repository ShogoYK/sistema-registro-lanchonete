package dao;

import java.sql.Connection;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import connection.ConnectionFactory;
import model.ItemCompra;

public class ItemCompraDAO {
	public static void salvarItem(ItemCompra itemCompra) {
		String sql = "INSERT INTO itemCompra(idCompra, codIngrediente, quant) VALUES (?, ?, ?)";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		
		try {
			//cria uma conexão com o bd
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criando uma PreparedStatement para exec uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores esperados pela query
			pstm.setInt(1, itemCompra.getCompra().getIdCompra());
			pstm.setInt(2, itemCompra.getIngrediente().getCodIngrediente());
			pstm.setInt(3, itemCompra.getQuant());
			
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
}
