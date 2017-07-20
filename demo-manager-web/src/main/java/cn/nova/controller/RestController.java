package cn.nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.service.ItemService;

@Controller
@RequestMapping("/rest")
public class RestController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/query/item/desc/{id}")
	@ResponseBody
	public TaotaoResult InitDesc(@PathVariable Long id){
		TaotaoResult result = itemService.InitDesc(id);
		return result;
	}
	@RequestMapping("/item/param/item/query/{id}")
	@ResponseBody
	public TaotaoResult InitParam(@PathVariable Long id) {
		TaotaoResult result = itemService.InitParam(id);
		return result;
	}
}
