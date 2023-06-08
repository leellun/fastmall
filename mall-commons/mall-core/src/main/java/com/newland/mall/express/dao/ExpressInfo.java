package com.newland.mall.express.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 物流追踪信息
 * @author leell
 */
@Data
public class ExpressInfo {

    @JsonProperty("LogisticCode")
    private String LogisticCode;
    @JsonProperty("ShipperCode")
    private String ShipperCode;
    @JsonProperty("Traces")
    private List<Traces> Traces;
    @JsonProperty("State")
    private String State;
    @JsonProperty("EBusinessID")
    private String EBusinessID;
    @JsonProperty("Success")
    private boolean Success;
    @JsonProperty("Reason")
    private String Reason;

    private String ShipperName;
}