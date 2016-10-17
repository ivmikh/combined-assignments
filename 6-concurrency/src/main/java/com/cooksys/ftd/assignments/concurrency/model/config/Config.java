package com.cooksys.ftd.assignments.concurrency.model.config;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

//import com.cooksys.socket.assignment.Config;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

//import com.cooksys.ftd.assignments.concurrency.Server;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {
	

    /**
     * Loads a {@link Config} object from the given xml file path
     *
     * @param path the path at which an xml configuration can be found
     * @return the unmarshalled {@link Config} object
     * @throws JAXBException 
     */
    public static Config load(Path path) throws JAXBException {
        //throw new NotImplementedException();
    	//File file = new File(path.toString());                          //Reading config file:   	
		//JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
		//Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(Config.class).createUnmarshaller();
		return (Config) JAXBContext.newInstance(Config.class).createUnmarshaller().unmarshal(path.toFile());       //config is here

    }

    /**
     * server configuration
     */
    private ServerConfig server;

    /**
     * client configuration
     */
    private ClientConfig client;

    public ServerConfig getServer() {
        return server;
    }

    public void setServer(ServerConfig server) {
        this.server = server;
    }

    public ClientConfig getClient() {
        return client;
    }

    public void setClient(ClientConfig client) {
        this.client = client;
    }
}
