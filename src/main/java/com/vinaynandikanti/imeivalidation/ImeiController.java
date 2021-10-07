package com.vinaynandikanti.imeivalidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImeiController {

    private final ImeiService imeiService;

    @Autowired
    public ImeiController(ImeiService imeiService) {
        this.imeiService = imeiService;
    }


    @RequestMapping(value = "/result", method = {RequestMethod.GET, RequestMethod.POST})
    public String computeImei(Model model, @RequestParam String digitSequence) {
        int checkDigit = imeiService.getCheckDigit(digitSequence.substring(0,14));
        int lastDigit = Integer.valueOf(digitSequence.substring(14));
        model.addAttribute("digitSequence", digitSequence);
        model.addAttribute("checkDigit", checkDigit);
        model.addAttribute("isValid", imeiService.validateTheNumber(checkDigit,lastDigit));
        return "result";
    }
}
