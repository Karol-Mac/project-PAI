package example.project.pai.controller;

import jakarta.validation.Valid;
import example.project.pai.entity.ToDo;
import example.project.pai.service.ToDoService;
import org.springframework.stereotype.Controller;
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


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap){
        modelMap.addAttribute("todos", toDoService.getAllToDos());

        return "listToDo";
    }


    //GET, POST
    @GetMapping("add-todo")
    public String showNewToDoPage(ModelMap modelMap){

        modelMap.put("todo", toDoService.generateEmptyToDo());
        return "todo";
    }

    @PostMapping(value = "add-todo")
    public String addNewToDoPage(@Valid ToDo todo, BindingResult result){

        if(result.hasErrors()) {
//            LoggerFactory.getLogger(ToDoController.class).warn("Sth wrong here");
            return "todo";
        }

        toDoService.addToDo(todo);
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteToDo(@RequestParam long id){
        toDoService.deleteToDo(id);
        return "redirect:list-todos";
    }

    @GetMapping(value = "update-todo")
    public String showUpdateToDoPage(@RequestParam long id, ModelMap modelMap){
        modelMap.addAttribute("todo", toDoService.getToDo(id));
        return "todo";
    }

    @PostMapping(value = "update-todo")
    public String updateToDoPage(@Valid ToDo todo, BindingResult result){

        if(result.hasErrors()) return "todo";

        toDoService.addToDo(todo);
        return "redirect:list-todos";
    }
}
