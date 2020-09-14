package com.helmes.assignment.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Table(name = "USER")
@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "NAME", length = 100, unique = true, nullable = false)
	private String name;

	@Column(name = "AGREE_TO_TERMS")
	private boolean agreeToTerms;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<SectorEntity> sectors;

}
