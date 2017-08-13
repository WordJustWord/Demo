package cn.nova.content.service;

import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbContent;

public interface ContentService {

	PageBean GetListByCategoryId(Long categoryId, Integer page, Integer rows);

	Integer SaveContent(TbContent content);

	Integer UpdateContent(TbContent content);

	void DeleteContent(String[] strings);

}
