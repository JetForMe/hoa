package com.latencyzero.hoa;





import io.baratine.service.Result;
import io.baratine.web.Body;





public
interface
HOASession
{
	void
	registerUser(@Body UserRegistrationForm inUser,
					Result<UserPublic> inResult);
	
	static
	class
	UserRegistrationForm
	{
		String			login;
		String			password;
		String			first;
		String			last;
		String			email;
	}
	
	static
	class
	UserPublic
	{
		private	String			id;
		private	String			first;
		private	String			last;
		
		public
		UserPublic(User.Data inUser)
		{
			id = inUser.id;
			first = inUser.first;
			last = inUser.last;
		}
		
		public
		static
		UserPublic
		of(User.Data inUser)
		{
			UserPublic up = new UserPublic(inUser);
			return up;
		}
	}
	
	static
	class
	UserPrivate extends UserPublic
	{
		public
		UserPrivate(User.Data inUser)
		{
			super(inUser);
			
			login = inUser.login;
			email = inUser.email;
		}
		
		private	String			login;
		private	String			email;
	}
}
