package pl.edu.project.kw.mw.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.project.kw.mw.model.Student;
import pl.edu.project.kw.mw.persistence.DatabaseConnector;
import javax.servlet.http.HttpSession;

@Controller
public class StudentsController {

    @RequestMapping(value = "/students")
    public String listStudents(Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        model.addAttribute("students", DatabaseConnector.getInstance().getStudent());

        return "studentList";
    }

    @RequestMapping(value = "/AddStudent")
    public String displayAddSchoolForm(Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        return "studentForm";
    }

    @RequestMapping(value = "/CreateStudent", method = RequestMethod.POST)
    public String createSchool(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "surname", required = false) String surname,
                               @RequestParam(value = "pesel", required = false) String pesel,
                               Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setPesel(pesel);

        DatabaseConnector.getInstance().addStudent(student);

        model.addAttribute("students", DatabaseConnector.getInstance().getStudent());
        model.addAttribute("message", "new students was added");

        return "studentList";
    }

    @RequestMapping(value = "/DeleteStudent", method = RequestMethod.POST)
    public String deleteStudent(@RequestParam(value = "studentId", required = false) String studentId,
                                Model model, HttpSession session) {
        if (session.getAttribute("userLogin") == null)
            return "redirect:/Login";

        DatabaseConnector.getInstance().deleteStudent(studentId);
        model.addAttribute("students", DatabaseConnector.getInstance().getStudent());
        model.addAttribute("message", "students was deleted");

        return "studentList";
    }

}