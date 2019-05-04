package com.example.demo.mapper;

import com.example.demo.entity.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrganizationMapper {

    Organization getOrgByCondition(Map<String, Object> map);

    List<Organization> getAllOrg(Map<String, Object> map);

}
