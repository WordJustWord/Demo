package cn.nova.content.service;

import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbContent;

import java.util.List;

public interface ContentService {

	PageBean GetListByCategoryId(Long categoryId, Integer page, Integer rows);

	Integer SaveContent(TbContent content);

	Integer UpdateContent(TbContent content);

	void DeleteContent(String[] strings);

	List<TbContent> GetContentList(Long categoryId);
}
