package in.abc.service;

	import java.util.List;
	import java.util.Map;

	import in.abc.entity.Plan;

	public interface PlanService {
		
	 public Map<Integer, String> getPlanCategories();
	 
	 public boolean savePlan(Plan plan);
	 
	 public List<Plan> getAllPlans();
	 
	 public Plan getPlanById(Integer planId);
	 
	 public boolean update(Plan plan);
	 
	 public boolean deletePlanById(Integer planId);
	 
	 public boolean planStatusChange(Integer planId,String status);
	 
	 

	}


