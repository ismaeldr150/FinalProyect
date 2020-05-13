package com.illoismael.finalproyect.dao;

import com.illoismael.finalproyect.model.Coach;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Player;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;
import com.illoismael.finalproyect.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoachDAO extends Coach implements DAO{
    
    public static String SELECT_ALL = "SELECT * FROM player";
    public static String INSERT = "INSERT INTO player (id,name,age,phone,videogame,team,idEmpleado) VALUES (NULL,?,?,?,?,?,NULL)";
    public static String GETBYID = "SELECT * FROM player WHERE idEmpleado=?";
    public static String FINDBYID = "SELECT * FROM player WHERE idEmpleado IN";
    public static String FINDBYNAME = "SELECT * FROM player WHERE name LIKE ?";
    public static String UPDATE = "UPDATE player SET name = ?, age = ?, phone = ?, videogame = ?, team ? = WHERE idEmpleado = ?";
    public static String REMOVE = "DELETE FROM player WHERE idEmpleado=?";
    
    public static String select2 = "WHERE name LIKE ?";
    
    private boolean persist;

    public CoachDAO() {
    }

     public CoachDAO(Coach c) {
        this();
    }
    
    public CoachDAO(int id, String name, int age, String phone, Videogame videogame, Team team, int idEmpleado) {
        super(id, name, age, phone, videogame, team, idEmpleado);
    }
    
    
    public static List<Coach> selectAll() throws SQLException{
         return selectAll("");
     }
    
    public static List<Coach> selectAll(String patter) throws SQLException{
        List<Coach> result = new ArrayList<>();
        Connection c = new Connection("localhost", "idr", "root",""); 
        
        try {
            java.sql.Connection conn = ConnectionUtil.connect(c);
            String query = SELECT_ALL;

            if(patter.length() > 0){
                query+=select2;
            }
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            if(patter.length() > 0){
                ps.setString(1, patter+"%");
            }
            
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
            while(rs.next()){
                Coach c1 = new Coach();
                c1.setId(rs.getInt("id"));
                c1.setName(rs.getString("name"));
                c1.setAge(rs.getInt("age"));
                c1.setPhone(rs.getString("phone"));
              //  p.setVideogame(rs.getString("videogame"));
              //  p.setTeam(rs.getString("team"));
              result.add(c1);
            }
        }
            
            
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    

    @Override
    public void persist() {
        this.persist = true;
    }

    @Override
    public void detach() {
        this.persist = false;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
