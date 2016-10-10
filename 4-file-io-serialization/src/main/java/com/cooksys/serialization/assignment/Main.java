package com.cooksys.serialization.assignment;

import com.cooksys.serialization.assignment.model.*;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

public class Main {

    /**
     * Creates a {@link Student} object using the given studentContactFile.
     * The studentContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param studentContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return a {@link Student} object built using the {@link Contact} data in the given file
     */
    public static Student readStudent(File contactFile, JAXBContext jaxbContext) {
        //return null; // TODO
    	try {
    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		Contact contact = (Contact) jaxbUnmarshaller.unmarshal(contactFile);
    	System.out.println(contact.toString());
    	    Student student = new Student();
    	    student.setContact(contact);
    		return student;
    		
    	  } catch (JAXBException e) {
    		e.printStackTrace();
    	  }
    	return null;
    }

    /**
     * Creates a list of {@link Student} objects using the given directory of student contact files.
     *
     * @param studentDirectory the directory of student contact files to use
     * @param jaxb the JAXB context to use
     * @return a list of {@link Student} objects built using the contact files in the given directory
     */
    public static List<Student> readStudents(File studentDirectory, JAXBContext jaxb) {
        //return null; // TODO
    	List<Student> students = new ArrayList<Student>();
    	File[] listOfFiles = studentDirectory.listFiles();

    	    for (File file:listOfFiles) {
    	      if (file.isFile()) {
    	    	Student student = readStudent(file, jaxb);    
    	    	students.add(student);
    	      }
    	    }
    	    return students;
    }

    /**
     * Creates an {@link Instructor} object using the given instructorContactFile.
     * The instructorContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param instructorContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return an {@link Instructor} object built using the {@link Contact} data in the given file
     */
    public static Instructor readInstructor(File contactFile, JAXBContext jaxbContext) {
        //return null; // TODO
    	try {
    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		Contact contact = (Contact) jaxbUnmarshaller.unmarshal(contactFile);
    	System.out.println(contact.toString());
    	    Instructor instructor = new Instructor();
    	    instructor.setContact(contact);
    		return instructor;

    	  } catch (JAXBException e) {
    		e.printStackTrace();
    	  }
    	return null;
    }

    /**
     * Creates a {@link Session} object using the given rootDirectory. A {@link Session}
     * root directory is named after the location of the {@link Session}, and contains a directory named
     * after the start date of the {@link Session}. The start date directory in turn contains a directory named
     * `students`, which contains contact files for the students in the session. The start date directory
     * also contains an instructor contact file named `instructor.xml`.
     *
     * @param rootDirectory the root directory of the session data, named after the session location
     * @param jaxb the JAXB context to use
     * @return a {@link Session} object built from the data in the given directory
     */
    public static Session readSession(File rootDirectory, JAXBContext jaxb) {
        return null; // TODO
    }

    /**
     * Writes a given session to a given XML file
     *
     * @param session the session to write to the given file
     * @param sessionFile the file to which the session is to be written
     * @param jaxb the JAXB context to use
     */
    public static void writeSession(Session session, File sessionFile, JAXBContext jaxb) {
        // TODO
    }

    /**
     * Main Method Execution Steps:
     * 1. Configure JAXB for the classes in the com.cooksys.serialization.assignment.model package
     * 2. Read a session object from the <project-root>/input/memphis/ directory using the methods defined above
     * 3. Write the session object to the <project-root>/output/session.xml file.
     *
     * JAXB Annotations and Configuration:
     * You will have to add JAXB annotations to the classes in the com.cooksys.serialization.assignment.model package
     *
     * Check the XML files in the <project-root>/input/ directory to determine how to configure the {@link Contact}
     *  JAXB annotations
     *
     * The {@link Session} object should marshal to look like the following:
     *      <session location="..." start-date="...">
     *           <instructor>
     *               <contact>...</contact>
     *           </instructor>
     *           <students>
     *               ...
     *               <student>
     *                   <contact>...</contact>
     *               </student>
     *               ...
     *           </students>
     *      </session>
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
//    	String dirName = "input\\memphis\\08-08-2016\\";
//    	File instructorContactFile = new File(dirName + "instructor.xml");
    	JAXBContext jaxbContext = JAXBContext.newInstance(Contact.class);
//    	Instructor instructor = readInstructor(instructorContactFile, jaxbContext); //Reading Instructor!
//    	
//    	dirName += "students\\";
//    	File dir = new File(dirName);
//    	List<Student> studentsList = readStudents(dir, jaxbContext);
    	
    	String dirName = "input\\";
    	Session session = new Session();
    	File dir = new File(dirName);
    	
    	File[] listOfFiles = dir.listFiles();
	    for (File locationDir:listOfFiles) {
	      if (! locationDir.isFile()) {
	    	String sessionLocation = locationDir.getName(); //Now we know session location!
	    	//System.out.println(sessionLocation);
	    	session.setLocation(sessionLocation);
	    	    dirName += sessionLocation + "\\";                //Go deeper
	    	    dir = new File(dirName);
	    	    listOfFiles = dir.listFiles();
	    	    for (File dateDir:listOfFiles) {
	    		      if (! dateDir.isFile()) {
	    		    	  String sessionDate = dateDir.getName(); //Now we know session date!
	    			    	//System.out.println(sessionDate);

	    			    	session.setStartDate(sessionDate);
	    			    	    dirName += sessionDate + "\\";                //Go deeper
	    			    	    //System.out.println(dirName);
	    			    	    dir = new File(dirName);
	    			    	    listOfFiles = dir.listFiles();
	    			    	    
	    			    	    for (File contactsDir:listOfFiles) {
	    			    		      if (! contactsDir.isFile()) {     //If the file is a folder ("students"):
	    			    		      	List<Student> studentsList = readStudents(contactsDir, jaxbContext);
	    			    		      	session.setStudents(studentsList);
	    			    		      }
	    			    		      else {                           //If the file is a file ("instructor.xml"):
	    			    			    	File instructorFile = new File(dirName + "instructor.xml");
	    			    			    	//JAXBContext jaxbContext = JAXBContext.newInstance(Contact.class);
	    			    			    	Instructor instructor = readInstructor(instructorFile, jaxbContext); //Reading Instructor!
	    			    			    	//System.out.println(instructor.toString());
	    			    			    	session.setInstructor(instructor);
	    			    		      }
	    			    		      //break;
	    			    	    }
	    		    	  
	    		      }
	    		      break;
	    	    }
	    	break;
	      }
	    }
	    
	    /*
	     * Marshalling
	     *
	     */
	    File file = new File("output\\session.xml");
		JAXBContext jaxb = JAXBContext.newInstance(Session.class);
		Marshaller jaxbMarshaller = jaxb.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(session, file);
		//jaxbMarshaller.marshal(session, System.out);
    }
}
