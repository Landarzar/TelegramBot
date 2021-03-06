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
public class InputLocationMessageContent extends InputMessageContent
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.landarzar.telegram.model.types.inline.InputMessageContent#build()
	 */
	@Override
	public void build(JsonObjectBuilder builder)
	{
		builder.add("latitude", latitude);
		builder.add("longitude", longitude);
	}


	/***
	 *  	Latitude of the venue in degrees
	 */
	public Float latitude;

	/***
	 * Longitude of the venue in degrees
	 */
	public Float longitude;

}
