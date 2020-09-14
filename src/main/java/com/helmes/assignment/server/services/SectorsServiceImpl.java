package com.helmes.assignment.server.services;

import com.helmes.assignment.api.model.Sector;
import com.helmes.assignment.server.model.SectorEntity;
import com.helmes.assignment.server.repository.SectorsRepository;
import com.helmes.assignment.server.services.helper.SectorDtoHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectorsServiceImpl implements SectorsService {

	private final SectorsRepository sectorsRepository;

	public SectorsServiceImpl(SectorsRepository sectorsRepository) {
		this.sectorsRepository = sectorsRepository;
	}

	@Override
	public List<Sector> getAllSectors() {
		ArrayList<SectorEntity> sectors = new ArrayList<>();
		List<SectorEntity> allEntities = sectorsRepository.findAll();
		for (SectorEntity sectorEntity : allEntities) {
			if (sectorEntity.getParent() == null) {
				sectors.add(sectorEntity);
			}
		}
		return SectorDtoHelper.toDTOs(sectors);
	}

}
