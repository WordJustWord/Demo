package cn.nova.service;

import java.util.List;

import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbItem;
import cn.nova.pojo.TbItemExample;

public interface ItemService {
	
	PageBean GetList(int page,int rows);
}
