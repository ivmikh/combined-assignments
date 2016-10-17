package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Server implements Runnable {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the
     * @param jaxb
     * @return
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) {
        //return null; // TODO
    	File file = new File(studentFilePath);
    	//System.out.println(studentPath);
    	//jaxbContext = JAXBContext.newInstance(Student.class);
		Student student = new Student();
		try {
			Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
			student = (Student) jaxbUnmarshaller.unmarshal(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return student;
    }

    @Override
    public void run() {
        // TODO
		try {
    	File file = new File("config\\config.xml");                          //Reading config file:   	

        	JAXBContext jaxbContext;
			Unmarshaller jaxbUnmarshaller;
			Config config = new Config();

				jaxbContext = JAXBContext.newInstance(Config.class);
				jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				config = (Config) jaxbUnmarshaller.unmarshal(file);

    	//System.out.println(config.toString());
																			//End of reading config file
			int port = config.getRemote().getPort();
			String studentPath = config.getStudentFilePath();   	
    	
    	//There the student is unmarshalled from Local:
			Student student = new Student();
			jaxbContext = JAXBContext.newInstance(Student.class);
			student = loadStudent(studentPath, jaxbContext);


		//Establishing a connection with Client:
    	ServerSocket ss = new ServerSocket(port);
    	Socket conn = ss.accept();
    	System.out.println("Server: Connection with Client established!");
    	OutputStream out = conn.getOutputStream();
    	
    	//Marshalling Student to Client:
    	
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(student, out);
    	
		out.close();
		conn.close();
		ss.close();
		System.out.println("Server: I am done!");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
