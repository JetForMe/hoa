package com.latencyzero.hoa;



import java.util.Date;

import io.baratine.service.Api;
import io.baratine.service.Modify;
import io.baratine.service.Result;
import io.baratine.vault.Asset;
import io.baratine.vault.IdAsset;



@Asset
@Api
public
interface
User
{
	@Modify
	void
	create(HOASession.UserRegistrationForm inForm,
			Result<IdAsset> inUserId);
			
	void
	get(Result<Data> ioResult);
	
	
	public
	static
	class
	Data
	{
		String			id;
		String			first;
		String			last;
		String			email;
		String			login;
	}
}
