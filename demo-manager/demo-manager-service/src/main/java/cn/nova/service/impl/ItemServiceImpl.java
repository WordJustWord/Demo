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
	/**
	 * 初始化参数配置
	 */
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
	/**
	 * 修改一项参数
	 */
	@Override
	public TaotaoResult Update(TbItem item, String desc) {
		//保存商品项
		TbItemExample itemExample = new TbItemExample();
		TbItem tbItem = itemMapper.selectByPrimaryKey(item.getId());
		tbItem.setCid(item.getCid());
		tbItem.setTitle(item.getTitle());
		tbItem.setSellPoint(item.getSellPoint());
		tbItem.setPrice(item.getPrice());
		tbItem.setNum(item.getNum());
		tbItem.setBarcode(item.getBarcode());
		tbItem.setImage(item.getImage());
		tbItem.setUpdated(new Date());
		itemMapper.updateByPrimaryKey(tbItem);

		//保存商品描述
		TbItemDescExample example = new TbItemDescExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(item.getId());
		List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
		if (list.size()>0) {
			TbItemDesc itemDesc = new TbItemDesc();
			itemDesc=list.get(0);
			itemDesc.setItemDesc(desc);
			itemDescMapper.updateByPrimaryKeyWithBLOBs(itemDesc);
		}
		return TaotaoResult.ok();
	}
	/**
	 * 下架商品
	 */
	@Override
	public TaotaoResult ItemDown(String ids) {
		if (ids.indexOf(",")>0) {
			String[] arrIds = ids.split(",");
			for (String itemId : arrIds) {
				TbItem tbItem = itemMapper.selectByPrimaryKey(Long.parseLong(itemId));
				tbItem.setStatus((byte) 2);
				itemMapper.updateByPrimaryKey(tbItem);
			}
		}
		return TaotaoResult.ok();
	}
	/**
	 * 上架商品
	 */
	@Override
	public TaotaoResult ItemUp(String ids) {
		if (ids.indexOf(",")>0) {
			String[] arrIds = ids.split(",");
			for (String itemId : arrIds) {
				TbItem tbItem = itemMapper.selectByPrimaryKey(Long.parseLong(itemId));
				tbItem.setStatus((byte) 1);
				itemMapper.updateByPrimaryKey(tbItem);
			}
		}
		return TaotaoResult.ok();
	}
	/**
	 * 删除商品
	 */
	@Override
	public TaotaoResult ItemDel(String ids) {
		if (ids.indexOf(",")>0) {
			String[] arrIds = ids.split(",");
			for (String itemId : arrIds) {
				TbItem tbItem = itemMapper.selectByPrimaryKey(Long.parseLong(itemId));
				tbItem.setStatus((byte) 3);
				itemMapper.updateByPrimaryKey(tbItem);
			}
		}
		return TaotaoResult.ok();
	}
}

