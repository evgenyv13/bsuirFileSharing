package com.filesharing.mvc.mvcModel;

import com.filesharing.account.model.File;
import com.filesharing.account.model.User;
import com.filesharing.account.service.FileService;
import com.filesharing.account.service.FileServiceImpl;
import com.filesharing.account.service.SecurityService;
import com.filesharing.account.service.UserService;
import com.filesharing.account.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.lang.Math.toIntExact;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecurityService securityService;

    private FileService fileService;

    @Autowired(required=true)
    @Qualifier(value="fileService")
    public void setFileService(FileService ps){
        this.fileService = ps;
    }


    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(final HttpServletRequest request,Model model) {
        int userId = toIntExact(userService.findByUsername(request.getUserPrincipal().getName().toString()).getId()) ;

        List<File> fileList = fileService.listFilesOfUser(userId);
        model.addAttribute("listFiles",fileList);
        model.addAttribute("uId",userId);
        model.addAttribute("uploadedFiles",fileList.size());
        model.addAttribute("username",request.getUserPrincipal().getName().toString());
        return "account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String accountPassChange(final HttpServletRequest request,Model model,@RequestParam("oldPass") String oldPass,@RequestParam("newPass") String newPass,@RequestParam("newPassConfirm") String newPassConfirm) {
        int userId = toIntExact(userService.findByUsername(request.getUserPrincipal().getName().toString()).getId()) ;
        User currUser = userService.findByUsername(request.getUserPrincipal().getName().toString());


        if(oldPass.isEmpty()==false && newPass.isEmpty()==false && newPassConfirm.isEmpty()==false){
            if((oldPass.length()<6 || oldPass.length()>32) || (newPass.length()<6 || newPass.length()>32) || (newPassConfirm.length()<6 || newPassConfirm.length()>32)){
                if ((oldPass.length()<6 || oldPass.length()>32)) model.addAttribute("oldPassmsg","Please use between 6 and 32 characters.");
                if ((newPass.length()<6 || newPass.length()>32)) model.addAttribute("newPassmmsg","Please use between 6 and 32 characters.");
                if((newPassConfirm.length()<6 || newPassConfirm.length()>32))  model.addAttribute("newPassConfirmmsg","Please use between 6 and 32 characters.");
            }
            else{
                if(bCryptPasswordEncoder.matches(oldPass,currUser.getPassword())){
                    if(newPass.equals(newPassConfirm)){
                        currUser.setPassword(newPass);
                        userService.save(currUser);
                        return "redirect:/logout";
                    }
                    else{
                        model.addAttribute("newPassConfirmmsg","This passwords do not match");
                        model.addAttribute("newPassmmsg","This passwords do not match");
                    }
                }
                else{
                    model.addAttribute("oldPassmsg","Incorrect password");
                }
            }
        }
        else{
            if(oldPass.isEmpty()==true) model.addAttribute("oldPassmsg","This field is required.");
            if(newPassConfirm.isEmpty()==true) model.addAttribute("newPassConfirmmsg","This field is required.");
            if(newPass.isEmpty()==true) model.addAttribute("newPassmmsg","This field is required.");

        }

        List<File> fileList = fileService.listFilesOfUser(userId);
        model.addAttribute("listFiles",fileList);
        model.addAttribute("uId",userId);
        model.addAttribute("uploadedFiles",fileList.size());
        model.addAttribute("username",request.getUserPrincipal().getName().toString());
        return "account";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/data/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Неверный логин или пароль");

        if (logout != null)
            model.addAttribute("message", "Вы успешно вышли!");
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "index";
    }

}