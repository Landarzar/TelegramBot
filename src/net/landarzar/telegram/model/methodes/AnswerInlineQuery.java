/**
 * 
 */
package net.landarzar.telegram.model.methodes;

import java.util.LinkedList;
import java.util.function.BiConsumer;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import net.landarzar.telegram.model.types.inline.InlineQueryResult;

/**
 * @author Kai Sauerwald
 *
 */
public class AnswerInlineQuery extends MethodWithCallback<Boolean>
{
	/**
	 * 
	 */
	public AnswerInlineQuery(BiConsumer<MethodResult, Boolean> cb)
	{
		super(cb);
	}

	/***
	 * Antwort auf ein InlineQuery
	 * 
	 * @param os
	 *            der Offset für das Update
	 * @param cb
	 */
	public AnswerInlineQuery(String inline_query_id, BiConsumer<MethodResult, Boolean> cb)
	{
		super(cb);
	}

	/***
	 * Unique identifier for the answered query
	 */
	public String inline_query_id;

	/***
	 * A JSON-serialized array of results for the inline query
	 */
	public LinkedList<InlineQueryResult> results = new LinkedList<>();

	/***
	 * Optional. The maximum amount of time in seconds that the result of the
	 * inline query may be cached on the server. Defaults to 300.
	 */
	public Integer cache_time = null;

	/***
	 * Optional. Pass True, if results may be cached on the server side only for
	 * the user that sent the query. By default, results may be returned to any
	 * user who sends the same query
	 */
	public Boolean is_personal = null;

	/***
	 * Optional. Pass the offset that a client should send in the next query
	 * with the same text to receive more results. Pass an empty string if there
	 * are no more results or if you don‘t support pagination. Offset length
	 * can’t exceed 64 bytes.
	 */
	public String next_offset = null;

	/***
	 * Optional. If passed, clients will display a button with specified text
	 * that switches the user to a private chat with the bot and sends the bot a
	 * start message with the parameter switch_pm_parameter
	 */
	public String switch_pm_text = null;

	/***
	 * Optional. Parameter for the start message sent to the bot when user
	 * presses the switch button
	 * 
	 * Example: An inline bot that sends YouTube videos can ask the user to
	 * connect the bot to their YouTube account to adapt search results
	 * accordingly. To do this, it displays a ‘Connect your YouTube account’
	 * button above the results, or even before showing any. The user presses
	 * the button, switches to a private chat with the bot and, in doing so,
	 * passes a start parameter that instructs the bot to return an oauth link.
	 * Once done, the bot can offer a switch_inline button so that the user can
	 * easily return to the chat where they wanted to use the bot's inline
	 * capabilities.
	 */
	public String switch_pm_parameter = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#getPath()
	 */
	@Override
	public String getPath()
	{
		// TODO Auto-generated method stub
		return "answerInlineQuery";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#buildMethod()
	 */
	@Override
	public String buildMethod()
	{
		JsonObjectBuilder ob = Json.createObjectBuilder();

		ob.add("inline_query_id", inline_query_id);

//		ob.add("results", Json.createArrayBuilder()
//				.add(Json.createObjectBuilder().add("type", "gif").add("id", "124").add("gif_url", "https://media.giphy.com/media/vLtxIwE5B8iyc/giphy.gif")
//				// .add("thumb_url",
//				// "https://media.giphy.com/media/vLtxIwE5B8iyc/giphy.gif")
//				).add(Json.createObjectBuilder().add("type", "gif").add("id", "124").add("gif_url", "https://media.giphy.com/media/vLtxIwE5B8iyc/giphy.gif")).build());

		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (InlineQueryResult iqr : results) {
			builder.add(iqr.build());
		}
		ob.add("results", builder.add(Json.createObjectBuilder().add("type", "gif").add("id", "124").add("gif_url", "https://media.giphy.com/media/vLtxIwE5B8iyc/giphy.gif").add("thumb_url", "https://media.giphy.com/media/vLtxIwE5B8iyc/giphy.gif"))
				.build());

		// ob.add("results", Json.createArrayBuilder().build());

		if (cache_time != null)
			ob.add("cache_time", cache_time);
		if (is_personal != null)
			ob.add("is_personal", is_personal);
		if (next_offset != null)
			ob.add("next_offset", next_offset);
		if (switch_pm_text != null)
			ob.add("switch_pm_text", switch_pm_text);
		if (switch_pm_parameter != null)
			ob.add("switch_pm_parameter", switch_pm_parameter);

		return ob.build().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.landarzar.telegram.model.methodes.MethodWithCallback#constructResult(
	 * java.io.BufferedReader)
	 */
	@Override
	protected Boolean constructResult(MethodResult mr)
	{
		return mr.isok;
	}
}
