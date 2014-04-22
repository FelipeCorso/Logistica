import java.sql.Connection;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = Conexao.getConnection();
		System.out.println(Conexao.status);
	}

}
