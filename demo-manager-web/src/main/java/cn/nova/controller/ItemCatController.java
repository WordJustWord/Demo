package cn.nova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nova.pojo.EasyUITreeNode;
import cn.nova.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> intiTree(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUITreeNode> list = itemCatService.initTree(parentId);
		return list;
	}
}
