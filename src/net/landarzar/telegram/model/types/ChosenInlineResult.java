/**
 * 
 */
package net.landarzar.telegram.model.types;

/**
 * @author Kai Sauerwald
 *
 */
public class ChosenInlineResult implements UpdateContent
{
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
	public Type getType()
	{
		// TODO Auto-generated method stub
		return Type.CHOSEN_INLINE_RESULT;
	}

}
