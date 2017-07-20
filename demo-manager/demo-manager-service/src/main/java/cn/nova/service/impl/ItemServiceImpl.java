package cn.nova.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.common.utils.IDUtils;
import cn.nova.mapper.TbItemDescMapper;
import cn.nova.mapper.TbItemMapper;
import cn.nova.mapper.TbItemParamItemMapper;
import cn.nova.mapper.TbItemParamMapper;
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbItem;
import cn.nova.pojo.TbItemDesc;
import cn.nova.pojo.TbItemDescExample;
import cn.nova.pojo.TbItemDescExample.Criteria;
import cn.nova.pojo.TbItemExample;
import cn.nova.pojo.TbItemParamItem;
import cn.nova.pojo.TbItemParamItemExample;
import cn.nova.service.ItemService;
import javassist.runtime.Desc;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	/**
	 * 商品分页列表
	 */
	@Override
	public PageBean GetList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample tbItemExample = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(tbItemExample);
		//取查询数据
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		PageBean pageBean = new PageBean();
		pageBean.setRows(list);
		pageBean.setTotal(pageInfo.getTotal());
		//返回结果
		return pageBean;
	}

	/**
	 * 保存商品信息
	 */
	@Override
	public TaotaoResult SaveItems(TbItem item, String desc) {
		
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		
		return TaotaoResult.ok();
	}

	/**
	 * 获取商品的描述
	 */
	@Override
	public TaotaoResult InitDesc(Long id) {		
		TbItemDescExample example = new TbItemDescExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);		
		TbItemDesc itemDesc = new TbItemDesc();
		List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
		if (list.size()>0) {
			itemDesc=(TbItemDesc)list.get(0);		
		}
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult InitParam(Long id) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		cn.nova.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);		
		TbItemParamItem itemParamItem = new TbItemParamItem();
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list.size()>0) {
			itemParamItem=(TbItemParamItem)list.get(0);
		}
		return TaotaoResult.ok(itemParamItem);
	}
}

