package com.helmes.assignment.api.model;

import com.helmes.assignment.server.model.SectorEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Data
public class Sector {

	private Long id;

	private String name;

	private List<Sector> children;

}