package com.newland.mall.express.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author leell
 */
public class Traces {

    @JsonProperty("AcceptStation")
    private String AcceptStation;
    @JsonProperty("AcceptTime")
    private String AcceptTime;

}