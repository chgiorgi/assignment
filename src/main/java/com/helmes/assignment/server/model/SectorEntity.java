package com.helmes.assignment.server.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SECTOR")
@Entity
public class SectorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToOne
	private SectorEntity parent;

	@Column(name = "CHILDREN")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SectorEntity> children;

	public SectorEntity(Long id) {
		this.id = id;
	}
}
