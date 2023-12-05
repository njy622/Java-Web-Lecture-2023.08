package com.example.demo.oracle.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service		//오라클에 할땐 살리고, 다른DB로 할땐 주석으로해서 죽이고
public class BlogServiceOracleImpl implements BlogService {

	@Autowired private BlogDaoOracle blogDao;
	
	@Override
	public Blog getBlog(int bid) {
		Blog blog = blogDao.getBlog(bid);
		return blog;
	}
	
	@Override
	public List<Blog> getBlogList(String field, String query) {
		query = "%" + query + "%";
		List<Blog> list = blogDao.getBlogList(field, query);
		return list;
	}

	@Override
	public void insertBlog(Blog blog) {
		blogDao.insertBlog(blog);
	}

	@Override
	public void updateBlog(Blog blog) {
		blogDao.updateBlog(blog);
	}

	@Override
	public void deleteBlog(int bid) {
		blogDao.deleteBlog(bid);
	}

	@Override
	public void increaseViewCount(int bid) {
		blogDao.increaseViewCount(bid);
	}

	
}
