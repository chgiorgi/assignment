package com.helmes.assignment.server.services.helper;

import com.helmes.assignment.api.model.User;
import com.helmes.assignment.server.model.SectorEntity;
import com.helmes.assignment.server.model.UserEntity;

import java.util.stream.Collectors;

public class UserDtoHelper {

	public static UserEntity fromDTO(User user) {
		UserEntity userEntity = UserEntity.builder()
				.name(user.getName())
				.agreeToTerms(user.isAgreeToTerms())
				.sectors(user.getSectors().stream().map(SectorEntity::new).collect(Collectors.toList())).build();
		if (user.getId() != null) {
			userEntity.setId(user.getId());
		}
		return userEntity;
	}
	public static User toDTO(UserEntity user) {
		return User.builder()
				.id(user.getId())
				.name(user.getName())
				.agreeToTerms(user.isAgreeToTerms())
				.sectors(user.getSectors().stream().map(SectorEntity::getId).collect(Collectors.toList())).build();
	}
}
