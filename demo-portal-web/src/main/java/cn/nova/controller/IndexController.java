package cn.nova.controller;

import cn.nova.common.utils.JsonUtils;
import cn.nova.content.service.ContentService;
import cn.nova.pojo.TbContent;
import cn.nova.protalpojo.Ad1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private ContentService contentService;

	@Value("${AD1_CID}")
	private Long AD1_CID;

	@Value("${AD1_HEIGHT}")
	private Long AD1_HEIGHT;

	@Value("${AD1_HEIGHT_B}")
	private Long AD1_HEIGHT_B;

	@Value("${AD1_WIDTH}")
	private Long AD1_WIDTH;

	@Value("${AD1_WIDTH_B}")
	private Long AD1_WIDTH_B;

	@RequestMapping("/index")
	public String ShowIndex(Model model) {
		
		List<Ad1Node> listAd1Node = GetAd1Node();
		model.addAttribute("ad1",JsonUtils.objectToJson(listAd1Node));
		return "index";
	}

	/**
	 * 获取中间大广告
	 * 
	 * @return
	 */
	private List<Ad1Node> GetAd1Node() {
		
		List<TbContent> list = contentService.GetContentList(AD1_CID);
		List<Ad1Node> ad1Nodes = new ArrayList<>();
		for (TbContent tbContent : list) {
			Ad1Node node = new Ad1Node();
			node.setAlt(tbContent.getTitle());
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT_B);
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTH_B);
			node.setSrc(tbContent.getPic());
			node.setSrcB(tbContent.getPic2());
			node.setHref(tbContent.getUrl());
			ad1Nodes.add(node);
		}
		return ad1Nodes;
	}
}
