package com.illoismael.finalproyect.model;

import java.io.File;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


//Con esto indicamos que esta clase va a ser mapeada con un archivo XML
@XmlRootElement(name="CONNECTION")

//Con esto indicamos que TODOS los atributos NO ESTÁTICOS van a ser mapeados
@XmlAccessorType(XmlAccessType.FIELD)

public class Connection {

    private StringProperty name;
    private String server;
    private String database;
    private String userName;
    private String password;

    public Connection(String name) {
        this.name = new SimpleStringProperty(name);
        this.server = "";
        this.userName = "";
        this.password = "";
    }

    public Connection(String server, String database, String userName, String password) {
        this.server = server;
        this.database = database;
        this.userName = userName;
        this.password = password;
        
    }
    
    

    public Connection() {
        this("");
    }

    public StringProperty getN() {
        return name;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void loadDataXML() {
        String file = "conf.xml";
        File f = new File(file);
        if (f.canRead()) {
            //Creamos un contextto de JAXB
            JAXBContext context;
            try {
                //Instanciamos el contexto usando Singleton. Recibe la clase que hace de Wrapper
                context = JAXBContext.newInstance(Connection.class);
                
                //Con unmarsharller leemos. Pasar de XML a Objeto. (Falta el contrario, marshall)
                Unmarshaller um = context.createUnmarshaller();
                Connection myconexion = (Connection) um.unmarshal(f);
                this.server = myconexion.server;
                this.database = myconexion.database;
                this.userName = myconexion.userName;
                this.password = myconexion.password;

            } catch (JAXBException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);

        }
    } else {
            System.out.println("¡Invalid File!");
        }
        
        

    }

    @Override
    public String toString() {
        return "Connection{" + "name=" + name + ", server=" + server + ", userName=" + userName + ", password=" + password + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else {
            if (o instanceof Connection) {
                Connection other = (Connection) o;
                if (name.getValue().equals(((Connection) o).name.getValue())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

}
