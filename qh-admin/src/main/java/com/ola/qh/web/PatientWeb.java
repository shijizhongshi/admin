package com.ola.qh.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web/patientmanage")
public class PatientWeb {

	@RequestMapping("/knowledgep")
	public String courseOrders(){
		return "patientmanage/knowledgep";
	}
	@RequestMapping(value="/patient",method=RequestMethod.GET)
	public String patient(){
		return "patientmanage/patient";
	}
	
	@RequestMapping(value="/replypatient",method=RequestMethod.GET)
	public String replypatient(@RequestParam(name="id") String id,HttpServletRequest request){
		request.getSession().setAttribute("id", id);
		return "patientmanage/replypatient";
	}
	
	@RequestMapping(value="/uservideo",method=RequestMethod.GET)
	public String uservideo(){
		return "patientmanage/uservideo";
	}
	
	@RequestMapping(value="/videopatient",method=RequestMethod.GET)
	public String videopatient(@RequestParam(name="vid") String vid,HttpServletRequest request){
		request.getSession().setAttribute("vid", vid);
		return "patientmanage/videopatient";
	}
}

