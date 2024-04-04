package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import java.util.Scanner;

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
		
		//Pour les exercices j'ai mis l'énoncé pour pas les confondres dans la console
		//exercice 1.2
		System.out.println("Afficvher tous les articles en base");
		for (Article article: articleRepository.findAllByOrderByIdAsc()) {			
			System.out.println(article);
		}
		System.out.println("Afficher un article en base");
		System.out.println(articleRepository.findById((long) 2));
		
	}
	
	//exercice 1.3 
	public void lookArticle(String description, String brand) {
		if (description != null || brand != null) {
			if (description != null) {
				for (Article article : articleRepository.findByDescription(description)) {
					System.out.println(article);
				}
			}
			if (brand != null) {
				for (Article article : articleRepository.findByBrand(brand)) {
					System.out.println(article);
				}
			}
		} else {
			System.out.println("Opéation impossible, l'ordinatgeur n'est pas une voyante");
		}
	}

	//exercice 1.4 ajouter une métode pour effacer un article.
	public void deleteArticle(Long id) {
		articleRepository.deleteById((long) id);
	}
	
	public void updateArticle(Long id) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Acticle actuel: "+articleRepository.findById((long) id));
		String description = scn.nextLine();
		String brand = scn.nextLine();
		Double price = scn.nextDouble();
		articleRepository.updateById(id, description, brand, price);
	}
	
}
