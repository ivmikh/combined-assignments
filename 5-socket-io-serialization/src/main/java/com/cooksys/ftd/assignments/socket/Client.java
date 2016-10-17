package com.cooksys.ftd.assignments.socket;

import java.io.File;
import java.io.IOException;
//import java.io.OutputStream;
//import java.io.InputStream;
//import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client implements Runnable {

    public void run(){
        // TODO
    	try {
			File file = new File("config\\config.xml");                          //Reading config file:   	

			JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Config config = (Config) jaxbUnmarshaller.unmarshal(file);       //config is here
//System.out.println(config.toString());
																			//End of reading config file
			int port = config.getRemote().getPort();
			String hostIP = config.getRemote().getHost();   	

			//Establishing a connection with Server:
			Socket inSocket = null;
			// boolean noConnectionEstablished = true;
			while (inSocket == null) {
				// while(noConnectionEstablished);
				try {
					inSocket = new Socket(hostIP, port);
					// noConnectionEstablished = false;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
			System.out.println("Client: Connection with Server established!");
			
//There the student is unmarshalled from Server:

jaxbContext = JAXBContext.newInstance(Student.class);
jaxbUnmarshaller = jaxbContext.createUnmarshaller();
Student student = (Student) jaxbUnmarshaller.unmarshal(inSocket.getInputStream());

System.out.println(student.toString());

inSocket.close();
System.out.println("Client: I am done!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
