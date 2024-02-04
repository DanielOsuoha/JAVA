import java.sql.*;
import java.util.Collections;
import java.sql.*;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

    public class DBMS {
        //add a constructor for loading the driver
        //have methods for CRUD operations
        //also for definitions
        private Connection connection = null;
        private Statement st = null;
        public static final String USER = "root";
        public static final String PASSWORD = "";
        public DBMS(String databaseUrl) throws SQLException {
            this.connection = DriverManager.getConnection(databaseUrl,USER,PASSWORD);

        }
        public void createTable(String tableName, String[] columns) throws SQLException{
            st = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS "+ tableName +"(" +String.join(",",columns)+")";
            st.executeUpdate(sql);
            System.out.println("Table "+ tableName+" has been created!!!");
        }
        public void delete(String tableName, String whereClause) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE " + whereClause);
            statement.executeUpdate();
        }

        public void deleteTable(String tableName) throws SQLException{
            if (doesTableExist(connection, tableName)) {
                st = connection.createStatement();
                String sql = "DROP TABLE "+tableName+";";
                st.executeUpdate(sql);
                System.out.println("Table "+tableName+" has been deleted!!!");
            } else {
                System.out.println("The table "+tableName+" does not exist!!");
            }
        }
        public void insert(String tableName, String[] columns, Object[] values) throws SQLException {
            if (columns.length != values.length) {
                throw new IllegalArgumentException("Columns count doesn't match values count");
            }
            String placeholders = String.join(",", Collections.nCopies(values.length, "?"));

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + tableName + " (" + String.join(",", columns) + ") " +
                            "VALUES (" + placeholders + ")"
            );
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
        }

        public void update(String tableName, String[] columns, Object[] values, String whereClause) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + tableName + " SET " + String.join(",", columns) + " = (" + String.join(",", "?") + ") WHERE " + whereClause);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
        }

        private boolean doesTableExist(Connection connection, String tableName) throws SQLException {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet result = meta.getTables(null,null,tableName,null);
            return result.next();
        }
        public ResultSet select(String tableName, String[] columns) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("SELECT " + String.join(",", columns) + " FROM " + tableName);
            return statement.executeQuery();
        }
        public ResultSet selectWhereClause(String tableName, String[] columns, String whereClause) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("SELECT " + String.join(",", columns) + " FROM " + tableName + " WHERE " + whereClause);
            return statement.executeQuery();
        }
        public void close() throws SQLException {
            connection.close();
        }
}
