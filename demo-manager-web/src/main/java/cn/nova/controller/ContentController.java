package cn.nova.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.content.service.ContentService;
import cn.nova.pojo.PageBean;
import cn.nova.pojo.TbContent;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/query/list")
	@ResponseBody
	public PageBean GetListByCategoryId(Long categoryId, Integer page, Integer rows) {
		PageBean list = contentService.GetListByCategoryId(categoryId, page, rows);
		return list;
	}

	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult Save(TbContent content) {

		content.setCreated(new Date());
		content.setUpdated(new Date());

		Integer res = contentService.SaveContent(content);

		return TaotaoResult.ok();
	}

	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult Edit(TbContent content) {
		content.setUpdated(new Date());

		Integer res = contentService.UpdateContent(content);

		return TaotaoResult.ok();
	}
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult Del(String ids) {
		String[] strings = ids.split(",");
		contentService.DeleteContent(strings);
		return TaotaoResult.ok();
	}
}
