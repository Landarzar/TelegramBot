/**
 * 
 */
package net.landarzar.telegram.model.types;

import javax.json.JsonObject;

/**
 * @author Kai Sauerwald
 *
 */
public class Message implements UpdateContent
{
	/***
	 * Defaulttype is {@value Type.MESSAGE}
	 */
	public Message()
	{
		this(false);
	}

	public Message(boolean isEdited)
	{
		if (isEdited)
			type = ContentType.EDIT_MESSAGE;
		else
			type = ContentType.MESSAGE;
	}
	
	public static Message build(JsonObject msg, boolean isEdited)
	{
		Message m = new Message(isEdited);

		m.message_id = msg.getInt("message_id");
		m.date = msg.getInt("date");
		if (msg.containsKey("from"))
			m.from = User.build(msg.getJsonObject("from"));
		if (msg.containsKey("chat"))
			m.chat = Chat.build(msg.getJsonObject("chat"));
		if (msg.containsKey("text"))
			m.text = (msg.getString("text"));

		return m;
	}

	public ContentType type;

	/***
	 * Unique message identifier
	 */
	public int message_id;

	/***
	 * Date the message was sent in Unix time
	 */
	public int date;

	/***
	 * Conversation the message belongs to
	 */
	public Chat chat = null;

	/* Optionale */

	/***
	 * Optional. Sender, can be empty for messages sent to channels
	 */
	public User from = null;

	/***
	 * Optional. For text messages, the actual UTF-8 text of the message, 0-4096
	 * characters.
	 */
	public String text = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.types.UpdateContent#getType()
	 */
	@Override
	public ContentType getContentType()
	{
		return type;
	}
}
