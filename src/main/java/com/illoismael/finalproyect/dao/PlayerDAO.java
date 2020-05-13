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

public class PlayerDAO extends Player implements DAO{
    
    
    
    public static String SELECT_ALL = "SELECT * FROM player";
    public static String INSERT = "INSERT INTO player (id,name,age,phone,videogame,team,idEmpleado) VALUES (NULL,?,?,?,?,?,NULL)";
    public static String GETBYID = "SELECT * FROM player WHERE idEmpleado=?";
    public static String FINDBYID = "SELECT * FROM player WHERE idEmpleado IN";
    public static String FINDBYNAME = "SELECT * FROM player WHERE name LIKE ?";
    public static String UPDATE = "UPDATE player SET name = ?, age = ?, phone = ?, videogame = ?, team ? = WHERE idEmpleado = ?";
    public static String REMOVE = "DELETE FROM player WHERE idEmpleado=?";
    
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

    public PlayerDAO(int id, String name, int age, String phone, Videogame videogame, Team team, int idEmpleado) {
        super(id, name, age, phone, videogame, team, idEmpleado);
    }
    
    public PlayerDAO(int id, String name, int age, String phone, Videogame videogame, Team team) {
        super(id, name, age, phone, videogame, team);
    }
    
    
    public PlayerDAO(Player p) {
        this(p.getId(), p.getName(), p.getAge(), p.getPhone(), p.getVideogame(), p.getTeam());
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
                p.setPhone(rs.getString("phone"));
              //  p.setVideogame(rs.getString("videogame"));
              //  p.setTeam(rs.getString("team"));
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
