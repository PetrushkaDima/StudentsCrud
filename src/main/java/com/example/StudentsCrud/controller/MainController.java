package com.example.StudentsCrud.controller;

import com.example.StudentsCrud.model.Student;
import com.example.StudentsCrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Transactional
public class MainController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getAllStudents(Model model) {
        Iterable<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "main";
    }

    @PostMapping("/add")
    public String createStudent(@RequestParam String lastName,
                                @RequestParam String firstName,
                                @RequestParam String middleName,
                                @RequestParam String birthday,
                                @RequestParam String groupId,
                                @RequestParam String uniqueNumber,
                                Model model
    ) {
        Student student = new Student();
        if (lastName.isEmpty()
                || firstName.isEmpty()
                || birthday.isEmpty()
                || groupId.isEmpty()
                || uniqueNumber.isEmpty()
        ) {
            model.addAttribute("errorMessage", "Некоторые поля пусты");
            return "error";
        } else if (studentService.findStudentByUniqueNumber(uniqueNumber).isPresent()) {
            model.addAttribute("errorMessage", "Поле \" Уникальный номер\" должно быть уникальным");
            return "error";
        } else {
            student.setLastName(lastName);
            student.setFirstName(firstName);
            student.setMiddleName(middleName);
            student.setBirthday(LocalDate.parse(birthday));
            student.setGroupId(groupId);
            student.setUniqueNumber(uniqueNumber);
            studentService.createStudent(student);
            return "redirect:/";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String uniqueNumber) {
        studentService.deleteStudentByUniqueNumber(uniqueNumber);
        return "redirect:/";
    }
}
