package esanchez.devel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import esanchez.devel.app.model.Coupon;
import esanchez.devel.app.repository.CouponRepository;

@Controller
public class CouponController2 {

	@Autowired
	private CouponRepository couponRepository;
	
	@GetMapping("/createCouponForm")
	public String show() {
		return "createCouponForm";
	}
	
	@PostMapping("/saveCoupon")
	public String save(Coupon coupon) {
		couponRepository.save(coupon);
		return "createResponse";
	}
	
	@GetMapping("/showCoupon")
	public String showGetCoupon() {
		return "getCoupon";
	}
	
	@PostMapping("/getCoupon") 
	public ModelAndView getCoupon(String code) {
		ModelAndView mav = new ModelAndView("couponDetails");
		mav.addObject(couponRepository.findByCode(code));
		return mav;
	}
}
