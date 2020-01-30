package br.com.alura.forum.dbinitializr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.CategoryRepository;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private TopicRepository topicRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    
	public DataLoader(TopicRepository topicRepository, CategoryRepository categoryRepository,
			UserRepository userRepository, CourseRepository courseRepository) {
		this.topicRepository = topicRepository;
		this.categoryRepository = categoryRepository;
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
	}

	@Override
    public void run(String... args) {
        loadData();
    }

    private void loadData() {
    	Category programacao = categoryRepository.save(new Category("Programacao"));
    	Category category = categoryRepository.save(new Category("Java", programacao));
		User user = userRepository.save(new User("Rafael", "rafaeltavares91@gmail.com", new BCryptPasswordEncoder().encode("123456")));
		Course course = courseRepository.save(new Course("Spring boot - fj27", category));
		Topic topic = new Topic("Problemas da primeira aula", "tive um erro de categoria", user, course);
		topicRepository.save(topic);
		
		userRepository.save(new User("Rafa", "rafa@gmail.com", "$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK"));
    }
}