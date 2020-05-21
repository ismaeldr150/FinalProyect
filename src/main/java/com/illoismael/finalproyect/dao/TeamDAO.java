package com.illoismael.finalproyect.dao;

import static com.illoismael.finalproyect.dao.PlayerDAO.instanceBuilder;
import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.Player;
import com.illoismael.finalproyect.model.Team;
import com.illoismael.finalproyect.utils.ConnectionUtil;
import com.illoismael.finalproyect.utils.Dialog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TeamDAO extends Team implements IDAO{
    
    public static String SELECT_ALL = "SELECT * FROM team";
    public static String INSERT = "INSERT INTO player (codTeam,name) VALUES (NULL,?)"; // ¿Añadir codVideogame "es clave ajena"?
    public static String GETBYCODE = "SELECT * FROM team WHERE codTeam=?";
    public static String FINDBYCODE= "SELECT * FROM team WHERE id IN";
    public static String FINDBYNAME = "SELECT * FROM team WHERE name LIKE ?";
    public static String UPDATE = "UPDATE team SET name = ? = WHERE id = ?";
    public static String REMOVE = "DELETE FROM team WHERE id=?";

    public static String select2 = "WHERE name LIKE ?";
    
    enum queries {
        INSERT("INSERT INTO team (codTeam,name) VALUES (NULL,)"),
        ALL("SELECT * FROM team"),
        GETBYCODE("SELECT * FROM team WHERE codTeam=?"),
        FINDBYCODE("SELECT * FROM team WHERE codTeam IN "), 
        FINDBYNAME("SELECT * FROM team WHERE name LIKE ?"),
        UPDATE("UPDATE team SET name = ? = WHERE id = ?"),
        REMOVE("DELETE FROM team WHERE id=?");
        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }

    java.sql.Connection con;
    private boolean persist;
 

    public TeamDAO(Team t) {
        super();
    }
    public TeamDAO() {
    }

    public TeamDAO(int codTeam, String name) {
        super(codTeam, name);
    }

    public TeamDAO(int i) {
        this();
        List<Object> params = new ArrayList<>();
        params.add(i);
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, TeamDAO.queries.GETBYCODE.getQ(), params);

            if (rs != null) {

                while (rs.next()) {
                    Team t = instanceBuilder(rs);
                    this.codTeam = t.getCodTeam();
                    this.name = t.getName();
                }

            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el contacto", ex.toString());
        }
    }
    
    public static Team instanceBuilder(ResultSet rs) {
       
        Team t = new Team();
        if (rs != null) {
            try {
                t.setCodTeam(rs.getInt("codTeam"));
                t.setName(rs.getString("name"));
                
            } catch (SQLException ex) {
                Dialog.showError("Error SQL", "SQL creando contacto", ex.toString());
            }

        }
        return t;
    }
    
    public static List<Team> selectAll() {
        return selectAll("");
    }
    
    public static List<Team> selectAll(String patter) {
        List<Team> result = new ArrayList<>();
        Connection c = new Connection("localhost", "idr", "root", ""); //  <-- Cambiar por XML (a fuego no)

        try {
            java.sql.Connection conn = ConnectionUtil.connect(c);
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
                    Team t = new Team();
                    t.setCodTeam(rs.getInt("codTeam"));
                    t.setName(rs.getString("name"));

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
  

    /**
     * Devuelve los equipos seleccionados por nombre
     * @param con le pasamos una conexión
     * @param name le pasamos el nombre
     * @return lista de teams
     */
    public static List<Team> selectByName(java.sql.Connection con, String name) {
        
        List<Team> result = new ArrayList<>();
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.FINDBYNAME.getQ(), name + "%");
            if (rs != null) {
                while (rs.next()) {
                    Team t = TeamDAO.instanceBuilder(rs);
                    result.add(t);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el contactos", ex.toString());
        }
        return result;
        
    }
    
    /**
     * Devuelve los equipos seleccionados por codigo
     * @param con le pasamos una conexión
     * @param id le pasamos el nombre
     * @return lista de equipos
     */
    public static List<Team> selectByCode(java.sql.Connection con, List<Integer> id) {
        List<Team> result = new ArrayList<>();
        try {
            List<String> newList = new ArrayList<String>(id.size());
            for (Integer myInt : id) {
                newList.add(String.valueOf(myInt));
            }
            String queryTotal = FINDBYCODE + "(" + String.join(",", newList) + ");";

            ResultSet rs = ConnectionUtil.execQuery(con, queryTotal, null);
            if (rs != null) {
                while (rs.next()) {
                    Team t = TeamDAO.instanceBuilder(rs);
                    result.add(t);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el contactos", ex.toString());
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
        if (this.codTeam != -1) {
            try {
                int rs = ConnectionUtil.execUpdate(con, queries.REMOVE.getQ(), this.codTeam,false);
            } catch (SQLException ex) {
                Dialog.showError("FAILED", "FAIL REMOVING TEAM", ex.toString());
            }
        }
    }

    @Override
    public void save() {
        queries q = null;
        List<Object> params = new ArrayList<>();
        params.add(this.getName());

        if (this.codTeam == -1) {
            q = q.INSERT;
        } else {
            q = q.UPDATE;
            params.add(this.codTeam);
        }

        try {
            con.setAutoCommit(false);

            int rs = ConnectionUtil.execUpdate(con, q.getQ(), params, (q == q.INSERT ? true : false));
            if (q == TeamDAO.queries.INSERT) {
                this.codTeam = rs;
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            Dialog.showError("ERROR", "Error guardando contacto", ex.toString());
        }
    }
    
}
