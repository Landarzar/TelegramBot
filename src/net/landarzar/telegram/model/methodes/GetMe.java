/**
 * 
 */
package net.landarzar.telegram.model.methodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.BiConsumer;

import javax.json.JsonObject;

/**
 * @author Kai Sauerwald
 *
 */
public class GetMe extends MethodWithCallback<JsonObject>
{

	/**
	 * @param bc
	 */
	public GetMe(BiConsumer<MethodResult, JsonObject> bc)
	{
		super(bc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#getPath()
	 */
	@Override
	public String getPath()
	{
		// TODO Auto-generated method stub
		return "getMe";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#buildMethod()
	 */
	@Override
	public String buildMethod()
	{
		// TODO Auto-generated method stub
		return "";
	}

	/* (non-Javadoc)
	 * @see net.landarzar.telegram.model.methodes.MethodWithCallback#constructResult(net.landarzar.telegram.model.methodes.MethodResult)
	 */
	@Override
	protected JsonObject constructResult(MethodResult mr)
	{
		return mr.getContent();
	}

}
