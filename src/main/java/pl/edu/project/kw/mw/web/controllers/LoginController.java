package pl.edu.agh.ki.mw.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = {"/", "/Login"}, method = RequestMethod.GET)
    public String displayLoginForm() {
        return "loginForm";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String doLogin(@RequestParam(value = "login") String login, Model model, HttpSession session) {
        session.setAttribute("userLogin", login);
        return "redirect:/Welcome";
    }

    @RequestMapping(value = "/Welcome")
    public String welcome(Model model, HttpSession session) {
        model.addAttribute("message", "Witamy w systemie Szkoła!");
        return "welcome";
    }

    @RequestMapping(value = "/logout")
    public String logout(Model model, HttpSession session) {
        return "redirect:/Login";
    }

}