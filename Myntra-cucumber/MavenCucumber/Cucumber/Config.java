package MavenCucumber.Cucumber;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	public static Properties prop;
	public Properties getProperty()
	{
		FileInputStream fis=null;
		prop=new Properties();
		try
		{
			prop.load(new FileInputStream("Properties/config.properties"));
		}
		catch (Exception e)
		{
			System.out.println("Exception: "+ e);
		}
		return prop;
	}

}
