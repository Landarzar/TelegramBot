/**
 * 
 */
package net.landarzar.telegram.model.types;

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
			type = Type.EDIT_MESSAGE;
		else
			type = Type.MESSAGE;
	}

	public Type type;

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
	public Type getType()
	{
		return type;
	}
}
