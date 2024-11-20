package ma.hariti.asmaa.wrm.citrontrack.repository;
import ma.hariti.asmaa.wrm.citrontrack.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository   extends JpaRepository<Tree, Long> {
}
