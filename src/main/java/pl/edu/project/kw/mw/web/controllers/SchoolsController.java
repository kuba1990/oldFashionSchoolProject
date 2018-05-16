package pl.edu.project.kw.mw.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.project.kw.mw.model.School;
import pl.edu.project.kw.mw.persistence.DatabaseConnector;

import javax.servlet.http.HttpSession;

@Controller
public class SchoolsController {

    @RequestMapping(value = "/Schools")
    public String listStudents(Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        model.addAttribute("schools", DatabaseConnector.getInstance().getSchools());

        return "schoolsList";
    }

    @RequestMapping(value = "/AddSchool")
    public String displayAddSchoolForm(Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        return "schoolForm";
    }

    @RequestMapping(value = "/CreateSchool", method = RequestMethod.POST)
    public String createSchool(@RequestParam(value = "schoolName", required = false) String name,
                               @RequestParam(value = "schoolAddress", required = false) String address,
                               Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        School school = new School();
        school.setName(name);
        school.setAddress(address);

        DatabaseConnector.getInstance().addSchool(school);
        model.addAttribute("schools", DatabaseConnector.getInstance().getSchools());
        model.addAttribute("message", "new school is added");

        return "schoolsList";
    }

    @RequestMapping(value = "/DeleteSchool", method = RequestMethod.POST)
    public String deleteSchool(@RequestParam(value = "schoolId", required = false) String schoolId,
                               Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        DatabaseConnector.getInstance().deleteSchool(schoolId);
        model.addAttribute("schools", DatabaseConnector.getInstance().getSchools());
        model.addAttribute("message", "scholl was deleted");

        return "schoolsList";
    }


}