import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

public class TestClass {

	public static void main(String[] args) throws Exception {
		File file = new File("resources/student.properties");
		FileReader fileReader = new FileReader(file);
		FileWriter fileWriter = new FileWriter(file);
		Properties p = new Properties();
		
		String str = "";
		for (int i = 0; i < 10000; i++) {
			str = nextSessionId();
			p.setProperty(str, "5~5~11/11/1999~5~5~5~5~true~" + str + "~5~5~5~5");
		}
		p.store(fileWriter, "lol");
	}

	private static SecureRandom random = new SecureRandom();

	public static String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
}