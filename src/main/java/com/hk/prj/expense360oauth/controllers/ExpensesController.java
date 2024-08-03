package com.hk.prj.expense360oauth.controllers;

import com.hk.prj.expense360oauth.model.AppUser;
import com.hk.prj.expense360oauth.model.Expense;
import com.hk.prj.expense360oauth.service.ExpenseService;
import com.hk.prj.expense360oauth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExpensesController {

    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpensesController(ExpenseService expenseService, UserService userService){
        this.expenseService = expenseService;
        this.userService = userService;
    }



    @RequestMapping(value =  "/expenses", method = RequestMethod.GET)
    public ModelAndView expenseview(){
        List<Expense> expenseList = expenseService.getExpensesByUser();
        Double totalExpense = expenseList.stream().map(Expense::getAmount).reduce(Double::sum).orElse(0.0);
        ModelAndView model = new ModelAndView("expenses");
        model.addObject("duration","04/2024");
        model.addObject("totalExpense", totalExpense.toString().formatted("%.2f"));
        model.addObject("expenses", expenseList);
        return model;
    }

    @RequestMapping(value =  "/addexpenseview", method = RequestMethod.GET)
    public ModelAndView addExpenseView(){
        Expense expense = new Expense();
        ModelAndView model = new ModelAndView("addexpense");
        model.addObject("expense", expense);
        return model;
    }

    @RequestMapping(value="/addexpense", params={"edit"})
    public ModelAndView editExpense(final HttpServletRequest req) {
        final Integer index = Integer.valueOf(req.getParameter("edit"));
        Expense expense = this.expenseService.getExpensesByUser(index);
        ModelAndView model = new ModelAndView("addexpense");
        model.addObject("expense", expense);
        return model;
    }


    @RequestMapping(value="/addexpense", params={"add"})
    public String addExpense(final Expense expense, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "addexpenseview";
        }
        this.expenseService.save(expense);
        model.clear();
        return "redirect:/expenses";
    }

    @RequestMapping(value="/addexpense", params={"delete"})
    public String deleteExpense(final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("delete"));
        this.expenseService.delete(rowId);
        return "redirect:/expenses";
    }

    @RequestMapping(value =  "/myprofile", method = RequestMethod.GET)
    public String myprofile(Model model, @AuthenticationPrincipal OAuth2User user){
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "myprofile";
    }


}
