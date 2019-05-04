package com.example.demo.common;

import io.swagger.annotations.ApiModel;

@ApiModel
public class TreeParam {

    private Long companyId;

    private String searchType;

    private String type;


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
