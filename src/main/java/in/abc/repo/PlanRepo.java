package in.abc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abc.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan,Integer> {

}
