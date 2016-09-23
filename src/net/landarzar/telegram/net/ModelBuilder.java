/**
 * 
 */
package net.landarzar.telegram.net;

import javax.json.*;
import javax.json.stream.JsonParser;

import net.landarzar.telegram.model.types.Chat;
import net.landarzar.telegram.model.types.ChosenInlineResult;
import net.landarzar.telegram.model.types.InlineQuery;
import net.landarzar.telegram.model.types.Location;
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
		throw new UnsupportedOperationException(jsonObject.toString());
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	public static ChosenInlineResult buildChosenInlineResult(JsonObject jsonObject)
	{
		ChosenInlineResult cir = new ChosenInlineResult();

		cir.result_id = jsonObject.getString("result_id");
		cir.from = buildUser(jsonObject.getJsonObject("from"));
		cir.query = jsonObject.getString("query");

		if (jsonObject.containsKey("location"))
			cir.location = buildLocation(jsonObject.getJsonObject("location"));
		if (jsonObject.containsKey("inline_message_id"))
			cir.inline_message_id = (jsonObject.getString("inline_message_id"));

		return cir;
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	private static Location buildLocation(JsonObject jsonObject)
	{
		throw new UnsupportedOperationException(jsonObject.toString());
	}

	/**
	 * @param jsonObject
	 * @return
	 */
	public static InlineQuery buildInlineQuery(JsonObject jsonObject)
	{
		InlineQuery ir = new InlineQuery();

		ir.id = jsonObject.getString("id");
		ir.from = buildUser(jsonObject.getJsonObject("from"));
		ir.query = jsonObject.getString("query");
		ir.offset = (jsonObject.getString("offset"));

		if (jsonObject.containsKey("location"))
			ir.location = buildLocation(jsonObject.getJsonObject("location"));

		return ir;
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
