package mt.com.vodafone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import mt.com.vodafone.dao.SubscriberRepository;
import mt.com.vodafone.exception.SubscriberNotFoundException;
import mt.com.vodafone.model.Subscriber;

@Service
public class SubscriberService {

	@Autowired
	private SubscriberRepository dao;

	// API
	public Subscriber save(final Subscriber subscriber) throws Throwable {
		return dao.save(subscriber);
	}

	public Subscriber findById(final Integer id) throws Throwable {
		return dao.findById(id).orElseThrow(() -> {
			throw new SubscriberNotFoundException(String.format("A subscriber of id {%d} not found for selecting", id));
		});
	}

	public Iterable<Subscriber> findAll() {
		return dao.findAll();
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public Subscriber merge(final Integer id, final Subscriber newSubscriber) throws Throwable {
		return dao.findById(id).map(subscriber -> {
			subscriber.setServiceType(newSubscriber.getServiceType());
			return dao.save(subscriber);
		}).orElseThrow(() -> {
			throw new SubscriberNotFoundException(String.format("A subscriber of id {%d} not found for updating", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public void delete(final Integer id) throws Throwable {
		dao.findById(id).orElseThrow(() -> {
			throw new SubscriberNotFoundException(String.format("A subscriber of id {%d} not found for deleting", id));
		});
		//Thread.sleep(4000);
		dao.deleteById(id);
	}
}
