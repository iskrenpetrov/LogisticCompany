package bg.nbu.logistics.web.controllers;

import static bg.nbu.logistics.commons.constants.AuthorizationConstants.IS_AUTHENTICATED;

import java.time.LocalDate;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import bg.nbu.logistics.services.income.IncomeService;

@Controller
@RequestMapping("income")
public class IncomeController extends BaseController {
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    @PreAuthorize(IS_AUTHENTICATED)
    public ModelAndView fetchIncome(@RequestParam(name = "fromDate") String fromStr,
                                    @RequestParam (name = "toDate") String toStr, ModelAndView modelAndView) {
        LocalDate fromDate = LocalDate.parse(fromStr);
        LocalDate toDate = LocalDate.parse(toStr);
        final double income = incomeService.getIncomeByTimePeriod(fromDate, toDate);
        modelAndView.addObject("income", income);
        return view("income", modelAndView);
    }

    @GetMapping
    @PreAuthorize(IS_AUTHENTICATED)
    public ModelAndView fetchIncome(ModelAndView modelAndView) {
        return view("income");
    }
}
