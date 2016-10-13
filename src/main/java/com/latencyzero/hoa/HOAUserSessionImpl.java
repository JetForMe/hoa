package com.latencyzero.hoa;




import java.util.logging.Logger;


import io.baratine.service.OnInit;
import io.baratine.service.Result;
import io.baratine.service.Service;
import io.baratine.web.Body;
import io.baratine.web.Path;
import io.baratine.web.Post;
import io.baratine.web.cors.CrossOrigin;




@Service("session:///user")
@CrossOrigin(value = "*", allowCredentials = true)
@Path("/user")
public
class
HOAUserSessionImpl
	extends AbstractHOASession
	implements HOAUserSession
{
	@OnInit
	public
	void
	init()
	{
		sLogger.info("HOAUserSessionImpl.init()");
	}
	
	private static final Logger		sLogger		=	Logger.getLogger(HOAUserSessionImpl.class.getName());
}
