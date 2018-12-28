/**
 * @author filipe.pinheiro, 29/09/2018
 */
package mt.com.vodafone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import mt.com.vodafone.type.SERVICE_TYPE;


@Entity(name="subscriber")
@SequenceGenerator(sequenceName="SEQ_SUBSCRIBER", name = "SEQ_SUBSCRIBER")
public class Subscriber {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_SUBSCRIBER")
	private Integer id;
	private String msisdn;
	private Integer customerIdOwner;
	private Integer customerIdUser;
	private SERVICE_TYPE serviceType;
	private Long serviceStartDate;
	
	// Getter and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Integer getCustomerIdOwner() {
		return customerIdOwner;
	}
	public void setCustomerIdOwner(Integer customerIdOwner) {
		this.customerIdOwner = customerIdOwner;
	}
	public Integer getCustomerIdUser() {
		return customerIdUser;
	}
	public void setCustomerIdUser(Integer customerIdUser) {
		this.customerIdUser = customerIdUser;
	}
	public SERVICE_TYPE getServiceType() {
		return serviceType;
	}
	public void setServiceType(SERVICE_TYPE serviceType) {
		this.serviceType = serviceType;
	}
	public Long getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(Long serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	
	// Equals and Hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerIdOwner == null) ? 0 : customerIdOwner.hashCode());
		result = prime * result + ((customerIdUser == null) ? 0 : customerIdUser.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
		result = prime * result + ((serviceStartDate == null) ? 0 : serviceStartDate.hashCode());
		result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscriber other = (Subscriber) obj;
		if (customerIdOwner == null) {
			if (other.customerIdOwner != null)
				return false;
		} else if (!customerIdOwner.equals(other.customerIdOwner))
			return false;
		if (customerIdUser == null) {
			if (other.customerIdUser != null)
				return false;
		} else if (!customerIdUser.equals(other.customerIdUser))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (msisdn == null) {
			if (other.msisdn != null)
				return false;
		} else if (!msisdn.equals(other.msisdn))
			return false;
		if (serviceStartDate == null) {
			if (other.serviceStartDate != null)
				return false;
		} else if (!serviceStartDate.equals(other.serviceStartDate))
			return false;
		if (serviceType != other.serviceType)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", msisdn=" + msisdn + ", customerIdOwner=" + customerIdOwner
				+ ", customerIdUser=" + customerIdUser + ", serviceType=" + serviceType + ", serviceStartDate="
				+ serviceStartDate + "]";
	}
	
	
	
}
