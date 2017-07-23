package cn.nova.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nova.content.service.CategoryService;
import cn.nova.mapper.TbContentCategoryMapper;
import cn.nova.pojo.EasyUITreeNode;
import cn.nova.pojo.TbContentCategory;
import cn.nova.pojo.TbContentCategoryExample;
import cn.nova.pojo.TbContentCategoryExample.Criteria;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	/**
	 * 初始化列表
	 */
	@Override
	public List<EasyUITreeNode> GetList(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);

		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> nodes = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			nodes.add(node);
		}
		return nodes;
	}
	/**
	 * 创建新的分类
	 */
	@Override
	public TbContentCategory Create(Long parentId, String name) {
		TbContentCategory category=new TbContentCategory();
		category.setParentId(parentId);
		category.setName(name);
		category.setSortOrder(1);
		category.setStatus(1);
		category.setIsParent(false);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		
		contentCategoryMapper.insert(category);
		TbContentCategory category2 = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!category2.getIsParent()) {
			category2.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(category2);
		}
		return category;
	}

}
