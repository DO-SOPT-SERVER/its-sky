package org.sopt.Seminar.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class PresignedUrlVO {
    private String fileName;
    private String url;
}
