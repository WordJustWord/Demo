package cn.nova.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.nova.content.service.ContentService;
import cn.nova.mapper.TbContentMapper;
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbContent;
import cn.nova.pojo.TbContentExample;
import cn.nova.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	/**
	 * 获取Content分页内容
	 */
	@Override
	public PageBean GetListByCategoryId(Long categoryId, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<TbContent> pageInfo=new PageInfo<>(list);
		PageBean bean=new PageBean();
		bean.setRows(list);
		bean.setTotal(pageInfo.getTotal());
		return bean;
	}

	/**
	 * 保存Content
	 */
	@Override
	public Integer SaveContent(TbContent content) {
		int res = contentMapper.insert(content);
		return res;
	}
	/**
	 * 修改一个Content
	 */
	@Override
	public Integer UpdateContent(TbContent content) {
		
		TbContent tbContent = contentMapper.selectByPrimaryKey(content.getId());
		content.setCreated(tbContent.getCreated());
		
		int res = contentMapper.updateByPrimaryKey(content);
		return res;
	}

	@Override
	public void DeleteContent(String[] strings) {
		for (String id : strings) {
			int res = contentMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
	}

}
