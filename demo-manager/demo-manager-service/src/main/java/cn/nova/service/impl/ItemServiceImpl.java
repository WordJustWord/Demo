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
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbItem;
import cn.nova.pojo.TbItemDesc;
import cn.nova.pojo.TbItemExample;
import cn.nova.service.ItemService;
import javassist.runtime.Desc;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;

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
}

