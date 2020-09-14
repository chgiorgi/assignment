package com.helmes.assignment.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class Sector {

	private Long id;

	private String name;

	private List<Sector> children;

}
