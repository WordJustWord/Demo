package cn.nova.content.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.nova.common.utils.JsonUtils;
import cn.nova.content.service.ContentService;
import cn.nova.jedis.JedisClient;
import cn.nova.mapper.TbContentMapper;
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbContent;
import cn.nova.pojo.TbContentExample;
import cn.nova.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;

//	@Value("${INDEX_CONTENT}")
//	private String INDEX_CONTENT;

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

		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		PageBean bean = new PageBean();
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

	/**
	 * 获取广告列表
	 * 
	 * @param categoryId
	 * @return
	 */
	@Override
	public List<TbContent> GetContentList(Long categoryId) {

		// 优先查询缓存
		try {
			String json = jedisClient.hget("INDEX_CONTENT", categoryId + "");
			if (StringUtils.isNoneBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				System.out.println("从缓存中取到的数据");
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);

		List<TbContent> list = contentMapper.selectByExample(example);

		try {
			jedisClient.hset("INDEX_CONTENT", categoryId + "", JsonUtils.objectToJson(list));
			System.out.println("从数据库中取到的数据");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
