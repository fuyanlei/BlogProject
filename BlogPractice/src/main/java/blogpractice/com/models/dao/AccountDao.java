package blogpractice.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogpractice.com.models.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

	//insertとupdate
	 Account save(Account account);
	 //同じメールアドレスを登録するのを防ぐ
	 Account findByAccountEmail(String accountEmail);
	 //メールパスワード認証
	 Account findByAccountEmailAndPassword(String accountEmail,String password);
	 
	 
}
