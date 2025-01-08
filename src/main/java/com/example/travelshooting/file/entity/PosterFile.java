package com.example.travelshooting.file.entity;

import com.example.travelshooting.poster.Poster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "poster_file")
@Getter
@NoArgsConstructor
public class PosterFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private Poster poster;

    @JoinColumn(name = "file_name")
    private String fileName;

    @Column(columnDefinition = "TEXT")
    private String url;

    @JoinColumn(name = "file_type")
    private String fileType;

    // 임시
    public PosterFile(String fileName, String url, String fileType) {
        this.fileName = fileName;
        this.url = url;
        this.fileType = fileType;
    }

    public PosterFile(Poster poster, String fileName, String url, String fileType) {
        this.poster = poster;
        this.fileName = fileName;
        this.url = url;
        this.fileType = fileType;
    }

}
