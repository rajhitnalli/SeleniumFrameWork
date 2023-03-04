package Generic_Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Property_Utility {
/**
 * this method is used to login in the application
 * @param Key
 * @return
 * @throws Throwable
 */
	public String getKeyValue(String Key) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/Commondata.properties.txt");
		Properties pro=new Properties();
		pro.load(fis);
		String Value = pro.getProperty(Key);
		return Value;
	}
}
