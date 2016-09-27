/**
 * 
 */
package net.landarzar.telegram.model.types;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * @author Kai Sauerwald
 *
 */
public class InlineKeyboardButton
{
	/**
	 * 
	 */
	public InlineKeyboardButton()
	{
	}

	/**
	 * 
	 */
	public InlineKeyboardButton(String text)
	{
		this.text = text;
	}

	/**
	 * 
	 */
	public InlineKeyboardButton(String text, String callback)
	{
		this.text = text;
		this.callback_data = callback;
	}
	
	public JsonObject build()
	{

		JsonObjectBuilder builder = Json.createObjectBuilder();

		builder.add("text", text);
		if (url != null)
			builder.add("url", url);
		else if (callback_data != null)
			builder.add("callback_data", callback_data);
		else if (switch_inline_query != null)
			builder.add("switch_inline_query", switch_inline_query);
		
		return builder.build();
	}

	/***
	 * Label text on the button
	 */
	public String text;
	/***
	 * Optional. HTTP url to be opened when button is pressed
	 */
	public String url = null;
	/***
	 * Optional. Data to be sent in a callback query to the bot when button is
	 * pressed, 1-64 bytes
	 */
	public String callback_data = null;
	/***
	 * Optional. If set, pressing the button will prompt the user to select one
	 * of their chats, open that chat and insert the bot‘s username and the
	 * specified inline query in the input field. Can be empty, in which case
	 * just the bot’s username will be inserted.
	 * 
	 * Note: This offers an easy way for users to start using your bot in inline
	 * mode when they are currently in a private chat with it. Especially useful
	 * when combined with switch_pm… actions – in this case the user will be
	 * automatically returned to the chat they switched from, skipping the chat
	 * selection screen.
	 */
	public String switch_inline_query = null;
}
