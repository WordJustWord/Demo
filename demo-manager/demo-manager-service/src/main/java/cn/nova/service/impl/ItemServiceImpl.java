package cn.nova.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.nova.mapper.TbItemMapper;
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbItem;
import cn.nova.pojo.TbItemExample;
import cn.nova.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

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
}

