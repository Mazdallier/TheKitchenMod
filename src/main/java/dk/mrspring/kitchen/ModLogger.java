package dk.mrspring.kitchen;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class ModLogger
{
    // Integers used to choose which type of message to Log
    public static final int INFO = 0;
    public static final int WARNING = 1;
    public static final int ERROR = 2;
    public static final int DEBUG = 3;

    private static Logger logger;

    public static void initializeLogger(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    public static void print(int type, String message)
    {
        print(type, message, null);
    }

    public static void print(int type, String message, Throwable error)
    {
        switch (type)
        {
            case INFO:
                logger.info(message);
                break;
            case WARNING:
                logger.warn(message, error);
                break;
            case ERROR:
                logger.error(message, error);
                break;
            case DEBUG:
                logger.debug(message, error);
                break;
        }

//		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

		/*switch (type)
        {
			case INFO:
				System.out.println("[" + time + "] [TheKitchenMod/INFO]: " + message);
				break;
			case WARNING:
				System.out.println("[" + time + "] [TheKitchenMod/WARNING]: " + message);
				if (obj[0] != null && obj[0] instanceof Exception) ((Exception) obj[0]).printStackTrace();
				break;
			case ERROR:
				System.err.println("[" + time + "] [TheKitchenMod/ERROR]: " + message);
				if (obj[0] != null && obj[0] instanceof Exception) ((Exception) obj[0]).printStackTrace();
				break;
			case DEBUG:
				if (ModConfig.getKitchenConfig().show_console_debug)
					System.out.println("[" + time + "] [TheKitchenMod/DEBUG]: " + message);
				break;
			default:
				System.out.println("[" + time + "] [TheKitchenMod/INFO]: " + message);
				break;
		}*/
    }
}
