package project.rebook;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.Member;
import project.rebook.repository.MemberRepository;

import javax.persistence.EntityManager;

@SpringBootApplication
public class RebookApplication {
	public static void main(String[] args) {
		SpringApplication.run(RebookApplication.class, args);
	}
}
