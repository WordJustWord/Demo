package cn.nova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.content.service.CategoryService;
import cn.nova.pojo.EasyUITreeNode;
import cn.nova.pojo.TbContentCategory;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private CategoryService categoryService; 
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> InitCategory(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUITreeNode> treeNodes=categoryService.GetList(parentId);
		return treeNodes;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult Create(Long parentId,String name){
		TbContentCategory category=categoryService.Create(parentId,name);
		return TaotaoResult.ok(category);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult Update(Long id,String name){
		categoryService.Update(id,name);
		return TaotaoResult.ok();
	}
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult Del(Long parentId,Long id){
		categoryService.Del(parentId,id);
		return TaotaoResult.ok();
	}
}
