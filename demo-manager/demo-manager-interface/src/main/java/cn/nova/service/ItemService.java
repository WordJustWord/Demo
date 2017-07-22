package cn.nova.service;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbItem;
import cn.nova.pojo.TbItemParamItem;

public interface ItemService {

	PageBean GetList(int page, int rows);

	TaotaoResult SaveItems(TbItem item, String desc);
	
	TaotaoResult InitDesc(Long id);
	
	TaotaoResult InitParam(Long id);

	TaotaoResult Update(TbItem item, String desc);

	TaotaoResult ItemDown(String ids);

	TaotaoResult ItemUp(String ids);

	TaotaoResult ItemDel(String ids);
}
