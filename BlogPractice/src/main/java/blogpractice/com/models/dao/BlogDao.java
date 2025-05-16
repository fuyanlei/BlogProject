package blogpractice.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogpractice.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
	// insert update
	Blog save(Blog blog);

	// ブログの一覧を表示させるときに
	List<Blog> findAll();

	// ブログの登録チェックに使用
	Blog findByBlogTitle(String blogTitle);

	//編集画面を表示する際に使用
	Blog findByBlogId(Long blogId);

	//削除使用
	void deleteByBlogId(Long blogId);
}
