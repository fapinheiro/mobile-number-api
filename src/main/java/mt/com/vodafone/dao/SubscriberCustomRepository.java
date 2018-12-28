package mt.com.vodafone.dao;

import java.util.Optional;

import mt.com.vodafone.model.Subscriber;

public interface SubscriberCustomRepository {

	Optional<Subscriber> findById2(Integer id);
	
}
