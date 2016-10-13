package com.latencyzero.hoa;


import java.util.logging.Logger;

import javax.inject.Inject;

import io.baratine.service.OnInit;
import io.baratine.service.Result;
import io.baratine.service.Service;
import io.baratine.service.Services;
import io.baratine.vault.Id;
import io.baratine.web.Body;
import io.baratine.web.Post;



public
class
AbstractHOASession implements HOASession
{
	@OnInit
	public
	void
	init()
	{
		sLogger.info("AbstractHOASession.init()");
	}
	
	@Post("/register")
	public
	void
	registerUser(@Body UserRegistrationForm inUser,
					Result<UserPublic> ioResult)
	{
		sLogger.info("AbstractHOASession.registerUser()");
		
		mUsers.create(inUser,
						ioResult.then((id, r) ->
							getUserService(id.toString()).get(r.then(u -> UserPublic.of(u)))));
	}

	public
	User
	getUserService(String inId)
	{
		return mManager.service(User.class, inId);
	}



	@Id
	String										mID;
	
	@Inject
	protected	Services						mManager;
	
	@Inject
	@Service("/User")
	private		UserAbstractVault<User>			mUsers;



	private static final Logger		sLogger		=	Logger.getLogger(UserImpl.class.getName());
}
