package com.helmes.assignment.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmptyUserResponse {

	private Long id;

	private String name;

	private Boolean agreeToTerms;

	private List<Long> sectors;

}
