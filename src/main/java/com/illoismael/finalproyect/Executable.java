package com.illoismael.finalproyect;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Executable {
    public static void main(String[] args) throws IOException {
        
        //Guarda todos los errores que se ejecute donde pone "logger" los vuelca 
        //En ese archivo 
        Logger logger = Logger.getLogger("Final");
        FileHandler fh = new FileHandler("MyLogFile.log");
        App.main(args);
    }
}
