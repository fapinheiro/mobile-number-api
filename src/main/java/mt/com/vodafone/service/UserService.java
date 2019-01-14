package mt.com.vodafone.service;

import mt.com.vodafone.entity.User;

public interface UserService {

	public User save(final User user) throws Throwable;

	public Iterable<User> findAll();

}
