import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableWithoutMaven {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/maven_dummy","root","root1234");
        Statement statement = connection.createStatement();

        statement.execute("create table dummy_table (id int, name varchar(50))");
    }
}
