/**
 * @author filipe.pinheiro, 29/09/2018
 */
package mt.com.vodafone.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mt.com.vodafone.model.Subscriber;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Integer>, SubscriberCustomRepository {
	
}
