package net.enjoy.springboot.registrationlogin.controller;

import jakarta.validation.Valid;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ToDoController {


    private final ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap){

        List<ToDo> todos =  toDoRepository.findByUsername("Karol");
        modelMap.addAttribute("todos", todos);

        return "listToDo";
    }


    //GET, POST
    @GetMapping("add-todo")
    public String showNewToDoPage(ModelMap modelMap){
        ToDo todo = new ToDo(0, "Karol", "",
                LocalDate.now().plusYears(1), false);
        modelMap.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewToDoPage(@Valid ToDo todo, BindingResult result){

        if(result.hasErrors()) {
            return "todo";
        }

        todo.setUsername("Karol");
//        todo.setUsername(getLoggedInUsername());
        toDoRepository.save(todo);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteToDo(@RequestParam int id){
        toDoRepository.deleteById(id);
        return "redirect:list-todos";
    }


    //GET, POST
    @GetMapping(value = "update-todo")
    public String showUpdateToDoPage(@RequestParam int id, ModelMap modelMap){
        var todo = toDoRepository.findById(id).get();
        modelMap.addAttribute("todo", todo);
        return "todo";
    }

    @PostMapping(value = "update-todo")
    public String updateToDoPage(@Valid ToDo todo, BindingResult result){

        if(result.hasErrors()) return "todo";

        todo.setUsername("Karol");
//        todo.setUsername(getLoggedInUsername());
        toDoRepository.save(todo);

        return "redirect:list-todos";
    }

//    private static String getLoggedInUsername() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
//    }

}
