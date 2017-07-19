package cn.nova.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nova.mapper.TbItemCatMapper;
import cn.nova.pojo.EasyUITreeNode;
import cn.nova.pojo.TbItemCat;
import cn.nova.pojo.TbItemCatExample;
import cn.nova.pojo.TbItemCatExample.Criteria;
import cn.nova.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 查询树形菜单
	 */
	@Override
	public List<EasyUITreeNode> initTree(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();

		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);

		List<TbItemCat> list = itemCatMapper.selectByExample(example);

		List<EasyUITreeNode> nodes = new ArrayList<>();
		for (TbItemCat itemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent() ? "closed" : "open");
			
			nodes.add(node);
		}
		return nodes;
	}

}
