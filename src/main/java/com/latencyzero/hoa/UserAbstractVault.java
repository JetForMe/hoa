package com.latencyzero.hoa;



import io.baratine.service.Result;
import io.baratine.service.Service;
import io.baratine.vault.IdAsset;
import io.baratine.vault.Vault;

@Service("/User")
public
interface
UserAbstractVault<X extends User> extends Vault<IdAsset, X>
{
	void
	create(HOASession.UserRegistrationForm inForm,
			  Result<IdAsset> ioResult);

	void
	findByLogin(String inLogin, Result<User> ioResult);
}
