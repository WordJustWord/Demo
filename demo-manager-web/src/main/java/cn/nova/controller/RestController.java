package cn.nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.pojo.TbItem;
import cn.nova.pojo.TbItemParamItem;
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
	@RequestMapping("/item/update")
	@ResponseBody
	public TaotaoResult UpdateItem(TbItem item,String desc){
		TaotaoResult result=itemService.Update(item,desc);
		return result;
	}
	@RequestMapping("/item/instock")
	@ResponseBody
	public TaotaoResult ItemDown(String ids){
		TaotaoResult result=itemService.ItemDown(ids);
		return result;
	}
	@RequestMapping("/item/reshelf")
	@ResponseBody
	public TaotaoResult ItemUp(String ids){
		TaotaoResult result=itemService.ItemUp(ids);
		return result;
	}
	@RequestMapping("/item/delete")
	@ResponseBody
	public TaotaoResult ItemDel(String ids){
		TaotaoResult result=itemService.ItemDel(ids);
		return result;
	}
}
