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
	
	/**
		DTO used when submitting a registration request.
	*/
	
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
	
	/**
		DTO used to return data to the REST client, when
		that client is not authorized to see the returned
		user’s information.
	*/
	
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
	
	/**
		DTO builds upon UserPublic to add additional
		fields visible to authorized users (admins or
		the owner).
	*/
	
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
