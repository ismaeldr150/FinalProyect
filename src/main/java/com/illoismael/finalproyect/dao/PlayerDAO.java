package com.illoismael.finalproyect.dao;

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

public class PlayerDAO extends Player implements IDAO{
    
    
    
    public static String SELECT_ALL = "SELECT * FROM person";
    public static String INSERT = "INSERT INTO person (id,name,age,phone,videogame,team) VALUES (NULL,?,?,?,?,?,NULL)";
    public static String GETBYID = "SELECT * FROM person WHERE idEmpleado=?";
    public static String FINDBYID = "SELECT * FROM person WHERE id IN";
    public static String FINDBYNAME = "SELECT * FROM person WHERE name LIKE ?";
    public static String UPDATE = "UPDATE person SET name = ?, age = ?, phone = ?, videogame = ?, team ? = WHERE id = ?";
    public static String REMOVE = "DELETE FROM person WHERE id=?";
    
    public static String select2 = "WHERE name LIKE ?";
    
    java.sql.Connection con;
    private boolean persist;
   
    /*
    enum queries {
        INSERT("INSERT INTO player (id,name,age,phone,videogame,team,idEmpleado) VALUES (NULL,?,?,?,?,?,NULL)"),
        ALL("SELECT * FROM player"),
        GETBYID("SELECT * FROM player WHERE idEmpleado=?"),
        FINDBYID("SELECT * FROM player WHERE idEmpleado IN "), 
        FINDBYNAME("SELECT * FROM player WHERE name LIKE ?"),
        UPDATE("UPDATE player SET name = ?, age = ?, phone = ?, videogame = ?, team ? = WHERE idEmpleado = ?"),
        REMOVE("DELETE FROM player WHERE idEmpleado=?");
        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
*/

    public PlayerDAO() {
    }
    public PlayerDAO(Player p) {
    }
    

    public PlayerDAO(int id, String name, int age, int salary) {
        super(id, name, age, salary);
    }

    
    
    
    
     public static List<Player> selectAll(){
         return selectAll("");
     }
    
    public static List<Player> selectAll(String patter){
        List<Player> result = new ArrayList<>();
        Connection c = new Connection("localhost", "idr", "root",""); //  <-- Cambiar por XML (a fuego no)
        
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
                Player p = new Player();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                result.add(p);
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
        
        
    }

    @Override
    public void save() {
        

    }
    
}
