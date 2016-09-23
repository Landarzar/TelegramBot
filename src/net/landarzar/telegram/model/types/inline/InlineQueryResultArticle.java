/**
 * 
 */
package net.landarzar.telegram.model.types.inline;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import net.landarzar.telegram.model.types.InlineKeyboardMarkup;

/**
 * @author Kai Sauerwald
 *
 */
public class InlineQueryResultArticle extends InlineQueryResult
{
	/**
	 * 
	 */
	public InlineQueryResultArticle()
	{
		type = "article";
	}

	public JsonObject build()
	{
		JsonObjectBuilder builder = Json.createObjectBuilder();

		builder.add("type", type);
		builder.add("id", id);
		builder.add("title", title);
		builder.add("input_message_content", input_message_content.build());
		if (reply_markup != null)
			builder.add("reply_markup", reply_markup.build());
		if (url != null)
			builder.add("url", url);
		if (hide_url != null)
			builder.add("hide_url", hide_url);
		if (description != null)
			builder.add("description", description);
		if (thumb_url != null)
			builder.add("thumb_url", thumb_url);
		if (thumb_width != null)
			builder.add("thumb_width", thumb_width);
		if (thumb_height != null)
			builder.add("thumb_height", thumb_height);
		return null;
	}

	/***
	 * Title of the result
	 */
	public String title;

	/***
	 * Content of the message to be sent
	 */
	public InputMessageContent input_message_content;

	/***
	 * Optional. Inline keyboard attached to the message
	 */
	public InlineKeyboardMarkup reply_markup = null;

	/***
	 * Optional. URL of the result
	 */
	public String url;

	/***
	 * Optional. Pass True, if you don't want the URL to be shown in the message
	 */
	public Boolean hide_url = null;

	/***
	 * Optional. Short description of the result
	 */
	public String description = null;

	/***
	 * Optional. Url of the thumbnail for the result
	 */
	public String thumb_url = null;

	/***
	 * Optional. Thumbnail width
	 */
	public Integer thumb_width = null;

	/***
	 * Optional. Thumbnail height
	 */
	public Integer thumb_height = null;
}
