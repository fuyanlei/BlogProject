package blogpractice.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blogpractice.com.models.entity.Account;
import blogpractice.com.models.entity.Blog;
import blogpractice.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;
	@Autowired
	private BlogService blogService;

	// ブログ一覧
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			List<Blog> blogList = blogService.selectAllBlogList(account.getAccountId());
			model.addAttribute("accountName", account.getAccountName());
			model.addAttribute("blogList", blogList);
			return "blog_list.html";
		}
	}
	
	//ブログ検索
	@GetMapping("/blog/search")
	public String searchBlogList(@RequestParam("keyword") String keyword, Model model) {
	    Account account = (Account) session.getAttribute("loginAccountInfo");
	    if (account == null) {
	        return "redirect:/account/login";
	    }else {
	    List<Blog> resultList = blogService.searchBlogs(keyword);
	    model.addAttribute("accountName", account.getAccountName());
	    model.addAttribute("blogList", resultList);
	    model.addAttribute("searchQuery", keyword);
	    return "blog_list.html";}
	}

}
