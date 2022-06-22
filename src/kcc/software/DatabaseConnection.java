/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcc.software;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

/**
 *
 * @author pradeep
 */
public class DatabaseConnection {
    private static DatabaseConnection databaseConnection;

    DatabaseConnection() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static DatabaseConnection getInstance() {
        Class<DatabaseConnection> class_ = DatabaseConnection.class;
        synchronized (DatabaseConnection.class) {
            DatabaseConnection inst = databaseConnection;
            if (inst != null) return databaseConnection;
            Class<DatabaseConnection> class_2 = DatabaseConnection.class;
            synchronized (DatabaseConnection.class) {
                databaseConnection = new DatabaseConnection();
                // ** MonitorExit[var2_2] (shouldn't be in output)
                // ** MonitorExit[var0] (shouldn't be in output)
                return databaseConnection;
            }
        }
    }

    public synchronized Connection getConnection() {
        System.out.println("get into connection");
        Map configMap = PropertiesCache.getInstance().getConfigMap();
        Connection conn = null;
        if (configMap != null) {
            try {
                String dbpwd = "";
                String dbusr = "";
                if (configMap.get((Object)"dbusr") != null) {
                    dbusr = configMap.get((Object)"dbusr").toString();
                    System.out.println(dbusr);
                }
                if (configMap.get((Object)"dbpswd") != null) {
                    dbpwd = configMap.get((Object)"dbpswd").toString();
                    System.out.println(dbpwd);
                }
                Class.forName((String)"org.postgresql.Driver").newInstance();
                String url = "jdbc:postgresql://localhost:5432/postgres?user=" + dbusr + "&password=" + dbpwd;
                System.out.println(url);
                conn = DriverManager.getConnection((String)url);
               
            }
            catch (Exception ex) {
                // empty catch block
            }
        }
        return conn;
    }

    public synchronized Connection getConnection(String dbName) {
        Map configMap = PropertiesCache.getInstance().getConfigMap();
        Connection conn = null;
        if (configMap != null) {
            try {
                String dbpwd = "";
                String dbusr = "";
                if (configMap.get((Object)"dbusr") != null) {
                    dbusr = configMap.get((Object)"dbusr").toString();
                }
                if (configMap.get((Object)"dbpswd") != null) {
                    dbpwd = configMap.get((Object)"dbpswd").toString();
                    
                }
                Class.forName((String)"org.postgresql.Driver").newInstance();
                String url = "jdbc:postgresql://localhost:5432/" + dbName + "?user=" + dbusr + "&password=" + dbpwd;
                conn = DriverManager.getConnection((String)url);
            }
            catch (Exception ex) {
                // empty catch block
            }
        }
        return conn;
    }
}