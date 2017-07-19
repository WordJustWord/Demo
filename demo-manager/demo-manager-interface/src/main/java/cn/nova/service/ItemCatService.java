package cn.nova.service;

import java.util.List;

import cn.nova.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> initTree(Long parentId);
}
