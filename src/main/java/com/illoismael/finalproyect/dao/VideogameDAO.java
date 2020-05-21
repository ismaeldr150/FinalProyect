package com.illoismael.finalproyect.dao;

import com.illoismael.finalproyect.enums.VideogameType;
import com.illoismael.finalproyect.model.Connection;
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

public class VideogameDAO extends Videogame implements IDAO {

    public static String SELECT_ALL = "SELECT * FROM videogame";
    public static String INSERT = "INSERT INTO videogame (codVideogame,name,description,type) VALUES (NULL,?,?,?)";
    public static String GETBYCODE = "SELECT * FROM videogame WHERE codVideogame=?";
    public static String FINDBYCODE = "SELECT * FROM videogame WHERE codVideogame IN";
    public static String FINDBYNAME = "SELECT * FROM videogame WHERE name LIKE ?";
    public static String UPDATE = "UPDATE videoagme SET name = ?, description = ?, type = ? = WHERE codVideogame = ?";
    public static String REMOVE = "DELETE FROM videogame WHERE codVideogame=?";

    public static String select2 = "WHERE name LIKE ?";

    enum queries {
        INSERT("INSERT INTO videogame (codideogame,name,description,type) VALUES (NULL,?,?,?)"),
        ALL("SELECT * FROM videogame"),
        GETBYCODE("SELECT * FROM videogame WHERE codVideogame=?"),
        FINDBYCODE("SELECT * FROM videogame WHERE id IN "),
        FINDBYNAME("SELECT * FROM videogame WHERE name LIKE ?"),
        UPDATE("UPDATE videogame SET name = ?, description = ?, type= ? = WHERE codVideogame = ?"),
        REMOVE("DELETE FROM videogame WHERE codVideogame*-+=?");
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

    public VideogameDAO(Videogame v) {
        super();
    }

    public VideogameDAO(int codVideogame, String name, String desciption, String type) {
        super(codVideogame, name, desciption, type);
    }

    public VideogameDAO() {
    }

    public static Videogame instanceBuilder(ResultSet rs) {

        Videogame t = new Videogame();
        if (rs != null) {
            try {
                t.setCodVideogame(rs.getInt("codVideogame"));
                t.setName(rs.getString("name"));
                t.setDescription(rs.getString("description"));
                t.setType(rs.getString("type"));

            } catch (SQLException ex) {
                Dialog.showError("FAILED SQL", "SQL creating videogame", ex.toString());
            }

        }
        return t;
    }

    public VideogameDAO(int i) {
        this();
        List<Object> params = new ArrayList<>();
        params.add(i);
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.GETBYCODE.getQ(), params);

            if (rs != null) {

                while (rs.next()) {
                    Videogame t = instanceBuilder(rs);
                    this.codVideogame = t.getCodVideogame();
                    this.name = t.getName();
                    this.description = t.getDescription();
                    this.type = t.getType();
                }

            }
        } catch (SQLException ex) {
            Dialog.showError("FAILED", "Fail  loading the videogame", ex.toString());
        }
    }

    public static List<Videogame> selectAll() {
        return selectAll("");
    }

    public static List<Videogame> selectAll(String patter) {
        List<Videogame> result = new ArrayList<>();
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
                    Videogame v = new Videogame();
                    v.setCodVideogame(rs.getInt("codVideogame"));
                    v.setName(rs.getString("name"));
                    v.setDescription(rs.getString("description"));
                    v.setType(rs.getString("type"));
                    result.add(v);
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
     * Devuelve los videojuegos seleccionados por nombre
     *
     * @param con le pasamos una conexión
     * @param name le pasamos el nombre
     * @return lista de videojuegos
     */
    public static List<Videogame> selectByName(java.sql.Connection con, String name) {

        List<Videogame> result = new ArrayList<>();
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.FINDBYNAME.getQ(), name + "%");
            if (rs != null) {
                while (rs.next()) {
                    Videogame v = VideogameDAO.instanceBuilder(rs);
                    result.add(v);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("FAILED", "Fail loading the videogames", ex.toString());
        }
        return result;
    }

    /**
     * Devuelve los Videojuegos seleccionados por codVideogame
     *
     * @param con le pasamos una conexión
     * @param id le pasamos el nombre
     * @return lista de videogames
     */
    public static List<Videogame> selectByCode(java.sql.Connection con, List<Integer> id) {
        List<Videogame> result = new ArrayList<>();
        try {
            List<String> newList = new ArrayList<String>(id.size());
            for (Integer myInt : id) {
                newList.add(String.valueOf(myInt));
            }
            String queryTotal = FINDBYCODE + "(" + String.join(",", newList) + ");";

            ResultSet rs = ConnectionUtil.execQuery(con, queryTotal, null);
            if (rs != null) {
                while (rs.next()) {
                    Videogame v = VideogameDAO.instanceBuilder(rs);
                    result.add(v);
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
        this.persist = true;
    }

    @Override
    public void remove() {
        if (this.codVideogame != -1) {
            try {
                int rs = ConnectionUtil.execUpdate(con, queries.REMOVE.getQ(), this.codVideogame,false);
            } catch (SQLException ex) {
                Dialog.showError("FAILED", "FAIL REMOVING PLAYER", ex.toString());
            }
        }
    }

    @Override
    public void save() {
        queries q = null;
        List<Object> params = new ArrayList<>();
        params.add(this.getName());
        params.add(this.getDescription());
        params.add(this.getType());

        if (this.codVideogame == -1) {
            q = q.INSERT;
        } else {
            q = q.UPDATE;
            params.add(this.codVideogame);
        }

        try {
            con.setAutoCommit(false);

            int rs = ConnectionUtil.execUpdate(con, q.getQ(), params, (q == q.INSERT ? true : false));
            if (q == VideogameDAO.queries.INSERT) {
                this.codVideogame = rs;
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            Dialog.showError("FAILED", "Fail saving videogame", ex.toString());

        }
    }

}
