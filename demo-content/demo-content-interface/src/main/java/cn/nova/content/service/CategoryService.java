package cn.nova.content.service;

import java.util.List;

import cn.nova.pojo.EasyUITreeNode;
import cn.nova.pojo.TbContentCategory;

public interface CategoryService {

	List<EasyUITreeNode> GetList(Long parentId);

	TbContentCategory Create(Long parentId, String name);

	void Update(Long id, String name);

	void Del(Long parentId, Long id);

}
