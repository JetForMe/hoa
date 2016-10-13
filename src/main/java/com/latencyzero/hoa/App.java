package com.latencyzero.hoa;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.inject.Inject;

import io.baratine.config.Config;
import io.baratine.config.Var;
import io.baratine.service.Modify;
import io.baratine.service.Service;
import io.baratine.service.ServiceExceptionNotFound;
import io.baratine.vault.Asset;
import io.baratine.web.Body;
import io.baratine.web.Get;
import io.baratine.web.Header;
import io.baratine.web.HttpStatus;
import io.baratine.web.Path;
import io.baratine.web.Post;
import io.baratine.web.Query;
import io.baratine.web.RequestWeb;
import io.baratine.web.Web;

import org.mindrot.jbcrypt.BCrypt;





public
class
App
{
	public
	static
	void
	main(String[] inArgs)
		throws
			Exception
	{
		System.out.println("Args: " + Arrays.toString(inArgs));
		
		Level level = Level.FINEST;
		
		Logger.getLogger("com.caucho").setLevel(level);
		Logger.getLogger("core").setLevel(level);

		Logger logger = Logger.getLogger("com.latencyzero");
		logger.setLevel(level);
// 		SimpleFormatter sf = new SimpleFormatter();
// 		sf.format = 
// 		logger.getHandlers().forEach(h -> h.setFormatter(new SimpleFormatter("")));
		
		sLogger.info("Starting server");
		
		Web.include(HOAUserSessionImpl.class);
		Web.include(UserVault.class);
		
		Web.start(inArgs);
	}
	



// 	@Get("/users/{id}/")
// 	public
// 	void
// 	getUser(@Path("id") String inUserId,
// 			@Header("Authorization") String inAuth,
// 			RequestWeb inRequest)
// 	{
// 		System.out.println("Get User: " + inUserId + ", token: " + inAuth);
// 		
// 		User user = mUsers.get(inUserId);
// 		if (user == null)
// 		{
// 			inRequest.fail(new ServiceExceptionNotFound("No such user"));
// 		}
// 		
// 		inRequest.ok(user);
// 	}
// 	
// 	@Post("/users")
// 	@Modify
// 	public
// 	void
// 	createUser(@Body RegistrationForm inRegForm,
// 				RequestWeb inRequest)
// 	{
// // 		try
// 		{
// 		System.out.println("createUser: " + inRegForm.login
// 							+ ", first: " + inRegForm.first
// 							+ ", last: " + inRegForm.last);
// 		
// 		int logRounds = 12;
// 		try
// 		{
// 			System.out.println("config: " + mConfig);
// 			logRounds = mConfig.get("bCryptLogRounds", Integer.class, 12);
// 			System.out.println("bcrypt: " + logRounds);
// 			System.out.println("FOO");
// 		}
// 		
// 		catch (Throwable e)
// 		{
// 			inRequest.fail(e);
// 			return;
// 		}
// 		
// 		System.out.println("mUsers: " + mUsers);
// 		User user = mUsers.get(inRegForm.login);
// 		if (user != null)
// 		{
// 			inRequest.status(HttpStatus.CONFLICT);
// 			inRequest.ok("{\"result\":\"User exists\"}");
// 			return;
// 		}
// 		
// 		user = new User();
// 		user.login = inRegForm.login;
// 		user.first = inRegForm.first;
// 		user.last = inRegForm.last;
// 		user.email = inRegForm.email;
// 		
// 		
// 		String hashed = BCrypt.hashpw(inRegForm.password, BCrypt.gensalt(logRounds));	//	TODO: configurable parameter
// 		user.hashedPassword = hashed;
// 		
// 		mUsers.put(user.login, user);
// 		
// 		inRequest.status(HttpStatus.CREATED);
// 		inRequest.ok(user);
// 		}
// 		
// // 		catch (Throwable e)
// // 		{
// // 			inRequest.fail(e);
// // 		}
// 	}
// 	
// 	@Post("/users/{id}/login")
// 	@Modify
// 	public
// 	void
// 	login(@Path("id") String inUserId,
// 			@Body LoginForm inLoginForm,
// 			RequestWeb inRequest)
// 	{
// 		System.out.println("Login user: " + inUserId + ", pw: " + inLoginForm.password);
// 		
// 		User user = mUsers.get(inUserId);
// 		if (user != null && BCrypt.checkpw(inLoginForm.password, user.hashedPassword))
// 		{
// 			user.lastLogin = new Date();
// 			inRequest.ok(user);
// 		}
// 		else
// 		{
// 			inRequest.status(HttpStatus.UNAUTHORIZED);
// 			inRequest.ok("Incorrect login/password");
// 		}
// 	}
// 	
// 	HashMap<String, User>			mUsers = new HashMap<String, User>();
// 	
// 	@Inject
// 	//@Var("config")
// 	Config							mConfig;
// 	
// 	static
// 	public
// 	class
// 	RegistrationForm
// 	{
// 		String			login;
// 		String			password;
// 		String			first;
// 		String			last;
// 		String			email;
// 	}
// 	
// 	static
// 	public
// 	class
// 	LoginForm
// 	{
// 		String			login;
// 		String			password;
// 	}
	
// 	static
// 	public
// 	class
// 	Config
// 	{
// 		int				bCryptLogRounds;
// 	}
	
	private static final Logger		sLogger		=	Logger.getLogger(App.class.getName());
}
