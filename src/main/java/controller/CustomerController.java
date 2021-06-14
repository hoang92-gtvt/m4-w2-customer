package controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CustomerService;
import service.ICustomerService;


import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    ICustomerService customerService = new CustomerService();


    @GetMapping("/home")
    public ModelAndView viewHome(){
        ModelAndView mav = new ModelAndView("/homePage");
        mav.addObject("customers", customerService.findAll());
        return mav;
    }

    @GetMapping ("/{id}/edit")
    public ModelAndView showFormEdit(@PathVariable int id){
        ModelAndView mav = new ModelAndView("/edit");
        mav.addObject("customer", customerService.findById(id));
        return mav;
    }

//    @PostMapping("/edit")
//    public ModelAndView edit(Customer customer){
//        ModelAndView modelAndView = new ModelAndView("/homePage");
//        customerService.update(customer.getId(), customer);
//
//        modelAndView.addObject("customers", customerService.findAll());
//        return modelAndView;
//    }
    @PostMapping("/edit")
    public String edit(Customer customer, RedirectAttributes redirect){
        customerService.update(customer.getId(), customer);

        redirect.addFlashAttribute("success", "Modified customer successfully!");
        return "redirect:/customer/home";
    }

    @GetMapping("/create")
    public String showFormCreate(){
        return "/create";
    }

    @PostMapping("/create")
    public ModelAndView create(Customer customer){
        customerService.save(customer);
        return viewHome();
    }

//    @PostMapping("/create")
//    public void create(Customer customer){
//        customerService.save(customer);
//        viewHome();
//    }

    @GetMapping("/delete")
    public String delete(int id){
        customerService.remove(id);
        return "redirect:/customer/home";
    }

}
