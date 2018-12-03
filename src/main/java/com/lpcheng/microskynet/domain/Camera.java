package com.lpcheng.microskynet.domain;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class Camera {
    @Expose
    private String ip;
    @Expose
    private Double lng;//经度
    @Expose
    private Double lat;//纬度
}
