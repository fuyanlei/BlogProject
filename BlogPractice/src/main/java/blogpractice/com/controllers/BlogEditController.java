package blogpractice.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blogpractice.com.models.entity.Account;
import blogpractice.com.models.entity.Blog;
import blogpractice.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private HttpSession session;

	// 編集画面の表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			Blog blog = blogService.blogEditCheck(blogId);
			if (blog == null) {
				return "redirect:/blog/list";
			} else {
				model.addAttribute("accountName", account.getAccountName());
				model.addAttribute("accountId", account.getAccountId());//
				model.addAttribute("blog", blog);
				return "blog_edit.html";
			}
		}
	}

	// 更新処理をする
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, @RequestParam String article, @RequestParam Long blogId) {
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/account/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (blogService.blogUpdate(blogId, blogTitle, categoryName, fileName, article, account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;
			}
		}

	}
}
