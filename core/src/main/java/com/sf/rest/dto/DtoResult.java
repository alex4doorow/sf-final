package com.sf.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoResult {
    private Integer code = 0;
    private String info = "";
    private String errorMessage = "";

    public DtoResult(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public static DtoResult error(Integer code, String info, String errorMessage) {
        return new DtoResult(code, info, errorMessage);
    }

    public static DtoResult success(Integer code) {
        return new DtoResult(code, "", "");
    }

    public static DtoResult success() {
        return DtoResult.success(0);
    }

    public static DtoResult empty() {
        return new DtoResult(0, "", "");
    }
}
