package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyFileUtil {
	
	public static String getValueForKey(String key) throws Throwable
	{
		Properties config = new Properties();
		FileInputStream fi = new FileInputStream("D:\\Ojt4oclock\\ERP_Accounting\\PropertyFile\\Environment.properties");
		config.load(fi);
		return config.getProperty(key);
		
	}

}
