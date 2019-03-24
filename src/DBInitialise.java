import java.sql.*;

public class DBInitialise {
    // db parameters
    String url       = "jdbc:mysql://localhost:3306/world";
    String user      = "root";
    String password  = "root";
    Connection conn = null;
    /**
     * Database meta data
     */
    DatabaseMetaData dbData;

    public DBInitialise(){
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successful DB connection");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads meta data of the connected database
     * used for reading table names etc.
     */
    private void getMetaData(){
        try {
            dbData = conn.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final void open(){
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successful DB connection");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Close database
     * <p>
     * Commits any remaining updates to database and
     * closes connection
     */
    public final void close() {
        try {
            conn.close();
            System.out.println("Successfully closed DB connection");
        } catch (Exception e) {
            notify("Db.close", e);
        }
    }

    /**
     * Outputs a stacktrace for debugging and exits
     * <p>
     * To be called following an {@link java.lang.Exception}
     *
     * @param message	informational String to display
     * @param e		the Exception
     */
    public void notify(String message, Exception e) {
        System.out.println( message + " : " + e );
        e.printStackTrace ( );
        System.exit( 0 );
    }

    /**
     * returns the set of tables in the database
     * @return - Returns the set of tables
     */
    public ResultSet getTables(){
        try {
            ResultSet tables = dbData.getTables(null, null, null, new String[]{"TABLE"});
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns the set of columns in the supplied table
     * @param tableName - name of the table to grab columns from
     * @return - Returns the set of columns
     */
    public ResultSet getColumns(String tableName){
        try {
            ResultSet columns = dbData.getColumns(null,null, tableName, null);
            return columns;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns the set of primary keys in the supplied table
     * @param tableName - name of the table to grab primary keys from
     * @return - Returns the set of primary keys
     */
    public ResultSet getPkey(String tableName){
        try {
            ResultSet pKey = dbData.getPrimaryKeys(null,null, tableName);
            return pKey;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * returns the set of Foreign keys in the supplied table
     * @param tableName - name of the table to grab Foreign keys from
     * @return - Returns the set of Foreign keys
     */
    public ResultSet getFkey(String tableName){
        try {
            ResultSet fKey = dbData.getImportedKeys(null, null, tableName);
            return fKey;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Processes supplied query and returns result
     * @param query - name of the sql query to execute
     * @return - Returns the result of the query
     */
    public ResultSet processQuery(String query){
        try{
            return conn.createStatement().executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
