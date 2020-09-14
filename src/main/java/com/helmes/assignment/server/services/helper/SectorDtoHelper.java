package com.helmes.assignment.server.services.helper;

import com.helmes.assignment.api.model.Sector;
import com.helmes.assignment.server.model.SectorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SectorDtoHelper {

	public static Sector toDTO(SectorEntity entity) {

		return Sector.builder()
				.id(entity.getId())
				.name(entity.getName())
				.children(entity.getChildren().stream().map(SectorDtoHelper::toDTO).collect(Collectors.toList()))
				.build();
	}

	public static SectorEntity fromDTO(Sector sector) {
		return SectorEntity.builder()
				.id(sector.getId())
				.name(sector.getName())
				.children(sector.getChildren().stream().map(SectorDtoHelper::fromDTO).collect(Collectors.toList()))
				.build();
	}

	public static List<Sector> toDTOs(List<SectorEntity> sectorEntityes) {
		if (sectorEntityes == null) {
			return new ArrayList<>();
		}
		return sectorEntityes.stream().map(SectorDtoHelper::toDTO).collect(Collectors.toList());
	}

	public static List<SectorEntity> fromDTOs(List<Sector> sectors) {
		if (sectors == null) {
			return new ArrayList<>();
		}
		return sectors.stream().map(SectorDtoHelper::fromDTO).collect(Collectors.toList());
	}

}
