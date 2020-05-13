
package com.illoismael.finalproyect.controller;

import com.illoismael.finalproyect.App;

public interface IControllers {
    
    /**
     * Método para iniciar la app 
     * @param app recibe un App
     */
    void setMainApp(App app);
    
    //To be ovewritten
    void onLoad();
    
    //To be ovewritten
    /**
     * Método para cerrar el modal
     * @param response recibe un objeto
     */
    void doOnCloseModal(Object response);
    
}
