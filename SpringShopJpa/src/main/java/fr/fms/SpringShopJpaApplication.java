package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category smartphone = categoryRepository.save(new Category("smartphone"));
		Category tablet = categoryRepository.save(new Category("tablet"));
		Category pc = categoryRepository.save(new Category("pc"));
		
		articleRepository.save(new Article("S10", "Samsoung", 549, smartphone));
		articleRepository.save(new Article("S9", "Samsoung", 379, smartphone));
		articleRepository.save(new Article("MI10", "Xiaomi", 149, smartphone));
		articleRepository.save(new Article("GalaxyTab", "Samsoung", 479, tablet));
		articleRepository.save(new Article("Ipad", "Apple", 995, tablet));
		articleRepository.save(new Article("R510", "Asus", 1190, pc));
		
		/*for (Article article : articleRepository.findByBrand("Samsoung")) {
			System.out.println(article);
		}*/
	}

}
