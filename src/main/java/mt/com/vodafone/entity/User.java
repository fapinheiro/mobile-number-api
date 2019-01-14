package mt.com.vodafone.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="users")
@SequenceGenerator(sequenceName="seq_user", name = "seq_user")
@JsonIgnoreProperties(value = {"creationAt"}, allowGetters = true)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_user")
    private long id;
    
    @NotBlank
	@Size(max = 100)
    private String username;
    
    @NotBlank
	@Size(max = 100)
	private String password;
	
	@Column(name="creation_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationAt;

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Date getCreationAt() {
		return this.creationAt;
	}

	public void setCreationAt(Date creationAt) {
		this.creationAt = creationAt;
	}


	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(creationAt, user.creationAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, creationAt);
	}


	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", username='" + getUsername() + "'" +
			", password='" + getPassword() + "'" +
			", createdAt='" + getCreationAt() + "'" +
			"}";
	}


}
