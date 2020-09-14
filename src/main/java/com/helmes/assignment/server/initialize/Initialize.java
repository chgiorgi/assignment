package com.helmes.assignment.server.initialize;

import com.helmes.assignment.server.model.SectorEntity;
import com.helmes.assignment.server.repository.SectorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Component
public class Initialize {

    private static final String FILE_PATH = "file/index.html";

    SectorsRepository sectorsRepository;

    public Initialize(SectorsRepository repository) {
        sectorsRepository = repository;
    }

    @PostConstruct
    private void readFile() {
        String fileAsString = getHTMLFileAsString();
        ArrayList<SectorEntity> sectorsFromFile = getSectorsFromFile(fileAsString);
        trimSectorNames(sectorsFromFile);
        if (sectorsRepository.count() == 0)
            sectorsRepository.saveAll(sectorsFromFile);
    }

    private void trimSectorNames(List<SectorEntity> sectorsFromFile) {
        for (SectorEntity sectorEntity : sectorsFromFile) {
            sectorEntity.setName(sectorEntity.getName().trim());
            if (!sectorEntity.getChildren().isEmpty()) {
                trimSectorNames(sectorEntity.getChildren());
            }
        }
    }

    private ArrayList<SectorEntity> getSectorsFromFile(String fileAsString) {
        ArrayList<String> sectorsStringList = getSectorsStringList(fileAsString);
        ArrayList<SectorEntity> sectorsList = new ArrayList<>();
        SectorEntity previousParentSector = null;
        SectorEntity previousSector = null;
        int previousSpaceCount = 0;
        for (String option : sectorsStringList) {
            String sectorName = option.replace("&nbsp;", " ").replace("&amp;", "&");
            int spaceCount = getSpaceCount(sectorName);
            SectorEntity sector = getSectorObject(sectorName);
            if (spaceCount == 0) {
                sectorsList.add(sector);
                previousSpaceCount = spaceCount;
            } else if (spaceCount > previousSpaceCount) {
                if (previousSector != null && previousSector.getChildren() != null) {
                    sector.setParent(previousSector);
                    previousSector.getChildren().add(sector);
                }
                previousSpaceCount = spaceCount;
                previousParentSector = previousSector;
            } else if (spaceCount < previousSpaceCount) {
                if (previousParentSector != null) {
                    while (getSpaceCount(previousParentSector.getName()) != spaceCount) {
                        previousParentSector = previousParentSector.getParent();
                    }
                    previousParentSector = previousParentSector.getParent();
                    sector.setParent(previousParentSector);
                    previousParentSector.getChildren().add(sector);
                }
                previousSpaceCount = spaceCount;
            } else {
                sector.setParent(previousParentSector);
                if (previousParentSector != null) {
                    previousParentSector.getChildren().add(sector);
                }
            }
            previousSector = sector;
        }
        return sectorsList;

    }

    private SectorEntity getSectorObject(String sectorName) {
        return SectorEntity.builder()
                .name(sectorName)
                .children(new ArrayList<>())
                .build();
    }

    private ArrayList<String> getSectorsStringList(String fileAsString) {
        String optionsStr = fileAsString.substring(fileAsString.indexOf("<option"), fileAsString.indexOf("</select>"));
        List<String> splitOptions = Arrays.asList(optionsStr.split("\">|</option>"));
        ArrayList<String> options = new ArrayList<>();
        for (int i = 1; i < splitOptions.size(); i += 2) {
            options.add(splitOptions.get(i));
            log.info(splitOptions.get(i));
        }
        return options;
    }

    private int getSpaceCount(String element) {
        int spaces = 0;
        for (char character : element.toCharArray()) {
            if ((int) character != 32) {
                return spaces;
            }
            spaces++;
        }
        return spaces;

    }

    private String getHTMLFileAsString() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(FILE_PATH);
        if (inputStream != null) {
            String result = null;
            try {
                result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
