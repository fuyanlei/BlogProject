package blogpractice.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogpractice.com.models.dao.BlogDao;
import blogpractice.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	// ブログ一覧チェック
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}

	// ブログの登録処理チェック
	public boolean createBlog(String blogTitle, String categoryName, String blogImage, String article, Long accountId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId));
			return true;
		} else {
			return false;
		}
	}

	// 編集画面を表示するときに使用
	public Blog blogEditCheck(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	// 削除処理
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}

	// 更新処理のチェック
	public boolean blogUpdate(Long blogId, String blogTitle, String categoryName, String blogImage, String article,
			Long accountId) {
		if (blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setCategoryName(categoryName);
			blog.setBlogImage(blogImage);
			blog.setArticle(article);
			blog.setAccountId(accountId);
			blogDao.save(blog);
			return true;
		}
	}

	// ブログ検索処理
	public List<Blog> searchBlogs(String keyword) {
		if (keyword == null || keyword.isEmpty()) {
			return blogDao.findAll();
		} else {
			return blogDao.findByBlogTitleContaining(keyword);
		}
	}

}
