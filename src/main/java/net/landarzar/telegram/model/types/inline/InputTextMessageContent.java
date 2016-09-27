/**
 * 
 */
package net.landarzar.telegram.model.types.inline;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * @author kai
 *
 */
public class InputTextMessageContent extends InputMessageContent
{
	/**
	 * 
	 */
	public InputTextMessageContent()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public InputTextMessageContent(String text)
	{
		message_text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.landarzar.telegram.model.types.inline.InputMessageContent#build()
	 */
	@Override
	public void build(JsonObjectBuilder builder)
	{

		builder.add("message_text", message_text);
		if (parse_mode != null)
			builder.add("parse_mode", parse_mode);
		if (disable_web_page_preview != null)
			builder.add("disable_web_page_preview", disable_web_page_preview);
	}

	/***
	 * Text of the message to be sent, 1-4096 characters
	 */
	public String message_text;

	/***
	 * Optional. Send Markdown or HTML, if you want Telegram apps to show bold,
	 * italic, fixed-width text or inline URLs in your bot's message.
	 */
	public String parse_mode = null;

	/***
	 * Optional. Disables link previews for links in the sent message
	 */
	public Boolean disable_web_page_preview = null;
}
