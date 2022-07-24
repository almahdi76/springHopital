package com.HOPITALL.HOPITALL.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.HOPITALL.HOPITALL.entities.Patient;
import com.HOPITALL.HOPITALL.repositiry.PateintRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PatientController {
    private final PateintRepository pateintRepository;
// public  PatientController(PateintRepository pateintRepository) {
//     this.pateintRepository=pateintRepository;
//}

@GetMapping(path = "/user/index")  // path url
public String patient(Model model,
        @RequestParam(name="page", defaultValue ="0") int page,
        @RequestParam(name="size", defaultValue ="5") int size,
        @RequestParam(name="keyword", defaultValue ="") String keyword
        ){

    // Page <Patient> patients=pateintRepository.findAll(PageRequest.of(page, size));
    // model.addAttribute("ListPatients",patients.getContent());
    // model.addAttribute("pages",new int [patients.getTotalPages()]);
    // model.addAttribute("carruntpage",page);
    // return "patients";  // page web
    Page <Patient> patients=pateintRepository.findByNameContains(keyword,PageRequest.of(page, size));
    model.addAttribute("ListPatients",patients.getContent());
    model.addAttribute("pages",new int [patients.getTotalPages()]);
    model.addAttribute("carruntpage",page);
    model.addAttribute("keyword",keyword);
    return "patients";  // page web
 
}
@GetMapping("/admin/delete")
public String deletPatient(Long id,String keyword,int page){
    pateintRepository.deleteById(id);
    return "redirect:/user/index?page="+page+"&keyword="+keyword;
}

@GetMapping("/")
public String home(){
    
    return "home";
}
@GetMapping("/user/patients")
@ResponseBody
public List <Patient> listPatients(){
    return pateintRepository.findAll();
}

//@RequestMapping(value="/form",method=RequestMethod.GET)
@GetMapping(path = "/admin/formPatients")
public String formPatients(Model model){
   
    model.addAttribute("patient",new Patient());
    return "formPatients";
}

@PostMapping(path = "/admin/save")
public String save(Model model,@Valid Patient patient,BindingResult bindingResult,
    @RequestParam(defaultValue = "")String keyword,
    @RequestParam(defaultValue = "0")int page){
    if(bindingResult.hasErrors()) return "formPatients";
    pateintRepository.save(patient);
    return "redirect:/user/index?page="+page+"&keyword="+keyword;
}

@GetMapping(path = "/admin/editPatient")
public String editPatient(Model model,Long id,
    String keyword,int page){
   Patient patient=pateintRepository.findById(id).orElse(null);
   if(patient==null) throw new RuntimeException("patient non trouvé"); 
    model.addAttribute("patient",patient);
    model.addAttribute("page",page);
    model.addAttribute("keyword",keyword);
    return "editPatient";
}

    
}
