
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.utils.MapEntry;
import javafx.scene.Parent;

public interface IAppController {
    
    /**
     * Método que recibe una Scene y sirve para cambiar de diferentes escenas 
     * En nuestra apliación
     * @param scene escena que recibe
     */
    void changeScene(Scenes scene);
    
    
    /**
     * Método que sirve para abrir un nuevo modal
     * @param scene recibe una escena (Scene)
     * @param title recibe un String
     * @param parentController recibe un Controllers
     * @param params objeto
     * @return 
     */
    Controllers openModal(Scenes scene,String title,Controllers parentController,Object params);
    
    /**
     * Cambia la escena a la pantalla about
     */
    void AboutPage();
    
    /**
     * Cierra la aplicación
     */
    void closeApp();
    
    /**
     * Establece la conexión si es distinto de null
     */
    public void enableCon();
    
    /**
     * Cierra la conexión con el servidor
     */
    public void disableCon();
    
    /**
     * Método para ponerle título a la conexión
     * @param txt recibe un String que será el título 
     */
    void title(String txt);
}
