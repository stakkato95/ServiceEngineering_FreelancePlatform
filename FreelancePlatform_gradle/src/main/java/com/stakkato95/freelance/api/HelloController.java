package com.stakkato95.freelance.api;

import com.stakkato95.freelance.domain.Client;
import com.stakkato95.freelance.domain.Freelancer;
import com.stakkato95.freelance.domain.Project;
import com.stakkato95.freelance.repository.ClientRepository;
import com.stakkato95.freelance.repository.FreelancerRepository;
import com.stakkato95.freelance.repository.ProjectRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

@ExecuteOn(TaskExecutors.IO)
@Controller("/hello")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Inject
    ClientRepository customerRepo;

    @Inject
    FreelancerRepository freelancerRepo;

    @Inject
    ProjectRepository projectRepo;

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String hell() {
        //TODO nice return http codes, headers, payloads
        //https://guides.micronaut.io/latest/micronaut-jpa-hibernate-maven-java.html
        return "hello";
    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("created");

        var f = new Freelancer(
                null,
                "first",
                "second",
                "nick",
                "a@a.a",
                emptyList()/*,
                List.of("tech1", "tech2")*/
        );
        freelancerRepo.save(f);

        var c = new Client(null, "first", "second", "a@a.a");
        customerRepo.save(c);

        var fOptional = freelancerRepo.findById(f.getId());
        if (fOptional.isEmpty()) {
            LOGGER.error("optional freelance is empty");
            return;
        }

        var f1 = fOptional.get();
        var p = new Project(null, "proj", List.of(f1));
        f1.getProjects().add(p);
        p = projectRepo.merge(p);

        var fOptionalNew = freelancerRepo.findById(f.getId());
        var name = fOptionalNew.get().getProjects().get(0).getName();
        LOGGER.error("{}", name);

        freelancerRepo.delete(fOptionalNew.get());
    }
}
