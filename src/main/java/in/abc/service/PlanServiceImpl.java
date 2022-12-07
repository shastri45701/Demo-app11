package in.abc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.abc.entity.Plan;
import in.abc.entity.PlanCategory;
import in.abc.repo.PlanCategoryRepo;
import in.abc.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService
{
	@Autowired
	private PlanRepo planRepo;
	
	private PlanCategoryRepo planCategoryRepo;
	
	

	@Override
	public Map<Integer, String> getPlanCategories() {
		
		List<PlanCategory> categories = planCategoryRepo.findAll();
		Map<Integer,String> categoryMap=new HashMap<>();
		categories.forEach(category->{
			
		categoryMap.put(category.getCategoryId(),category.getCategoryName());
		});
		// TODO Auto-generated method stub
		return categoryMap;
		
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepo.save(plan);
		return saved.getPlanId()!=null;
		
	}

	@Override
	public List<Plan> getAllPlans() {
		return planRepo.findAll();
		
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);
		
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean update(Plan plan) {
		Plan save = planRepo.save(plan);
		
		return plan.getPlanId()!=null;
		
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status=false;
		try {
			
		
		planRepo.deleteById(planId);
		status=true;
		}
		catch(Exception e)
		
		{
		e.printStackTrace();	
		}
		
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		if(findById.isPresent())
		{
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}
	

}
