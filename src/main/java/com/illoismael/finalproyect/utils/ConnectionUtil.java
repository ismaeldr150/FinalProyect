/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.illoismael.finalproyect.utils;

import com.illoismael.finalproyect.model.Connection;
import java.sql.Array;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author srism
 */
public class ConnectionUtil {
    
    private static java.sql.Connection  _conn = null;
    
    public static java.sql.Connection connect(Connection c) throws ClassNotFoundException, SQLException{
        java.sql.Connection conn = null;
        
        if (c == null){
            return null;
        }
        Class.forName("com.mysql.jdbc.Driver");
        conn=DriverManager.getConnection("jdbc:mysql://" + c.getServer() + "/agenda?useLegacyDatetimeCode=false&serverTimezone=UTC", c.getUserName(), c.getPassword()); 
        
        return conn;
    }
    
    public static java.sql.Connection getConnection(){
        if(_conn == null){
            Connection c = new Connection();
            c.loadDataXML();
            try {
                _conn = connect(c);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _conn;
    }
    
    
    /**
     * 
     * @param con le pasamos una conexion
     * @param q un String
     * @param params una lista de parámetros
     * @return devolvemos un PreparedStatement
     * @throws SQLException 
     */
    public static PreparedStatement prepareQuery(java.sql.Connection con, String q, List params) throws SQLException {
        PreparedStatement ps = null;
        ps = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS); 
        if (params != null) {
            int i = 1;
            for (Object o : params) {
                switch (is(params)) {
                    case 0:
                        ps.setInt(i++, (Integer) o);
                        break;
                    case 1:
                        ps.setFloat(i++, (Float) o);
                        break;
                    case 2:
                        ps.setDouble(i++, (Double) o);
                        break;
                    case 3:
                        ps.setBoolean(i++, (Boolean) o);
                        break;
                    case 4:
                        ps.setString(i++, (String) o);
                        break;
                    case 5:
                        ps.setArray(i++, (Array) o);
                        break;
                    default:
                        ps.setObject(i++, o);
                }
            }
        }
        return ps;
    }
    
    public static int is(Integer n) {
        return 0;
    }
    public static int is(Float n) {
        return 1;
    }
    public static int is(Double n) {
        return 2;
    }
    public static int is(Boolean n) {
        return 3;
    }
    public static int is(String n) {
        return 4;
    }   
    public static int is(Array n){
        return 5;
    }
    public static int is(Object n) {
        return 6;
    }

    
    
    public static ResultSet execQuery(java.sql.Connection con, String q, List<Object> params) throws SQLException {
        ResultSet result = null;
        if (con == null) {
            return null;
        }

        PreparedStatement ps = prepareQuery(con, q, params);
        result = ps.executeQuery();

        return result;
    }
    
    public static ResultSet execQuery(java.sql.Connection con, String q, Object param) throws SQLException {
        List<Object> params = new ArrayList<>();
        params.add(param);
        return execQuery(con, q, params);
    }
    
    
    public static int execUpdate(java.sql.Connection con, String q, List<Object> params, boolean insert) throws SQLException {
        if (con == null) {
            return -1;
        }
        

        PreparedStatement ps = prepareQuery(con, q, params);
        int result = ps.executeUpdate();
        
        if (insert) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);  
                } else {
                    return -1;
                }
            }
        } else {
            return result;
        }

    }
    
    public static int execUpdate(java.sql.Connection con, String q, Object param, boolean insert) throws SQLException {
        List<Object> params = new ArrayList<>();
        params.add(param);
        return execUpdate(con, q, params, insert);
    }
    
}
