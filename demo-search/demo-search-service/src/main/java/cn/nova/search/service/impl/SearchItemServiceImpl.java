package cn.nova.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nova.common.pojo.SearchItem;
import cn.nova.common.pojo.TaotaoResult;
import cn.nova.search.mapper.SearchItemMapper;
import cn.nova.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private SearchItemMapper searchItemMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public TaotaoResult ImportAllItems() throws Exception {
		
		List<SearchItem> list=searchItemMapper.GetSearchItemList();
		
		for (SearchItem searchItem : list) {
			
			SolrInputDocument document = new SolrInputDocument();
			
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_desc());
			
			solrServer.add(document);
		}
		
		solrServer.commit();
		
		return TaotaoResult.ok();
	}

}
