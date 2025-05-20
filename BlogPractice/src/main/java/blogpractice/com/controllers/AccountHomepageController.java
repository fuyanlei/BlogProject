package blogpractice.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blogpractice.com.models.entity.Account;
import blogpractice.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountHomepageController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private HttpSession session;

	@GetMapping("/account/homepage")
	public String showHomePage(Model model) {
		Account loginUser = (Account) session.getAttribute("loginAccountInfo");
		if (loginUser == null) {
			return "redirect:/account/login";
		} else {
			//情報を取得
			model.addAttribute("accountId", loginUser.getAccountId());
			model.addAttribute("accountName", loginUser.getAccountName());
			model.addAttribute("accountEmail", loginUser.getAccountEmail());
			return "account_homepage.html";
		}
	}
}
