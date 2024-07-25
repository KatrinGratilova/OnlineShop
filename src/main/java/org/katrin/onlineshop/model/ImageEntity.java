package org.katrin.onlineshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.Types;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(nullable = false)
    private long size;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "is_preview_image", nullable = false)
    private boolean isPreviewImage;

    @Column(name = "bytes", nullable = false, columnDefinition = "bytea")
    @Lob
    @JdbcTypeCode(Types.VARBINARY)
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private ProductEntity product;
}
