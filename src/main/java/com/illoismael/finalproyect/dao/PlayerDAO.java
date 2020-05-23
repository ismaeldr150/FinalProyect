package com.illoismael.finalproyect.dao;

import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Player;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.model.Videogame;
import com.illoismael.finalproyect.utils.ConnectionUtil;
import com.illoismael.finalproyect.utils.Dialog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerDAO extends Player implements IDAO {

    public static String SELECT_ALL = "SELECT * FROM player";
    public static String INSERT = "INSERT INTO player (id,name,age,salary) VALUES (NULL,?,?,?)"; // ¿Añadir CodTeam?
    public static String GETBYID = "SELECT * FROM player WHERE id=?";
    public static String FINDBYID = "SELECT * FROM player WHERE id IN";
    public static String FINDBYNAME = "SELECT * FROM player WHERE name LIKE ?";
    public static String UPDATE = "UPDATE player SET name = ?, age = ?, salary = ? = WHERE id = ?";
    public static String REMOVE = "DELETE FROM player WHERE id=?";

    public static String select2 = "WHERE name LIKE ?";

    java.sql.Connection con;
    private boolean persist;

    @Override
    public int remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    enum queries {
        INSERT("INSERT INTO player (id,name,age,salary) VALUES (NULL,?,?,?)"),
        ALL("SELECT * FROM player"),
        GETBYID("SELECT * FROM player WHERE id=?"),
        FINDBYID("SELECT * FROM player WHERE id IN "),
        FINDBYNAME("SELECT * FROM player WHERE name LIKE ?"),
        UPDATE("UPDATE player SET name = ?, age = ?, salary = ? = WHERE id = ?"),
        REMOVE("DELETE FROM player WHERE id=?");
        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }

    public PlayerDAO() {
    }

    public PlayerDAO(Player p) {
    }

    public PlayerDAO(int id, String name, int age, int salary) {
        super(id, name, age, salary);
    }

    public PlayerDAO(int i) {
        this();
        List<Object> params = new ArrayList<>();
        params.add(i);
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.GETBYID.getQ(), params);

            if (rs != null) {

                while (rs.next()) {
                    Player p = instanceBuilder(rs);
                    this.id = p.getId();
                    this.name = p.getName();
                    this.age = p.getAge();
                    this.salary = p.getSalary();
                }

            }
        } catch (SQLException ex) {
            Dialog.showError("FAILED", "Fail loading player", ex.toString());
        }
    }

    public static Player instanceBuilder(ResultSet rs) {

        Player p = new Player();
        if (rs != null) {
            try {
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setSalary(rs.getInt("salary"));

            } catch (SQLException ex) {
                Dialog.showError("FAILED IN SQL", "SQL creating player", ex.toString());
            }

        }
        return p;
    }

    /**
     * Si le pasamos una cadena vacía no tenemos criterio de busqueda,
     * selecciona todos
     *
     * @return
     */
    public static List<Player> selectAll() {
        return selectAll("");
    }

    /**
     * En caso de tener una cadena, busqueda correspondiente
     *
     * @param patter le pasamos un String (cadena)
     * @return
     */
    public static List<Player> selectAll(String patter) {
        List<Player> result = new ArrayList<>();
        

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String query = SELECT_ALL;

            if (patter.length() > 0) {
                query += select2;
            }

            PreparedStatement ps = conn.prepareStatement(query);

            if (patter.length() > 0) {
                ps.setString(1, patter + "%");
            }

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Player p = new Player();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setAge(rs.getInt("age"));
                    p.setSalary(rs.getInt("salary"));
                    result.add(p);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Devuelve los jugadores seleccionados por nombre
     *
     * @param con le pasamos una conexión
     * @param name le pasamos el nombre
     * @return lista de players
     */
    public static List<Player> selectByName(java.sql.Connection con, String name) {

        List<Player> result = new ArrayList<>();
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.FINDBYNAME.getQ(), name + "%");
            if (rs != null) {
                while (rs.next()) {
                    Player p = PlayerDAO.instanceBuilder(rs);
                    result.add(p);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("FAILED", "Fail loading the players", ex.toString());
        }
        return result;

        /*
        List<Player> result = new ArrayList<>();
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, FINDBYNAME, name + "%");
            if (rs != null) {
                while (rs.next()) {
                    Player p = PlayerDAO.instanceBuilder(rs);
                    result.add(p);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Failed to load players", ex.toString());
        }
        return result;
         */
    }

    /**
     * Devuelve los jugadores seleccionados por id
     *
     * @param con le pasamos una conexión
     * @param id le pasamos el nombre
     * @return lista de players
     */
    public static List<Player> selectById(java.sql.Connection con, List<Integer> id) {
        List<Player> result = new ArrayList<>();
        try {
            List<String> newList = new ArrayList<String>(id.size());
            for (Integer myInt : id) {
                newList.add(String.valueOf(myInt));
            }
            String queryTotal = FINDBYID + "(" + String.join(",", newList) + ");";

            ResultSet rs = ConnectionUtil.execQuery(con, queryTotal, null);
            if (rs != null) {
                while (rs.next()) {
                    Player p = PlayerDAO.instanceBuilder(rs);
                    result.add(p);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("FAILED", "Fail loading the players", ex.toString());
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

    /*
    @Override
    public int remove() {
        if (this.id != -1) {
            try {
                int rs = ConnectionUtil.execUpdate(con, queries.REMOVE.getQ(), this.id, false);
            } catch (SQLException ex) {
                Dialog.showError("FAILED", "FAIL REMOVING PLAYER", ex.toString());
            }
        }
    }
    */

    @Override
    public int save() {

        int result = -1;

        return result;
    }

}
