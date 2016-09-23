/**
 * 
 */
package net.landarzar.telegram.model.types.inline;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * @author kai
 *
 */
public class InputContactMessageContent extends InputMessageContent
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.landarzar.telegram.model.types.inline.InputMessageContent#build()
	 */
	@Override
	public JsonObject build()
	{
		JsonObjectBuilder builder = Json.createObjectBuilder();

		builder.add("phone_number", phone_number);
		builder.add("first_name", first_name);
		if (last_name != null)
			builder.add("last_name", last_name);
		
		return builder.build();
	}
	/***
	 *  	Contact's phone number
	 */
	public String phone_number;

	/***
	 *  	Contact's first name
	 */
	public String first_name;
	
	/***
	 * Optional. Contact's last name
	 */
	public String last_name = null;
}
