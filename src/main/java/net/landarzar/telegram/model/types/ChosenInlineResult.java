/**
 * 
 */
package net.landarzar.telegram.model.types;

import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 * @author Kai Sauerwald
 *
 */
public class ChosenInlineResult implements UpdateContent
{
	public static ChosenInlineResult build(JsonObject jsonObject){
		ChosenInlineResult cir = new ChosenInlineResult();

		cir.result_id = jsonObject.getString("result_id");
		cir.from = User.build(jsonObject.getJsonObject("from"));
		cir.query = jsonObject.getString("query");

		if (jsonObject.containsKey("location"))
			cir.location = Location.build(jsonObject.getJsonObject("location"));
		if (jsonObject.containsKey("inline_message_id"))
			cir.inline_message_id = (jsonObject.getString("inline_message_id"));

		return cir;
	}
	
	/***
	 * The unique identifier for the result that was chosen
	 */
	public String result_id;

	/***
	 * The unique identifier for the result that was chosen
	 */
	public User from;

	/***
	 * Optional. Sender location, only for bots that require user location
	 */
	public Location location = null;

	/***
	 * Optional. Identifier of the sent inline message. Available only if there
	 * is an inline keyboard attached to the message. Will be also received in
	 * callback queries and can be used to edit the message.
	 */
	public String inline_message_id = null;

	/***
	 * The query that was used to obtain the result
	 */
	public String query;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.types.UpdateContent#getType()
	 */
	@Override
	public ContentType getContentType()
	{
		// TODO Auto-generated method stub
		return ContentType.CHOSEN_INLINE_RESULT;
	}
}
