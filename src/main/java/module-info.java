module com.illoismael.finalproyect {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.xml.bind;
    requires java.prefs;

    //borrame
    opens com.illoismael.finalproyect.utils to java.xml.bind; //Para que JAXB pueda ejecutarse en XMLUtil
    opens com.illoismael.finalproyect.controller to javafx.fxml;
    opens com.illoismael.finalproyect.model to java.xml.bind; //Para que JAXB pueda ejecutarse en ConnectionWrapper
    
    exports com.illoismael.finalproyect;
    exports com.illoismael.finalproyect.model;  //para que JAXB pueda acceder a Connection y Connection wrapper
}