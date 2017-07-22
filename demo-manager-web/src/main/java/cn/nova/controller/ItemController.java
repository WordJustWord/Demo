package cn.nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbItem;
import cn.nova.pojo.TbItemParamItem;
import cn.nova.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/list")
	@ResponseBody
	public PageBean GetList(Integer page, Integer rows) {
		PageBean list = itemService.GetList(page, rows);
		return list;
	}

	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult SaveItem(TbItem item,String desc) {
		TaotaoResult result = itemService.SaveItems(item, desc);
		return result;
	}
}
