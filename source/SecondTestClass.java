import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Properties;

public class SecondTestClass {
	
	public static final int AGE = 8;
	public static final String BIRTH_DATE = "2009-01-01";
	public static final int GRADE = 3;
	public static final boolean IS_MORNING_CLASS = true;
	
	public static void main(String[] args) throws Exception {
		File file = new File("resources/student.properties");
		FileReader file2 = new FileReader("resources/names.txt");
		BufferedReader name = new BufferedReader(file2);
		FileReader fileReader = new FileReader(file);
		Properties p = new Properties();
		p.load(fileReader);
		FileWriter fileWriter = new FileWriter(file);
		
		String str = "";
		String[] array = null;
		for (int i = 0; i < 10; i++) {
			str = name.readLine();
			array = str.split(" ", 2);
			p.setProperty(array[1] + " " + array[0], "8 Rob Ave.~" + AGE + "~" + BIRTH_DATE + "~Simon Rob~647647647~" + GRADE + "~884689755-XX~" + IS_MORNING_CLASS + "~" + array[1] + " " + array[0] + "~404-404-404~Rob Simon~416416416~");
		}
		p.store(fileWriter, "lol");
	}
}
