package com.example.demo.service.impl;

import com.example.demo.common.Node;
import com.example.demo.common.TreeParam;
import com.example.demo.entity.Organization;
import com.example.demo.mapper.OrganizationMapper;
import com.example.demo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Node getTree(TreeParam treeParam) {
        //根据companyId查询出根节点
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", treeParam.getCompanyId());
        map.put("orgCode", "101001");
        Organization rootOrg = organizationMapper.getOrgByCondition(map);
        if(rootOrg != null){//存在根节点
            Node rootNode = new Node();
            rootNode.setId(rootOrg.getId());
            rootNode.setCode(rootOrg.getOrgCode());
            rootNode.setName(rootOrg.getOrgName());
            rootNode.setParentId(rootOrg.getParentId());
            rootNode.setIcon("org-tree");
            //查询所有子节点
            map.remove("orgCode");
            List<Organization> allOrg = organizationMapper.getAllOrg(map);
            if(!CollectionUtils.isEmpty(allOrg)){
                Map<Long, Node> orgMap = new HashMap<>();//orgCode为key
                List<Node> childList = new ArrayList<>();
                Map<Long, List<Node>> pIdMap = new HashMap<>();//pId为key
                Node node = null;
                for (Organization org : allOrg) {
                    node = new Node();
                    node.setId(org.getId());
                    node.setCode(org.getOrgCode());
                    node.setName(org.getOrgName());
                    node.setParentId(rootOrg.getParentId());
                    node.setIcon("org-tree");
                    childList.add(node);
                    orgMap.put(org.getId(), node);
                    pIdMap.put(org.getParentId(), childList);
                }
                rootNode.setChildren(getChildren(rootNode, allOrg, pIdMap, orgMap));
                return rootNode;
            }
        }
        return null;
    }

    private List<Node> getChildren(Node rootNode, List<Organization> allOrg, Map<Long, List<Node>> pIdMap, Map<Long, Node> orgMap){
        List<Node> nodeList = new ArrayList<>();
        Node node = null;
        for (Organization org : allOrg){
            if(org.getParentId() == rootNode.getId()){
                //判断nodeList是否已经包含
                if(pIdMap.containsKey(org.getParentId())){//map中包含子节点
                    node = orgMap.get(org.getId());
                    node.setChildren(getChildren(node, allOrg, pIdMap, orgMap));
                }
            } else {
                continue;
            }
            nodeList.add(node);
        }
        return nodeList;
    }

    private boolean isContainNode(String orgCode, List<Node> nodeList){
        for (Node node : nodeList){
            if(orgCode.equals(node.getCode())){
                return true;
            }
        }
        return false;
    }
}
