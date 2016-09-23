/**
 * 
 */
package net.landarzar.telegram.model.methodes;

import java.io.BufferedReader;
import java.util.List;
import java.util.function.BiConsumer;

import net.landarzar.telegram.model.types.Update;

/**
 * @author Kai Sauerwald
 *
 */
public abstract class MethodWithCallback<T> implements Method
{

	/**
	 * 
	 */
	public MethodWithCallback(BiConsumer<MethodResult, T> bc)
	{
		this.callback = bc;
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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#buildMethod()
	 */
	@Override
	public abstract String buildMethod();
	/***
	 * Funktion die das Ergebnis der Anfrage enthält
	 */
	protected BiConsumer<MethodResult, T> callback = null;

	/***
	 * Diese Funktion konstruiert das Resultat für den Callback
	 * 
	 * @param in
	 * @return
	 */
	protected abstract T constructResult(MethodResult in);

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#afterPerform(java.io.
	 * BufferedReader)
	 */
	@Override
	public void afterPerform(MethodResult in)
	{
		T out = constructResult(in);
		if (callback != null) {
			callback.accept(in, out);
		}
	}

}
