/**
 * 
 */
package net.landarzar.telegram.model.types;

import javax.json.JsonObject;

/**
 * @author Kai Sauerwald
 *
 */
public class InlineQuery implements UpdateContent
{
	/**
	 * @param jsonObject
	 * @return
	 */
	public static InlineQuery build(JsonObject jsonObject)
	{
		InlineQuery ir = new InlineQuery();

		ir.id = jsonObject.getString("id");
		ir.from = User.build(jsonObject.getJsonObject("from"));
		ir.query = jsonObject.getString("query");
		ir.offset = (jsonObject.getString("offset"));

		if (jsonObject.containsKey("location"))
			ir.location = Location.build(jsonObject.getJsonObject("location"));

		return ir;
	}
	
	/***
	 * The unique identifier for the result that was chosen
	 */
	public String id;

	/***
	 * The unique identifier for the result that was chosen
	 */
	public User from;

	/***
	 * Optional. Sender location, only for bots that require user location
	 */
	public Location location = null;


	/***
	 * The query that was used to obtain the result
	 */
	public String query;

	/***
	 * Offset of the results to be returned, can be controlled by the bot
	 */
	public String offset = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.types.UpdateContent#getType()
	 */
	@Override
	public ContentType getContentType()
	{
		// TODO Auto-generated method stub
		return ContentType.INLINE_QUERY;
	}
}
