package in.abc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.abc.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory,Integer>
{
	
}
