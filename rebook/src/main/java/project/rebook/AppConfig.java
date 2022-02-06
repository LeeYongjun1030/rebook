package project.rebook;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import project.rebook.domain.Member;
import project.rebook.repository.MemberRepository;

import javax.persistence.EntityManager;

@Configuration
public class AppConfig {
}
