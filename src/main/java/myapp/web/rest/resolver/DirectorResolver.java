//package myapp.web.rest.resolver;
//
//import graphql.kickstart.tools.GraphQLResolver;
//import myapp.domain.Director;
//import myapp.repository.DirectorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityNotFoundException;
//
//@Component
//public class DirectorResolver implements GraphQLResolver<Director> {
//
//    @Autowired
//    DirectorRepository directorRepository;
//
//    public Director getDirector(Long id) {
//        return directorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//    }
//
//}
