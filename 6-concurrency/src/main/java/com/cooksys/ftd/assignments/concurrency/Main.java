package com.cooksys.ftd.assignments.concurrency;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.concurrency.model.config.*;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Main {

    /**
     * First, load a {@link com.cooksys.ftd.assignments.concurrency.model.config.Config} object from
     * the <project-root>/config/config.xml file.
     *
     * If the embedded {@link com.cooksys.ftd.assignments.concurrency.model.config.ServerConfig} object
     * is not disabled, create a {@link Server} object with the server config and spin off a thread to run it.
     *
     * If the embedded {@link com.cooksys.ftd.assignments.concurrency.model.config.ClientConfig} object
     * is not disabled, create a {@link Client} object with the client config ans spin off a thread to run it.
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
       //throw new NotImplementedException();
    	// Path path = FileSystems.getDefault().getPath("config", "config.xml");
    	Config config = Config.load(FileSystems.getDefault().getPath("config", "config.xml"));
//System.out.println(config.toString());
																		//End of reading config file
//		int port = config.getRemote().getPort();
//		String hostIP = config.getRemote().getHost();   	
		
		if(config.getServer().default) 
    }
}
