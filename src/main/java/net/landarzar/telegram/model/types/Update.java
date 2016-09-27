/**
 * 
 */
package net.landarzar.telegram.model.types;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kai Sauerwald
 * This object represents an incoming update.
 */
public class Update
{
	/***
	 * The update‘s unique identifier. Update identifiers start from a certain
	 * positive number and increase sequentially. This ID becomes especially
	 * handy if you’re using Webhooks, since it allows you to ignore repeated
	 * updates or to restore the correct update sequence, should they get out of
	 * order.
	 */
	public int update_id;
	/***
	 * The content of this update. There are 5 possible types of updates:
	 *  - message 
	 *  - edited_message
	 *  - inline_query
	 *  - chosen_inline_query
	 *  - callback_query
	 */
	public UpdateContent content = null;
}
