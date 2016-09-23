/**
 * 
 */
package net.landarzar.telegram.model.types;

import javax.json.JsonObject;

/**
 * @author Kai Sauerwald
 *
 */
public class Chat
{
	public static Chat build(JsonObject chat)
	{
		Chat c = new Chat();

		// ID can be LONG!
		c.id = Long.parseLong(chat.get("id").toString());
		c.type = chat.getString("type");
		if (chat.containsKey("title"))
			c.title = chat.getString("title");
		if (chat.containsKey("username"))
			c.username = chat.getString("username");
		if (chat.containsKey("first_name"))
			c.first_name = chat.getString("first_name");
		if (chat.containsKey("last_name"))
			c.last_name = chat.getString("last_name");

		return c;
	}
	
	/***
	 * Unique identifier for this chat. This number may be greater than 32 bits
	 * and some programming languages may have difficulty/silent defects in
	 * interpreting it. But it smaller than 52 bits, so a signed 64 bit integer
	 * or double-precision float type are safe for storing this identifier.
	 */
	public long id;

	/***
	 * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
	 */
	public String type;

	/***
	 * Optional. Title, for channels and group chats
	 */
	public String title = null;

	/***
	 * Optional. Username, for private chats, supergroups and channels if
	 * available
	 */
	public String username = null;

	/***
	 * Optional. First name of the other party in a private chat
	 */
	public String first_name = null;

	/***
	 * Optional. Last name of the other party in a private chat
	 */
	public String last_name = null;
}
