/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcc.software;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pradeep
 */
public class DatabaseUtil {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String getDatabaseName() {
        Connection connection = null;
        List existDBList = DatabaseUtil.getExistDatabase();
        String databaseName = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String qry = "SELECT datname FROM pg_database";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);
            while (resultSet.next()) {
                String dbName = resultSet.getString("datname");
                if (existDBList.contains((Object)dbName)) continue;
                databaseName = dbName;
                break;
            }
        }
        catch (Exception e) {
            DatabaseUtil.closeConnection(connection, statement, resultSet);
            return databaseName;
            }
            catch (Throwable throwable) {
                DatabaseUtil.closeConnection(connection, statement, resultSet);
                throw throwable;
            }
       
        DatabaseUtil.closeConnection(connection, statement, resultSet);
        return databaseName;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */


    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean dropDatabase(String dbname) {
        boolean flag;
        flag = true;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection(dbname);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            List tableList = DatabaseUtil.getAllTables(dbname);
            List existTables = DatabaseUtil.getSystemTablesandViews();
            for (int tableidx = 0; tableidx < tableList.size(); ++tableidx) {
                String tableName = (String)tableList.get(tableidx);
                String droptableqry = "DROP TABLE IF EXISTS " + tableName + " CASCADE";
                if (existTables.contains((Object)tableName)) continue;
                try {
                    statement.executeUpdate(droptableqry);
                    continue;
                }
                catch (Exception e) {
                    System.out.println("Exception in " + droptableqry);
                    try {
                        droptableqry = "DROP VIEW IF EXISTS " + tableName + " CASCADE";
                        statement.executeUpdate(droptableqry);
                        continue;
                    }
                    catch (Exception e1) {
                        System.out.println("Exception in " + droptableqry);
                    }
                }
            }
            connection.commit();
        }
        catch (Exception e) {
            try {
                try {
                    connection.rollback();
                }
                catch (SQLException ex) {
                    // empty catch block
                }
                flag = false;
            }
            catch (Throwable throwable) {
                DatabaseUtil.closeConnection(connection, statement, resultSet);
                throw throwable;
            }
            DatabaseUtil.closeConnection(connection, statement, resultSet);
        }
        DatabaseUtil.closeConnection(connection, statement, resultSet);
        return flag;
    }

    public static List getSystemTablesandViews() {
        ArrayList list = new ArrayList();
        list.add((Object)"pg_buffercache");
        list.add((Object)"pg_freespacemap_pages");
        list.add((Object)"pg_freespacemap_relations");
        list.add((Object)"pg_ts_cfg");
        list.add((Object)"pg_ts_cfgmap");
        list.add((Object)"pg_ts_dict");
        list.add((Object)"pg_ts_parser");
        return list;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean createDatabase(String dbname) {
        boolean flag;
        flag = true;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String qry = "create database " + dbname;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(qry);
        }
        catch (Exception e) {
            flag = false;
        }
        finally {
            DatabaseUtil.closeConnection(connection, statement, resultSet);
        }
        return flag;
    }

    public static List getExistDatabase() {
        ArrayList existDBList = new ArrayList();
        existDBList.add((Object)"postgres");
        existDBList.add((Object)"template_postgis");
        existDBList.add((Object)"demo");
        existDBList.add((Object)"template1");
        existDBList.add((Object)"template0");
        return existDBList;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static List getAllTables(String dbname) {
        LinkedList list;
        list = new LinkedList();
        String qry = "SELECT table_name FROM information_schema.tables where table_schema='public'";
        Statement statement = null;
        Connection connection = DatabaseConnection.getInstance().getConnection(dbname);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(qry);
            while (resultSet.next()) {
                String tablename = resultSet.getString("table_name");
                list.add((Object)tablename);
            }
        }
        catch (Exception e) {
        }
        finally {
            DatabaseUtil.closeConnection(connection, statement, null);
        }
        return list;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int getDBCount(Connection connection) throws Exception {
        List existDBList = DatabaseUtil.getExistDatabase();
        int noofdatabase = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        String qry = "SELECT datname FROM pg_database";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);
            while (resultSet.next()) {
                String databaseName = resultSet.getString("datname");
                if (existDBList.contains((Object)databaseName)) continue;
                ++noofdatabase;
            }
        }
        catch (Throwable throwable) {
            DatabaseUtil.closeConnection(null, statement, resultSet);
            throw throwable;
        }
        DatabaseUtil.closeConnection(null, statement, resultSet);
        return noofdatabase;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String getPathOfSql() {
        String configDir;
        ResultSet resultSet;
        Statement statement;
        Connection conn;
        block7 : {
            conn = null;
            statement = null;
            resultSet = null;
            String filePath = "";
            configDir = "";
            File file = null;
            String query = "select setting from pg_settings where name = 'data_directory';";
            try {
                conn = DatabaseConnection.getInstance().getConnection();
                statement = conn.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    filePath = resultSet.getString("setting");
                }
                file = new File(filePath);
                configDir = file.getParent();
                if (file == null) break block7;
                file = null;
            }
            catch (Exception ex) {
                if (file != null) {
                    file = null;
                }
                DatabaseUtil.closeConnection(conn, statement, resultSet);
                return configDir;
            }
                catch (Throwable throwable) {
                    if (file != null) {
                        file = null;
                    }
                    DatabaseUtil.closeConnection(conn, statement, resultSet);
                    throw throwable;
                }
            
        }
        DatabaseUtil.closeConnection(conn, statement, resultSet);
        return configDir;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Boolean isDBNotExist(String dbname) {
        ResultSet resultSet;
        Boolean bl;
        Statement statement;
        Connection conn;
        block6 : {
            Object name;
            String query = "select datname from pg_catalog.pg_database;";
            conn = null;
            statement = null;
            resultSet = null;
            try {
                conn = DatabaseConnection.getInstance().getConnection();
                statement = conn.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    name = resultSet.getString("datname");
                    if (!dbname.equalsIgnoreCase((String) name)) 
                     continue;
                    bl = false;
                    break block6;
                }
                name = true;
            }
            catch (Exception ex) {
                DatabaseUtil.closeConnection(conn, statement, resultSet);
                return false;
            }
                catch (Throwable throwable) {
                    DatabaseUtil.closeConnection(conn, statement, resultSet);
                    throw throwable;
                }
            
            DatabaseUtil.closeConnection(conn, statement, resultSet);
            return (Boolean) name;
        }
        DatabaseUtil.closeConnection(conn, statement, resultSet);
        return bl;
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
