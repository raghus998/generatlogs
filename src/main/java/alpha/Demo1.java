package alpha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Demo1 {
	private static Logger log = LogManager.getLogger(Demo1.class.getName());
	@Test
	public void testA()
	{
		log.info("info log");
		log.fatal("fetal log");
		log.error("Error log");
		log.debug("debug log");
		log.trace("trace log");
		log.warn("warn log");
	}
}
