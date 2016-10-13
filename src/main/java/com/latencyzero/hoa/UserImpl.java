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
		mFirst = inForm.first;
		mLast = inForm.last;
		mEmail = inForm.email;
		mCreateDate = new Date();
		
		String bcp = BCrypt.hashpw(inForm.password, BCrypt.gensalt(12));	//	TODO: configurable parameter
		mEncryptedPassword = bcp;
		
		ioResult.ok(mId);
	}
	
	@Override
	public
	void
	get(Result<Data> ioResult)
	{
		sLogger.info("UserImpl.get() called");
		
		Data user = new Data();
		user.id = mId.toString();
		user.first = mFirst;
		user.last = mLast;
		
		ioResult.ok(user);
	}
	
	@Id
	private IdAsset 			mId;
	private	String				mFirst;
	private	String				mLast;
	private	String				mEmail;
	private	String				login;
	private	String				mEncryptedPassword;
	
	private	Date				mCreateDate;

	private static final Logger		sLogger		=	Logger.getLogger(UserImpl.class.getName());
}
