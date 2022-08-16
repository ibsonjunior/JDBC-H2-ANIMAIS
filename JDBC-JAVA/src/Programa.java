import java.sql.*;

public class Programa {

    //Antes do método main, criaremos as instruções sql para executar no bloco try/catch.

    private static final String sqlCreateTable = "DROP TABLE IF EXISTS animal; CREATE TABLE ANIMAL (id INT PRIMARY KEY, nome VARCHAR(64), tipo VARCHAR(32))";

    private static final String sqlInsert1 = "INSERT INTO animal (id, nome, tipo) VALUES (1, 'Meg', 'Cachorro')";

    private static final String sqlInsert2 = "INSERT INTO animal (id, nome, tipo) VALUES (2, 'Mingau', 'Gato')";

    private static final String sqlInsert3 = "INSERT INTO animal (id, nome, tipo) VALUES (3, 'Dumbo', 'Elefante')";

    private static final String sqlInsert4 = "INSERT INTO animal (id, nome, tipo) VALUES (4, 'Pup', 'Cachorro')";

    private static final String sqlInsert5 = "INSERT INTO animal (id, nome, tipo) VALUES (5, 'Pé de pano', 'Cavalo')";

    private static final String sqlADelete1 = "DELETE FROM animal WHERE id=2";




    public static void main(String[] args) throws SQLException {
        //Evita juntar arquivis na memória

        Connection connection = null;

       try{
           connection = conectarBD();
           //System.out.println("Conectado ao Banco de Dados");

            // Statement Prepara o arquivo para fazer a tabela e demais comandos do sql
           Statement preparacao = conectarBD().createStatement();
           preparacao.execute(sqlCreateTable);
           preparacao.execute(sqlInsert1);
           preparacao.execute(sqlInsert2);
           preparacao.execute(sqlInsert3);
           preparacao.execute(sqlInsert4);
           preparacao.execute(sqlInsert5);
           listarTodos(connection);
           preparacao.execute(sqlADelete1);
           listarTodos(connection);
       }catch (Exception e){
            e.printStackTrace();
       } finally {
           System.out.println("Conexão encerrada...");
           connection.close();
       }

    }

    //Aqui criamos um método para conectar com o banco de dados
    public static Connection conectarBD() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/jdbc-BancoAnimal", "sa", "");
    }

    //Método select

    public static void listarTodos(Connection connection) throws SQLException {

        String sqlQuery = "SELECT * FROM animal;";

        Statement preparaQuery = connection.createStatement();

        ResultSet rs = preparaQuery.executeQuery(sqlQuery);


        while (rs.next()){
            System.out.println("ID: " + rs.getInt(1) + " - " + "Nome: " + rs.getString(2) + "Tipo: " + rs.getString(3));
        }

    }
}
