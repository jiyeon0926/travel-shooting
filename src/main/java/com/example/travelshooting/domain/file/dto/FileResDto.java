package com.example.travelshooting.domain.file.dto;

import com.example.travelshooting.domain.file.entity.LeisureFile;
import com.example.travelshooting.domain.file.entity.PosterFile;
import com.example.travelshooting.global.common.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FileResDto implements BaseDtoDataType {

    private final Long id;

    private final String fileName;

    private final String url;

    public FileResDto(PosterFile file) {
        this.id = file.getId();
        this.fileName = file.getFileName();
        this.url = file.getUrl();
    }

    public FileResDto(LeisureFile file) {
        this.id = file.getId();
        this.fileName = file.getFileName();
        this.url = file.getUrl();
    }
}
