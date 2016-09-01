package com.niit.ShoppingCart;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ShoppingCart.DAO.LoginDAO;
import com.niit.ShoppingCart.DAO.SupplierDAO;
import com.niit.ShoppingCart.DAO.UserDetailsDAO;
import com.niit.ShoppingCart.model.Login;
import com.niit.ShoppingCart.model.Supplier;
import com.niit.ShoppingCart.model.Login;
import com.niit.ShoppingCart.model.UserDetails;

@Controller
public class HomeController 
{
	@Autowired
	private UserDetailsDAO ud;
	
	@Autowired
	SupplierDAO sd;
	
	private LoginDAO ld;
	@RequestMapping("/")
	public ModelAndView home()
	{
		ModelAndView m1=new ModelAndView("Home");
		return m1;
	}
	
	@RequestMapping("Register")
	public ModelAndView register()
	{
		ModelAndView m1=new ModelAndView("Register");
		return m1;
	}
	@RequestMapping("Login")
	public ModelAndView Login()
	{
		ModelAndView m1=new ModelAndView("Login");
		return m1;
	}
	@ModelAttribute("UserDetails")
	public UserDetails registerUser() {
		return new UserDetails();

	}
	
	@RequestMapping(value = "storeUser", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("UserDetails") UserDetails registeruser,BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Errors");
			return "register";
		}
		System.out.println(registeruser.getUsername());
		ud.save(registeruser);
		Login loginuser = new Login();
		loginuser.setId(registeruser.getId());
		loginuser.setUsername(registeruser.getUsername());
		loginuser.setPassword(registeruser.getPassword());
		loginuser.setStatus(registeruser.isStatus());
		ld.save(loginuser);
		return "register";
	}
    @ModelAttribute("Login")
    public Login createuser(){
    	return new Login();
    }
    
    @RequestMapping("/checkuser")
    public ModelAndView chackedUser(@Valid @ModelAttribute("Login")Login user,BindingResult result,@RequestParam("userName") String userName,@RequestParam("password")String password) 
    { 
    	System.out.println("UserName is............."+userName);
    	System.out.println("Password is............."+password);
		return null;
    	
    }
    @RequestMapping("addsupplier")
	public ModelAndView display3() {

		ModelAndView mv3 = new ModelAndView("addsupplier");
		return mv3;
	}
    @RequestMapping("storesupplier")
	public String addBook(HttpServletRequest request, @Valid @ModelAttribute("Supplier") Supplier supplier,
			BindingResult result) {
		if (result.hasErrors()) {
			return "addsupplier";
		}
		sd.save(supplier);
		return "ManageSuppliers";

	}
	
}