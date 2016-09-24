/**
 * 
 */
package net.landarzar.telegram.model.types;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * @author Kai Sauerwald
 *
 */
public class CallbackQuery implements UpdateContent
{

	/**
	 * @param jsonObject
	 * @return
	 */
	public static UpdateContent build(JsonObject jsonObject)
	{
		CallbackQuery uc = new CallbackQuery();
		uc.id = jsonObject.getString("id");
		uc.from = User.build(jsonObject.getJsonObject("from"));
		uc.data = jsonObject.getString("data");
		if (jsonObject.containsKey("message"))
			uc.message = Message.build(jsonObject.getJsonObject("message"), false);
		if (jsonObject.containsKey("inline_message_id"))
			uc.inline_message_id = jsonObject.getString("inline_message_id");

		return uc;
	}

	/***
	 * JsonObjectBuilder builder
	 */
	public String id;

	/***
	 * Sender
	 */
	public User from;

	/***
	 * Optional. Message with the callback button that originated the query.
	 * Note that message content and message date will not be available if the
	 * message is too old
	 */
	public Message message = null;

	/***
	 * Optional. Identifier of the message sent via the bot in inline mode, that
	 * originated the query
	 */
	public String inline_message_id = null;

	/***
	 * Data associated with the callback button. Be aware that a bad client can
	 * send arbitrary data in this field
	 */
	public String data;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.types.UpdateContent#getContentType()
	 */
	@Override
	public ContentType getContentType()
	{
		// TODO Auto-generated method stub
		return ContentType.CALLBACK_QUERY;
	}

}
