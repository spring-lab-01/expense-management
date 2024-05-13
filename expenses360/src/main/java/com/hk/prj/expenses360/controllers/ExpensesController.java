package com.hk.prj.expenses360.controllers;

import com.hk.prj.expenses360.service.ExpenseService;
import com.hk.prj.expenses360.model.Expense;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExpensesController {

    private final ExpenseService expenseService;

    public ExpensesController(ExpenseService expenseService){
        this.expenseService = expenseService;
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

    @RequestMapping(value =  "/expenseCrudView", method = RequestMethod.GET)
    public ModelAndView addExpenseView(){
        Expense expense = new Expense();
        ModelAndView model = new ModelAndView("expenseCrud");
        model.addObject("expense", expense);
        return model;
    }

    @RequestMapping(value="/expenseCrud", params={"edit"})
    public ModelAndView editExpense(final HttpServletRequest req) {
        final Integer index = Integer.valueOf(req.getParameter("edit"));
        Expense expense = this.expenseService.getExpensesByUser(index);
        ModelAndView model = new ModelAndView("expenseCrud");
        model.addObject("expense", expense);
        return model;
    }


    @RequestMapping(value="/expenseCrud", params={"add"})
    public String addExpense(final Expense expense, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "addexpenseview";
        }
        this.expenseService.save(expense);
        model.clear();
        return "redirect:/expenses";
    }

    @RequestMapping(value="/expenseCrud", params={"delete"})
    public String deleteExpense(final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("delete"));
        this.expenseService.delete(rowId);
        return "redirect:/expenses";
    }

    @RequestMapping(value =  "/myprofile", method = RequestMethod.GET)
    public String myprofile(){
        return "myprofile";
    }


}
