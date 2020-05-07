package com.illoismael.finalproyect.utils;

import com.illoismael.finalproyect.model.Connection;
import com.illoismael.finalproyect.model.ConnectionWrapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLUtil {
    
    public static String file="conn.xml";
    
    public static List<Connection> loadDataXML() {
        List<Connection> result=new ArrayList<>();       
        File f=new File(file);
        if(f.canRead()){
            try{
                JAXBContext context=JAXBContext.newInstance(ConnectionWrapper.class);
                Unmarshaller um = context.createUnmarshaller();
                ConnectionWrapper wrapper = (ConnectionWrapper) um.unmarshal(f);
                result.addAll(wrapper.getConns());
            }catch(JAXBException ex){
                ex.printStackTrace();
                Dialog.showError("ERROR", "Error writing "+file, ex.toString());
                result=new ArrayList<>();
            }
        }
        return result;
    }
    
    public static void writeDataXML(List<Connection> data){
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(ConnectionWrapper.class);
            Marshaller m=context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ConnectionWrapper wrapper = new ConnectionWrapper();
            wrapper.setConns(data);
            m.marshal(wrapper, new File(file));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            Dialog.showError("ERROR", "Error reading "+file, ex.toString());
        }
               
    }
    
}
