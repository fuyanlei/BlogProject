package blogpractice.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blogpractice.com.models.entity.Account;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;

	// ブログ一覧
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			model.addAttribute("accountName", account.getAccountName());
			return "blog_list.html";
		}
	}
}
