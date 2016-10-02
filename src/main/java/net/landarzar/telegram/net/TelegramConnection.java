/**
 * 
 */
package net.landarzar.telegram.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

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
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		con.connect();
		String msg = m.buildMethod();
		if (msg != null && !msg.isEmpty()) {
			log.log(Level.FINER, "[Network - " + m.getPath() + "] Start sending Parameter for Method: " + msg);
			// System.out.println("Sending Parameter for Method <" + m.getPath()
			// + ">: " + msg);
			OutputStream os = con.getOutputStream();
			OutputStreamWriter osr = new OutputStreamWriter(os);
			BufferedWriter out = new BufferedWriter(osr);

			out.write(msg);
			out.flush();
			log.log(Level.FINER, "[Network - " + m.getPath() + "] finish sending parameter for Method ");
		}
	}

	private void sendMsgMultipart(MultipartMethod m, HttpsURLConnection con) throws IOException
	{
		con.setRequestProperty("Content-Type", "multipart/form-data;; boundary=-----------------------3456y7u8");
		log.log(Level.FINER, "[Network - " + m.getPath() + "] Start sending Multipart for Method: " + m.getPath());
		// System.out.println("Sending Parameter for Method <" + m.getPath()
		// + ">: " + msg);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setBoundary("-----------------------3456y7u8");
		m.buildMultiparts(builder);
		HttpEntity entity = builder.build();
		con.setRequestProperty("Context-Length", "" + entity.getContentLength());
		con.connect();
		OutputStream os = con.getOutputStream();
		entity.writeTo(os);
		os.flush();
		log.log(Level.FINER, "[Network - " + m.getPath() + "] finish sending parameter for Method ");
	}

	private void recieveMsg(Method m, HttpsURLConnection con) throws IOException
	{
		try {
			InputStream ins = con.getInputStream();
			try {
				log.log(Level.FINER, "[Network - " + m.getPath() + "] Recieving Result for Method");
				m.afterPerform(new MethodResult(m, ins));
			} finally {
				ins.close();
			}
		} catch (IOException io) {
			log.log(Level.FINER, "[Network - " + m.getPath() + "] Recieving Error Result for Method");
			if (con.getErrorStream() != null) {
				InputStreamReader isr = new InputStreamReader(con.getErrorStream());
				BufferedReader reader = new BufferedReader(isr);
				System.out.println(reader.readLine());
				m.afterPerform(new MethodResult(m, con.getErrorStream()));
			} else
				System.out.println(con.getResponseCode());
			{
				System.out.println(con.getResponseMessage());
			}
		}
	}

	public void performMethode(Method m) throws IOException
	{
		if (m == null) {
			log.log(Level.WARNING, "[Queue, Error] TelegrammConnection revieves empty Method (null)");
			return;
		}

		String httpsURL = properties.NET_BASEURL + "bot" + properties.NET_BOT_ID + ":" + properties.NET_BOT_TOKEN + "/" + m.getPath();
		log.log(Level.FINER, "[Queue   - " + m.getPath() + "] Executing Method");
		URL myurl = new URL(httpsURL);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		con.setDoOutput(true);

		if (m instanceof MultipartMethod && ((MultipartMethod) m).shouldSendMultipart()) {
			sendMsgMultipart((MultipartMethod) m, con);
			recieveMsg(m, con);
		} else {
			sendMsg(m, con);
			recieveMsg(m, con);
		}
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
