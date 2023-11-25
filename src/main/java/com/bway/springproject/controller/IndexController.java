package com.bway.springproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Cart;
import com.bway.springproject.model.Product;
import com.bway.springproject.model.Sell;
import com.bway.springproject.model.Slider;
import com.bway.springproject.model.User;
import com.bway.springproject.repository.UserRepository;
import com.bway.springproject.service.CartService;
import com.bway.springproject.service.IUserService;
import com.bway.springproject.service.ProductService;
import com.bway.springproject.service.SellService;
import com.bway.springproject.service.SliderService;
import com.bway.springproject.util.MailUtils;
import com.bway.springproject.util.PasswordGenerator;

@Controller
public class IndexController {
	
	@Autowired
	private PasswordGenerator  passwordGenerator;
	
	@Autowired
	private UserRepository  userRepo;

    @Autowired
    private ProductService proService;

	
	@Autowired
	private IUserService  userService; 

	
	@Autowired
	private SliderService sliderservice;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private SellService sellService;
	
	@Autowired
	private MailUtils mailutils; 
	
	
	@GetMapping("/")
	public String gethome(Model model) {
	    List<Slider> sliders = sliderservice.getAllSlis();
	    model.addAttribute("sliders", sliders);
		return "/Index";
	}
	
	@GetMapping("/Login")
	public String getLogin() {
		return "LoginForm";
	}
	
	
	@PostMapping("/Login")
	public String postLogin(@ModelAttribute User user, Model model, HttpSession session){
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		User  usr = userService.login(user.getUsername(), user.getPassword());
		
		if(usr != null) {
			session.setAttribute("validuser", usr);
			session.setMaxInactiveInterval(600);	

		    List<Slider> sliders = sliderservice.getAllSlis();
		    model.addAttribute("sliders", sliders);
			return "Index";
		}else {
			model.addAttribute("message","user not found sorry!!");
			return  "LoginForm";
		}
		
	}
	
	
	@GetMapping("/Signup")
	public String getSignup() {
		return "SignupForm";
	}
	
	@PostMapping("/Signup")
	public String postSignup(@RequestParam("username") String username,@ModelAttribute User user,Model model) {
	    user.setUsername(username);
	    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
	    if (userService.isUserExists(user.getUsername()) != null) {
	        model.addAttribute("message", "User already exists");
	        return "SignupForm"; 
	    }
	    userService.signup(user);
	    return "LoginForm";
	}
	
	@GetMapping("/Profile")
	public String getProfile(Model model,HttpSession session, @ModelAttribute User user) {

        if (session.getAttribute("validuser") == null) {
            return "LoginForm";
        }
	    model.addAttribute("data1", user.getFname());
	    model.addAttribute("data2", user.getLname());
	    model.addAttribute("data3", user.getGender());
	    model.addAttribute("data4", user.getEmail());
	    model.addAttribute("data5", user.getUsername());
	    model.addAttribute("data6", user.getPassword());
		return "Profile";
	}
	
	@GetMapping("/Index")
	public String getHome(Model model) {

	    List<Slider> sliders = sliderservice.getAllSlis();
	    model.addAttribute("sliders", sliders);
		return "Index";
	}	
	
	@GetMapping("/Contact")
	public String getcontact() {
		return "Contact";
	}
	
	@PostMapping("/Contact")
	public String postContact(@RequestParam String email, @RequestParam String message, Model model) {
		mailutils.sendEmail(email, "Subject", message);  
	    return "Contact";  
	}
	

	@GetMapping("/Cart")
	public String viewCart(HttpSession session, Model model, User user) {
	    if (session.getAttribute("validuser") == null) {
	        return "LoginForm";
	    }

	    List<Cart> cartItems = cartService.getAllCart(); // Retrieve cart items from the database
	    
	    model.addAttribute("cartItems", cartItems);
	    return "cart"; // Return the name of your cart.html template
	}



	
	@PostMapping("/addCart")
	public String addToCart(
	        @RequestParam("productId") int productId,
	        @RequestParam("quantity") int quantity) {
	    
	    // Find the product from the database using productId
	    Product product = proService.getProById(productId);
	    
	    if (product != null) {
	        Cart cartItem = new Cart();
	        cartItem.setQuantity(quantity);
	        cartItem.setProduct(product);

	        cartItem.setAmount(quantity * product.getPrice()); // Calculate totalAmount here
	            
	        // Save the cart item to the database
	        cartService.addCart(cartItem);
	    }

	    // Redirect to a relevant page after adding to cart
	    return "redirect:/Cart"; // Replace with your desired URL
	}

	@GetMapping("/Clear")
	public String getclear() {
		cartService.deleteAllCarts();
		return "Cart";
	}
	
	   @GetMapping("/Checkout")
	    public String getSell(HttpSession session) {
	        User user = (User) session.getAttribute("validuser");

	        if (user == null) {
	            return "LoginForm";
	        }

	        List<Cart> cartItems = cartService.getAllCart();
	        double totalAmount = 0.0;

	        // Calculate totalAmount and process checkout

	        for (Cart cartItem : cartItems) {
	            totalAmount += cartItem.getAmount();
	            Sell sell = new Sell();
	            sell.setProduct(cartItem.getProduct());
	            sell.setQuantity(cartItem.getQuantity());
	            sell.setAmount(cartItem.getAmount());
	            sell.setUser(user);
	            sellService.addSell(sell);
	        }


	        cartService.deleteAllCarts();
	        return "Cart";
	    }

	
		@GetMapping("/forgetpassword")
		public String getForgetpass() {
			return "ForgetPassword";
		}
		
		@PostMapping("/forgetpassword")
		public String postForgetpass(@RequestParam String email, User user, Model model) {
			
			System.out.println(email);
			
			List<User> use = userService.getAllInfo();
			System.out.println(use);
			String pass = passwordGenerator.getNewPassword();
		    String encodedPsw = DigestUtils.md5DigestAsHex(pass.getBytes());
		   
		    userRepo.updatePasswod(encodedPsw, email);
		    
		    mailutils.sendEmail(email,pass);
		    //database password change.....by id
			return "ForgetPassword";
		}
		
	
	@GetMapping("/About")
	public String getabout() {
		return "About";
	}
}
