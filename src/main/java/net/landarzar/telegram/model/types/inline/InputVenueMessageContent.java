/**
 * 
 */
package net.landarzar.telegram.model.types.inline;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * @author Kai Sauerwald
 *
 */
public class InputVenueMessageContent extends InputMessageContent
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
		builder.add("title", title);
		builder.add("address", address);
		if (foursquare_id != null)
			builder.add("foursquare_id", foursquare_id);

	}

	/***
	 *  	Latitude of the venue in degrees
	 */
	public Float latitude;

	/***
	 * Longitude of the venue in degrees
	 */
	public Float longitude;

	/***
	 *  	Name of the venue
	 */
	public String title;

	/***
	 *  	Address of the venue
	 */
	public String address;

	/***
	 *  	Optional. Foursquare identifier of the venue, if known
	 */
	public String foursquare_id = null;

}
