import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.cooksys.ftd.assignments.socket.Client;
import com.cooksys.ftd.assignments.socket.Server;
import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;

//import com.cooksys.serialization.assignment.model.Session;

public class Main {
	static private void createConfig()  throws JAXBException, UnknownHostException {
	// TODO generate config.xml
	int port = 8080;
	//System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());
	String ip = InetAddress.getLocalHost().getHostAddress();
	//String ip = "localhost";
	
	LocalConfig local = new LocalConfig();
	local.setPort(port);
	
    Config config = new Config();
    config.setLocal(local);
    
	RemoteConfig remote = new RemoteConfig();
	remote.setPort(port);
	remote.setHost(ip);
	
	config.setRemote(remote);
	config.setStudentFilePath("config\\student.xml");
		
	File file = new File("config\\config.xml");
	
	JAXBContext jaxb = JAXBContext.newInstance(Config.class);
	Marshaller jaxbMarshaller = jaxb.createMarshaller();
	
	// output pretty printed
	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	jaxbMarshaller.marshal(config, file);
	//jaxbMarshaller.marshal(session, System.out);
	}
	
	public static void main(String[] args) throws InterruptedException{
	try {createConfig();} catch (UnknownHostException e) {e.printStackTrace();} catch (JAXBException e) {e.printStackTrace();}
		
	Thread serverThread = new Thread(new Server());
	Thread clientThread = new Thread(new Client());
	clientThread.start();
	Thread.sleep(1000*2);
	serverThread.start();
	//Thread.sleep(1000*1);

	
	System.out.println(Thread.currentThread().getName()+": I am done!");
}
}
