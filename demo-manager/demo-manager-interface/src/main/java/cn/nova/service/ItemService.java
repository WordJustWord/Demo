package cn.nova.service;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbItem;

public interface ItemService {

	PageBean GetList(int page, int rows);

	TaotaoResult SaveItems(TbItem item, String desc);
	
	TaotaoResult InitDesc(Long id);
	
	TaotaoResult InitParam(Long id);
}
