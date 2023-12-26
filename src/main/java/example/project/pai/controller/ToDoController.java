package example.project.pai.controller;

import example.project.pai.entity.ToDo;
import example.project.pai.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class ToDoController {


    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }


    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap modelMap){
        modelMap.addAttribute("todos", toDoService.getAllToDos());

        return "listToDo";
    }


    //GET, POST
    @GetMapping("/add-todo")
    public String showNewToDoPage(ModelMap modelMap){

        modelMap.put("todo", toDoService.generateEmptyToDo());
        return "todo";
    }

    @PostMapping("/add-todo")
    public String addNewToDoPage(@ModelAttribute("todo") ToDo todo,
                                 BindingResult result,
                                 Model model){

        if (todo.getDescription().length() < 5 || todo.getDescription().isBlank()) {
            result.rejectValue("description", null, "Enter at least 5 characters");
        }

        if (result.hasErrors()) {
            model.addAttribute("todo", todo);
            return "todo";
        }

        toDoService.addToDo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping("/delete-todo")
    public String deleteToDo(@RequestParam long id){
        toDoService.deleteToDo(id);
        return "redirect:/list-todos";
    }

    @GetMapping("/update-todo")
    public String showUpdateToDoPage(@RequestParam long id, ModelMap modelMap){
        modelMap.addAttribute("todo", toDoService.getToDo(id));
        return "todo";
    }

    @PostMapping("/update-todo")
    public String updateToDoPage(ToDo todo, BindingResult result){

        if(result.hasErrors()) return "todo";

        toDoService.addToDo(todo);
        return "redirect:/list-todos";
    }
}
