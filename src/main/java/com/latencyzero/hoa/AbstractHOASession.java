package com.latencyzero.hoa;


import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.baratine.service.OnInit;
import io.baratine.service.Result;
import io.baratine.service.Service;
import io.baratine.service.Services;
import io.baratine.vault.Id;
import io.baratine.web.Body;
import io.baratine.web.Get;
import io.baratine.web.Path;
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
		sLogger.info("AbstractHOASession.init(). ID: " + mID);
	}
	
	@Post("/register")
	public
	void
	registerUser(@Body UserRegistrationForm inUser,
					Result<UserPublic> ioResult)
	{
		sLogger.info("AbstractHOASession.registerUser(). ID: " + mID);
		
		mUsers.create(inUser,
						ioResult.then((id, r) ->
							{
								sLogger.info("Created user: " + id.toString());
								
								//	Add the login to the set of unavailable logins…
								
								mLogins.add(inUser.login);
								
								//	Get the user that was just created…
								
								User user = getUserService(id.toString());
								user.get(r.then(u ->
									{
										sLogger.info("Fetched user: " + u);
										return new UserPublic(u);
									}
									));
							}));
	}

	@Get("/{login}/available")
	public
	void
	checkAvailability(@Path("login") String inLogin,
						Result<AvailabilityResponse> ioResult)
	{
		sLogger.info("AbstractHOASession.checkAvailability(): " + inLogin);
		
		//	If we have it in our set, return false…
		
		AvailabilityResponse resp = new AvailabilityResponse();
		resp.login = inLogin;
		
		if (mLogins.contains(inLogin))
		{
			resp.available = false;
			ioResult.ok(resp);
			return;
		}
		
		//	If it's not in our set, then look it up…
		
		mUsers.findByLogin(inLogin,
							ioResult.then((u, r) ->
								{
// 									sLogger.info("find result then (" + u);
// 									sLogger.info("find result then (" + u.getClass().getName()
// 													+ ", " + r.getClass().getName());
// 									r.ok(u == null);
									resp.available = u == null;
									r.ok(resp);
								}));
	}
	
	public
	static
	class
	AvailabilityResponse
	{
		String			login;
		Boolean			available;
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
	protected Services							mManager;
	
	@Inject
	@Service("/User")
	private UserAbstractVault<User>				mUsers;

	Set<String>									mLogins			=	new HashSet<String>();

	private static final Logger		sLogger		=	Logger.getLogger(UserImpl.class.getName());
}
