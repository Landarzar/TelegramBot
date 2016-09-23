/**
 * 
 */
package net.landarzar.telegram;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Kai Sauerwald
 *
 */
public class TelegramBotProperties
{
	/**
	 * 
	 */
	public TelegramBotProperties(int bot_id, String bot_token)
	{
		this.NET_BOT_ID = bot_id;
		this.NET_BOT_TOKEN = bot_token;
	}
	
	public boolean THREADED = true;

	/***
	 * The Time im MS for System Ticks
	 */
	public int SYSTEM_TICK = 50;
	
	/***
	 * The Time im MS for the next poll from Server
	 */
	public int NET_UPDATE_INTERVAL = 1000;
	/***
	 * ID of the Bot
	 */
	public int NET_BOT_ID = -1;

	/***
	 * Each bot is given a unique authentication token when it is created
	 */
	public String NET_BOT_TOKEN = null;

	/***
	 * All queries to the Telegram Bot API must be served over HTTPS and need to
	 * be presented in this form:
	 * https://api.telegram.org/bot<token>/METHOD_NAME
	 */
	public String NET_BASEURL = "https://api.telegram.org/";
}
