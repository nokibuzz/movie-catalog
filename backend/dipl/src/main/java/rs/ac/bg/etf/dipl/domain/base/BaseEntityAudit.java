package rs.ac.bg.etf.dipl.domain.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityAudit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "created_date")
	@CreatedDate
	private Date createdDate;
	
	@Column(name = "updated_date")
	@LastModifiedDate
	private Date updatedDate;
}
