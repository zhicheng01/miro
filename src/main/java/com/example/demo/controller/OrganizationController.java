package com.example.demo.controller;


import com.example.demo.common.Node;
import com.example.demo.common.TreeParam;
import com.example.demo.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "组织树接口")
@RestController
@RequestMapping("/org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @ApiOperation(value="获取组织树", notes="获取组织树")
    @ApiImplicitParam(name = "treeParam", value = "用户详细实体treeParam", required = true, dataType = "TreeParam")
    @RequestMapping(value = "/getTree", method = RequestMethod.POST)
    public Node getTree(TreeParam treeParam){
        Node node = organizationService.getTree(treeParam);
        return node;
    }
}
