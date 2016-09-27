/**
 * 
 */
package net.landarzar.telegram;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.landarzar.telegram.model.methodes.GetUpdates;
import net.landarzar.telegram.model.methodes.Method;
import net.landarzar.telegram.model.methodes.MethodResult;
import net.landarzar.telegram.model.types.CallbackQuery;
import net.landarzar.telegram.model.types.ChosenInlineResult;
import net.landarzar.telegram.model.types.InlineQuery;
import net.landarzar.telegram.model.types.Message;
import net.landarzar.telegram.model.types.Update;
import net.landarzar.telegram.model.types.UpdateContent;
import net.landarzar.telegram.net.TelegramConnection;

/**
 * @author Kai Sauerwald
 *
 */
public abstract class TelegramBot
{
	private static final Logger log = Logger.getLogger("TelegramBot");
	private Thread mainThread;
	private volatile long time = -1L;
	private int offset = -1;
	/***
	 * Variable for the Thread
	 */
	private volatile boolean shouldRun = false;

	protected TelegramBotProperties properties;
	protected TelegramConnection conn;

	private void onUpdate(MethodResult mr, List<Update> list)
	{
		for (Update u : list) {
			log.log(Level.WARNING, "[Handler] Recieving Update: " + mr.getResult().toString());
			if(u.update_id > offset)
				offset = u.update_id ;
			if (u.content.getContentType() == UpdateContent.ContentType.MESSAGE)
				onMessage((Message) u.content, u);
			else if (u.content.getContentType() == UpdateContent.ContentType.INLINE_QUERY)
				onInlineQuery((InlineQuery) u.content, u);
			else
				log.log(Level.WARNING, "[Handler] Unhandled Update (TYPE="+ u.content.getContentType().name() +", ID=" + u.update_id + ")");
		}
	}

	private void mainLoop()
	{
		try {
			time = System.currentTimeMillis();
			long ctime;
			while (shouldRun) {
				ctime = System.currentTimeMillis();
				if (ctime - time > properties.NET_UPDATE_INTERVAL) {
					time = ctime;
					if (offset == -1)
						conn.performMethode(new GetUpdates(this::onUpdate));
					else
						conn.performMethode(new GetUpdates(offset + 1, this::onUpdate));
				}

				Thread.sleep(properties.SYSTEM_TICK);
				while(conn.pump());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TelegramBot(TelegramBotProperties prop)
	{
		//TODO: Add Checks for Thread
		properties = prop;
		conn = new TelegramConnection(properties);

	}

	protected void StartBot()
	{
		shouldRun = true;
		mainThread = new Thread(this::mainLoop);
		if (properties.SYSTEM_THREADED) {
			mainThread.start();
		} else {
			throw new UnsupportedOperationException("At the moment only the thread mode is implemented.");
		}
	}
	
	protected void StopBot()
	{
		//TODO: Add Checks for Thread
		shouldRun=false;
	}
	
	protected boolean isAlive()
	{
		return mainThread == null ? false : mainThread.isAlive();
	}

	/***
	 * Fügt eine Methode in die Warteschlange ein
	 */
	protected void methodEnqueue(Method m)
	{
		conn.enque(m);
	}

	/***
	 * New incoming message of any kind — text, photo, sticker, etc.
	 * 
	 * @param msg
	 * @param update
	 */
	protected abstract void onMessage(Message msg, Update update);

	/***
	 * New version of a message that is known to the bot and was edited
	 * 
	 * @param msg
	 * @param update
	 */
	protected abstract void onEditedMessage(Message msg, Update update);

	/***
	 * New version of a message that is known to the bot and was edited
	 * 
	 * @param query
	 * @param update
	 */
	protected abstract void onInlineQuery(InlineQuery query, Update update);

	/***
	 * New version of a message that is known to the bot and was edited
	 * 
	 * @param result
	 * @param update
	 */
	protected abstract void onChosenInlineResult(ChosenInlineResult result, Update update);

	/***
	 * New incoming callback query
	 * 
	 * @param query
	 * @param update
	 */
	protected abstract void onCallbackQuery(CallbackQuery query, Update update);
}
