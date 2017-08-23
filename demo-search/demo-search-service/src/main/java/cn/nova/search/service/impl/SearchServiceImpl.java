package cn.nova.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.nova.common.pojo.SearchResult;
import cn.nova.search.dao.SearchDao;
import cn.nova.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SearchResult Search(String queryString,int page,int rows) throws Exception {
		
		SolrQuery solrQuery = new SolrQuery();
		
		solrQuery.setQuery(queryString);
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		
		solrQuery.set("df", "item_title");
		
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		
		SearchResult result=searchDao.search(solrQuery);
		
		long recordCount=result.getRecordCount();
		long pageCount=recordCount/rows;
		if (recordCount%rows>0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		
		return result;
	}

}
