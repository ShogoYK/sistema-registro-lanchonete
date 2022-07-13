package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	//Nome do usuário do mysql
	private static final String USERNAME = "root";
	
	//Senha
	private static final String PASSWORD = "";
	
	//Caminho do bd, porta, nome do bd
	private static final String DATABASE_URL = //Add path"";
	
	/*
	 * Conexão com o bd
	 */
	
	public static Connection createConnectionToMySQL() throws Exception{
		
		//Faz com q a classe seja carregada pela jvm
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Cria conexão com o bd
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
		
	}
	
	public static void main(String[] args) throws Exception{
		
		//Recuperar conexão com o bd
		Connection con = createConnectionToMySQL();
		
		//Teste if null
		if(con!=null) {
			System.out.println("Conexão obtida com sucesso!");
			con.close();
		}
		
	}
	
}
