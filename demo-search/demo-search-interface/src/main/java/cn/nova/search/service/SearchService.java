package cn.nova.search.service;

import cn.nova.common.pojo.SearchResult;

public interface SearchService {
	SearchResult Search(String queryString,int page,int rows) throws Exception;
}
