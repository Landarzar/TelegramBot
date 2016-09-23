/**
 * 
 */
package net.landarzar.telegram.net;

import javax.json.*;
import javax.json.stream.JsonParser;

import net.landarzar.telegram.model.types.Chat;
import net.landarzar.telegram.model.types.Message;
import net.landarzar.telegram.model.types.Update;
import net.landarzar.telegram.model.types.UpdateContent;
import net.landarzar.telegram.model.types.User;

/**
 * @author Kai Sauerwald Builds from a JsonObject
 */
public class ModelBuilder
{
	public static Update buildUpdate(JsonObject obj)
	{
		Update update = new Update();

		update.update_id = obj.getInt("update_id");
		if (obj.keySet().contains("message"))
			update.content = buildMessage(obj.getJsonObject("message"), false);
		if (obj.keySet().contains("edited_message"))
			update.content = buildMessage(obj.getJsonObject("edited_message"), true);
		if (obj.keySet().contains("inline_query"))
			update.content = buildInlineQuery(obj.getJsonObject("inline_query"));
		if (obj.keySet().contains("chosen_inline_result"))
			update.content = buildChosenInlineResult(obj.getJsonObject("chosen_inline_result"));
		if (obj.keySet().contains("callback_query"))
			update.content = buildCallbackQuery(obj.getJsonObject("callback_query"));

		return update;
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	public static UpdateContent buildCallbackQuery(JsonObject jsonObject)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	public static UpdateContent buildChosenInlineResult(JsonObject jsonObject)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	public static UpdateContent buildInlineQuery(JsonObject jsonObject)
	{
		throw new UnsupportedOperationException();
	}

	public static Message buildMessage(JsonObject msg, boolean isEdited)
	{
		Message m = new Message(isEdited);

		m.message_id = msg.getInt("message_id");
		m.date = msg.getInt("date");
		if (msg.containsKey("from"))
			m.from = buildUser(msg.getJsonObject("from"));
		if (msg.containsKey("chat"))
			m.chat = buildChat(msg.getJsonObject("chat"));
		if (msg.containsKey("text"))
			m.text = (msg.getString("text"));

		return m;
	}

	public static User buildUser(JsonObject usr)
	{
		User u = new User();

		u.id = usr.getInt("id");
		u.first_name = usr.getString("first_name");
		if (usr.containsKey("last_name"))
			u.last_name = usr.getString("last_name");
		if (usr.containsKey("username"))
			u.username = usr.getString("username");

		return u;
	}

	public static Chat buildChat(JsonObject chat)
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
}
