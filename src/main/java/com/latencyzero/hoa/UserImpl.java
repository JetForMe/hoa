package com.latencyzero.hoa;




import java.util.Date;
import java.util.logging.Logger;

import io.baratine.service.Api;
import io.baratine.service.Modify;
import io.baratine.service.Result;
import io.baratine.vault.Asset;
import io.baratine.vault.Id;
import io.baratine.vault.IdAsset;

import org.mindrot.jbcrypt.BCrypt;





public
class
UserImpl implements User
{
	public
	UserImpl()
	{
		sLogger.info("UserImpl() called");
	}
	
	@Override
	@Modify
	public
	void
	create(HOASession.UserRegistrationForm inForm,
			Result<IdAsset> ioResult)
	{
		sLogger.info("UserImpl.create() called");
		
		//	TODO: throw exception on login collision
		
		login = inForm.login;
		first = inForm.first;
		last = inForm.last;
		email = inForm.email;
		createDate = new Date();
		
		String bcp = BCrypt.hashpw(inForm.password, BCrypt.gensalt(12));	//	TODO: configurable parameter
		encryptedPassword = bcp;
		
		ioResult.ok(id);
	}
	
	@Override
	public
	void
	get(Result<Data> ioResult)
	{
		sLogger.info("UserImpl.get() called");
		
		Data user = new Data();
		user.id = id.toString();
		user.first = first;
		user.last = last;
		
		ioResult.ok(user);
	}
	
	@Id
	private IdAsset 			id;
	private	String				first;
	private	String				last;
	private	String				email;
	private	String				login;
	private	String				encryptedPassword;
	
	private	Date				createDate;

	private static final Logger		sLogger		=	Logger.getLogger(UserImpl.class.getName());
}
