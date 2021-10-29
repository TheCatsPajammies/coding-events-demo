package org.launchcode.codingevents.controllers;


import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//testComment
@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    // You can use the @RequestMapping annotation to map to "eventCategories". To get our handlers working, we also need a variable of type EventCategoryRepository.
    // We will be creating 3 handlers in our controller:

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    // displayAllEvents
    @GetMapping
    private String displayAllEvents(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }

    // renderCreateEventCategoryForm
    @GetMapping("create")
    public String renderCreateEventCategoryForm (Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }


    // processCreateEventCategoryForm
    @PostMapping("create")
    public String processCreateEventCategoryForm(@Valid @ModelAttribute EventCategory eventCategory, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute(new EventCategory());
            return "eventCategories/create";
        }

        eventCategoryRepository.save(eventCategory);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteCategoriesForm(Model model) {
        model.addAttribute("title", "Delete Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/delete";
    }

    @PostMapping("delete")
    public String processDeleteCategoriesForm(@RequestParam(required = false) int[] categoryIds) {

        if (categoryIds != null) {
            for (int id : categoryIds) {
                eventCategoryRepository.deleteById(id);
            }
        }

        return "redirect:";
    }







}
