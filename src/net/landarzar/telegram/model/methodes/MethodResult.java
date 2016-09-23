/**
 * 
 */
package net.landarzar.telegram.model.methodes;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Repräsentiert das Resultat einer Anfrage
 * 
 * @author Kai Sauerwald
 */
public class MethodResult
{
	public MethodResult(Method m, InputStream is)
	{
		method = m;
		
		JsonReader reader = Json.createReader(is);
		jsonResult = reader.readObject();
		isok = jsonResult.getBoolean("ok");
	}

	public Method method;
	public boolean isok;
	public int error_code;
	public JsonObject jsonResult;

	public boolean isOK()
	{
		return isok;
	}

	public int getErrorCode()
	{
		if (isok)
			return -1;

		return jsonResult.getInt("error_code");
	}

	public String getErrorDescrition()
	{
		if (isok)
			return "";

		return jsonResult.getString("description");
	}
	
	/***
	 * Die komplette Serverantwort
	 * @return
	 */
	public JsonObject getResult(){
		return jsonResult;
	}

	/***
	 * Der eigentliche Inhalt der Nachricht
	 * @return
	 */
	public JsonObject getContent()
	{
		if (isok && jsonResult != null && jsonResult.containsKey("result"))
			return jsonResult.getJsonObject("result");

		return null;
	}
}
