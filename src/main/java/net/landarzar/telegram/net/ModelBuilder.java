/**
 * 
 */
package net.landarzar.telegram.net;

import javax.json.*;
import javax.json.stream.JsonParser;

import net.landarzar.telegram.model.types.CallbackQuery;
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
			update.content = Message.build(obj.getJsonObject("message"), false);
		if (obj.keySet().contains("edited_message"))
			update.content = Message.build(obj.getJsonObject("edited_message"), true);
		if (obj.keySet().contains("inline_query"))
			update.content = InlineQuery.build(obj.getJsonObject("inline_query"));
		if (obj.keySet().contains("chosen_inline_result"))
			update.content = ChosenInlineResult.build(obj.getJsonObject("chosen_inline_result"));
		if (obj.keySet().contains("callback_query"))
			update.content = CallbackQuery.build(obj.getJsonObject("callback_query"));

		return update;
	}
}
