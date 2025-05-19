package blogpractice.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blogpractice.com.models.entity.Account;
import blogpractice.com.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountLoginController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession session;

	// ログイン画面の表示
	@GetMapping("/account/login")
	public String getAccountLoginPage() {
		return "account_login.html";
	}

	// ログインの処理
	@PostMapping("/account/login/process")
	public String accountLoginProcess(@RequestParam String accountEmail, @RequestParam String password) {
		Account account = accountService.loginCheck(accountEmail, password);
		
		if (account == null) {
			return "account_login.html";
		} else {
			session.setAttribute("loginAccountInfo", account);
			return "redirect:/blog/list";
		}
		
	}
}
