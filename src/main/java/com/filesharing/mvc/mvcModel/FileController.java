package com.filesharing.mvc.mvcModel;

import com.filesharing.account.model.File;
import com.filesharing.account.service.FileService;
import com.filesharing.account.service.UserService;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Math.toIntExact;

@Controller
public class FileController {

    @Autowired
    private UserService userService;

    private FileService fileService;

    @Autowired(required=true)
    @Qualifier(value="fileService")
    public void setFileService(FileService ps){
        this.fileService = ps;
    }

    @RequestMapping("/remove/{id}")
    public String removeFile(HttpServletRequest request,@PathVariable("id") int id){

        int userId=-1;


        try{
            userId = toIntExact(userService.findByUsername(request.getUserPrincipal().getName().toString()).getId()) ;
        }
        catch (Throwable t){
            return "redirect:" + request.getHeader("referer");
        }

        this.fileService.removeFile(id,userId);



        return "redirect:" + request.getHeader("referer");
    }
}
