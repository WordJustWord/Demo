package cn.nova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.nova.common.pojo.TaotaoResult;
import cn.nova.search.service.SearchItemService;

@Controller
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/index/import")
	public TaotaoResult ImportAllItems() {

		TaotaoResult taotaoResult;
		try {
			taotaoResult = searchItemService.ImportAllItems();
			return taotaoResult;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "导入数据失败!");
		}
	}
}
