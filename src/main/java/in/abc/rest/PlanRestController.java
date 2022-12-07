package in.abc.rest;

import java.util.List;
import java.util.Map;

import org.jboss.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.abc.entity.Plan;
import in.abc.props.AppProperties;
import in.abc.service.PlanService;

@RestController
public class PlanRestController {
	
	
	private PlanService planService;
	
	
	private Map<String, String> messages;
	
	public PlanRestController(PlanService planService,AppProperties appProps)
	{
		this.planService=planService;
		this.messages=appProps.getMessages();
		System.out.println(this.messages);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>>PlanCategories(){
		Map<Integer,String> categories=planService.getPlanCategories();
		return new ResponseEntity<>(categories,HttpStatus.OK);
		
		
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		String responsemsg="AppConstants.EMPTY_STR";
		boolean isSaved = planService.savePlan(plan);
		
		if(isSaved) {
			
			responsemsg = messages.get("AppConstants.PLAN_SAVE_SUCC");
			 
		}
		else
		{
			responsemsg= messages.get("AppConstants.PPLAN_SAVE_FAIL");
			 
		}
		return new ResponseEntity<String>(responsemsg, HttpStatus.CREATED);
	}
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>>plans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
		
	}
	@GetMapping("/plan{planId}")
	public ResponseEntity<Plan>editPlan(@PathVariable Integer PlanId){
		Plan plan = planService.getPlanById(PlanId);
		return new ResponseEntity<Plan>(plan,HttpStatus.OK);
		
		
	}
	@PutMapping("/plan")
	public ResponseEntity<String>updatePlan(@RequestBody Plan plan){
		boolean isUpdated = planService.update(plan);
		String msg="AppConstants.EMPTY_STR";
		
		
		if(isUpdated) {
			msg= messages.get("AppConstants.PLAN_UPDATE_SUCC");
		}
		else {
			msg=messages.get("AppConstants.PLAN_UPDATE_FAIL");
			
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/plan{planId}")
	public ResponseEntity<String>deletePlan(@PathVariable Integer PlanId){
		boolean isDeleted = planService.deletePlanById(PlanId);
		String msg="AppConstants.EMPTY_STR";
		
		if(isDeleted) {
			
			msg= messages.get("AppConstants.PLAN_DELETE_SUCC");
		}
			else {
				msg=messages.get("AppConstants.PLAN_DELETE_FAIL");
			}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		}


	@PutMapping("/status-change/{planId}/{status}")	
	public ResponseEntity<String>statusChange(@PathVariable Integer planId,@PathVariable String status){
		boolean isStatusChanged = planService.planStatusChange(planId, status);
		String msg="";
		
		if(isStatusChanged) {
			msg=messages.get("AppConstants.PLAN_STATUS_CHANGE_SUCC");
		}
		else {
			msg=messages.get("AppConstants.PLAN_STATUS_CHANGE_FAIL");
			
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
}


