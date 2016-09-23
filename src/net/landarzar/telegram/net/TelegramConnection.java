/**
 * 
 */
package net.landarzar.telegram.net;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import net.landarzar.telegram.TelegramBotProperties;
import net.landarzar.telegram.model.methodes.Method;
import net.landarzar.telegram.model.methodes.MethodResult;

/**
 * @author Kai Sauerwald
 *
 */
public class TelegramConnection
{
	private static final Logger log = Logger.getLogger("TelegramBot");

	TelegramBotProperties properties;
	ConcurrentLinkedQueue<Method> queue;

	/**
	 * Erzeugt eine neue Instanz dieser Klasse
	 */
	public TelegramConnection(TelegramBotProperties prop)
	{
		properties = prop;
		queue = new ConcurrentLinkedQueue<>();
	}

	public void enque(Method m)
	{
		queue.add(m);
	}

	/**
	 * @param con
	 */
	private void sendMsg(Method m, HttpsURLConnection con) throws IOException
	{
		String msg = m.buildMethod();
		if (msg != null && !msg.isEmpty()) {
			log.log(Level.FINER, "Sending Parameter for Method <" + m.getPath() + ">: " + msg);
			System.out.println("Sending Parameter for Method <" + m.getPath() + ">: " + msg);
			OutputStream os = con.getOutputStream();
			OutputStreamWriter osr = new OutputStreamWriter(os);
			BufferedWriter out = new BufferedWriter(osr);

			out.write(msg);
			out.flush();
			log.log(Level.FINER, "[Success] Sending Parameter for Method <" + m.getPath() + ">");
		}
	}

	private void recieveMsg(Method m, HttpsURLConnection con) throws IOException
	{
		InputStream ins = con.getInputStream();
		log.log(Level.FINER, "Recieving Result for Method <" + m.getPath() + ">");
		try {
			m.afterPerform(new MethodResult(m, ins));
		} finally {
			ins.close();
		}
	}

	public void performMethode(Method m) throws IOException
	{
		if (m == null) {
			log.log(Level.WARNING, "TelegrammConnection revieves empty Method (null)");
			return;
		}

		String httpsURL = properties.NET_BASEURL + "bot" + properties.NET_BOT_ID + ":" + properties.NET_BOT_TOKEN + "/" + m.getPath();
		log.log(Level.FINER, "[Execution] Method <" + m.getPath() + ">");
		URL myurl = new URL(httpsURL);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		con.connect();

		sendMsg(m, con);
		recieveMsg(m, con);
	}

	public boolean pump() throws IOException
	{
		Method m = queue.poll();
		if (m == null)
			return false;
		performMethode(m);
		return true;
	}

}
