package logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Demo2 {
	private static Logger log = LogManager.getLogger(Demo2.class.getName());
	@Test
	public void testA()
	{
		if(5>4)
		{
			log.info("testA is passed");
		}
		else
		{
			log.error("testA is failed");
		}
	}

}
