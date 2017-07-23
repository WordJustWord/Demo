package cn.nova.content.service;

import java.util.List;

import cn.nova.pojo.EasyUITreeNode;

public interface CategoryService {

	List<EasyUITreeNode> GetList(Long parentId);

}
