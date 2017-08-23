package cn.nova.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.nova.common.pojo.SearchItem;
import cn.nova.common.pojo.SearchResult;

//@Repository是将Dao类声明为Bean，方便Spring加载
@Repository
public class SearchDao {

	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 根据条件获取查询数据
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(SolrQuery query) throws Exception{
		
		QueryResponse response = solrServer.query(query);
		
		SolrDocumentList solrDocumentList = response.getResults();
		
		List<SearchItem> itemList=new ArrayList<>();
		
		for (SolrDocument solrDocument : solrDocumentList) {
			
			SearchItem item=new SearchItem();
			item.setId((String) solrDocument.get("id"));
			item.setCategory_name((String)solrDocument.get("item_category_name"));
			item.setImage((String)solrDocument.get("item_image"));
			item.setPrice((long)solrDocument.get("item_price"));
			item.setSell_point((String)solrDocument.get("item_sell_point"));
			item.setItem_desc((String)solrDocument.get("item_desc"));
			
			//高亮显示
			Map<String, Map<String, List<String>>> highlighting=response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			
			String itemTitle="";
			if (list!=null&&list.size()>0) {
				itemTitle=list.get(0);
			}
			else {
				itemTitle=(String)solrDocument.get("item_title");
			}
			item.setTitle(itemTitle);
			
			itemList.add(item);
		}
		
		SearchResult searchResult = new SearchResult();
		
		searchResult.setItemList(itemList);
		searchResult.setRecordCount(solrDocumentList.getNumFound());
		return searchResult;
	}
}
