package blogpractice.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountLogoutController {
	@Autowired
	private HttpSession session;

	// ログアウト処理
	@GetMapping("/account/logout")
	public String accountLogout() {
		session.invalidate();
		return "redirect:/account/login";
	}
}
