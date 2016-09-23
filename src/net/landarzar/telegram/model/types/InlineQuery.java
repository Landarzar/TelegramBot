/**
 * 
 */
package net.landarzar.telegram.model.types;

/**
 * @author Kai Sauerwald
 *
 */
public class InlineQuery implements UpdateContent
{
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
	public Type getType()
	{
		// TODO Auto-generated method stub
		return Type.INLINE_QUERY;
	}
}
