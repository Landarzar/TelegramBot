/**
 * 
 */
package net.landarzar.telegram.model.methodes;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.swing.text.IconView;

import net.landarzar.telegram.model.types.Update;
import net.landarzar.telegram.net.ModelBuilder;

/**
 * @author Kai Sauerwald
 *
 */
public class GetUpdates extends MethodWithCallback<List<Update>>
{
	/**
	 * 
	 */
	public GetUpdates(BiConsumer<MethodResult, List<Update>> cb)
	{
		super(cb);
		this.callback = cb;
	}

	/***
	 * Anforderung eines neues update
	 * 
	 * @param os
	 *            der Offset für das Update
	 * @param cb
	 */
	public GetUpdates(int os, BiConsumer<MethodResult, List<Update>> cb)
	{
		super(cb);
		this.offset = os;
		this.callback = cb;
	}

	/***
	 * Identifier of the first update to be returned. Must be greater by one
	 * than the highest among the identifiers of previously received updates. By
	 * default, updates starting with the earliest unconfirmed update are
	 * returned. An update is considered confirmed as soon as getUpdates is
	 * called with an offset higher than its update_id. The negative offset can
	 * be specified to retrieve updates starting from -offset update from the
	 * end of the updates queue. All previous updates will forgotten.
	 */
	public Integer offset = null;

	/***
	 * Optional Limits the number of updates to be retrieved. Values between
	 * 1—100 are accepted. Defaults to 100.
	 */
	public Integer limit = null;

	/***
	 * Timeout in seconds for long polling. Defaults to 0, i.e. usual short
	 * polling
	 */
	public Integer timeout = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#getPath()
	 */
	@Override
	public String getPath()
	{
		// TODO Auto-generated method stub
		return "getUpdates";
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

		if (offset != null)
			ob.add("offset", offset);
		if (limit != null)
			ob.add("limit", limit);
		if (timeout != null)
			ob.add("timeout", timeout);

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
	protected List<Update> constructResult(MethodResult mr)
	{
		if (mr.isOK() && callback != null) {
			LinkedList<Update> list = new LinkedList<>();

			for (JsonValue val : mr.getResult().getJsonArray("result")) {
				JsonObject o = (JsonObject) val;
				list.add(ModelBuilder.buildUpdate(o));

			}

			return list;
		}
		return null;
	}
}
