package com.filesharing.mvc.mvcModel;

import com.filesharing.account.model.User;
import com.filesharing.account.service.*;
import com.filesharing.account.validator.Translit;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import static java.lang.Math.toIntExact;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/data")
public class DataController {

    private FileService fileService;

    @Autowired(required=true)
    @Qualifier(value="fileService")
    public void setFileService(FileService ps){
        this.fileService = ps;
    }

    @Autowired
    private UserService userService;

    @Autowired
    ServletContext context;

    /*next request mappings*/

    @RequestMapping(value ="/",method = RequestMethod.GET)
    public String processForm(HttpServletRequest request, HttpServletResponse response, Model model) {

        String pathString = new String(MainController.FOLDER_NAME);

        /*test for none folder*/
        String fileUrl = "";
        downloadFileIfNotFolder(response,fileUrl,pathString);
        /*if not file continue*/

        ArrayList<FileObjects> folderArray = new ArrayList<FileObjects>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Номер Специальности");
        model.addAttribute("foldername","главная");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/",method = RequestMethod.POST)
    public String processForm(@RequestParam("file") MultipartFile file,@RequestParam("dirName") String dirName, Model model, HttpServletRequest request) {

        String pathString = new String(MainController.FOLDER_NAME);
        String fileUrl = "";

        uploadFileHandler(request,fileUrl,file,dirName);

        ArrayList<FileObjects> folderArray = new ArrayList<FileObjects>();
        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("fileList",folderArray);
        model.addAttribute("foldername","главная");
        model.addAttribute("nowTimeFolder","Номер Специальности");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/",method = RequestMethod.GET)
    public String processForm(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable String specialty) {

        String pathString = new String(MainController.FOLDER_NAME +specialty);

        /*test for none folder*/
        String fileUrl = specialty;
        downloadFileIfNotFolder(response,fileUrl,pathString);
        /*if not file continue*/

        ///////// ---------------------------------------
       // userService.findByUsername(request.getUserPrincipal().getName().toString()).getId()
        ///////// ---------------------------------------


        ArrayList<FileObjects> folderArray = new ArrayList<FileObjects>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Номер Семестра");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/",method = RequestMethod.POST)
    public String processForm(@RequestParam("file") MultipartFile file,@RequestParam("dirName") String dirName, Model model, HttpServletRequest request, @PathVariable String specialty) {

        String pathString = new String(MainController.FOLDER_NAME +specialty);
        String fileUrl = specialty;

        uploadFileHandler(request,fileUrl,file,dirName);

        ArrayList<FileObjects> folderArray = new ArrayList<FileObjects>();
        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Номер Семестра");

        return "dataView/dataList";
    }

    @RequestMapping(value = "/{specialty}/{semester}/",method = RequestMethod.GET)
    public String processForm(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable String specialty,@PathVariable String semester) {
        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester);

        /*test for none folder*/
        String fileUrl = specialty+"/"+ semester;
        downloadFileIfNotFolder(response,fileUrl,pathString);
        /*if not file continue*/


        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Предмет");

        return "dataView/dataList";
    }

    @RequestMapping(value = "/{specialty}/{semester}/",method = RequestMethod.POST)
    public String processForm(@RequestParam("file") MultipartFile file,@RequestParam("dirName") String dirName, Model model, HttpServletRequest request,@PathVariable String specialty,@PathVariable String semester) {
        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester); // full url of project
        String fileUrl = specialty+"/"+ semester; // only path url from /data/

        uploadFileHandler(request,fileUrl,file,dirName);

        /*update page*/
        ArrayList<FileObjects> folderArray = new ArrayList<>();
        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Предмет");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/",method = RequestMethod.GET)
    public String processForm(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+ "/" +subjectId);

        /*test for none folder*/
        String fileUrl = specialty+"/"+ semester + "/" + subjectId;
        downloadFileIfNotFolder(response,fileUrl,pathString);
        /*if not file continue*/

        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Перподаватель");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/",method = RequestMethod.POST)
    public String processForm(@RequestParam("file") MultipartFile file,@RequestParam("dirName") String dirName, Model model, HttpServletRequest request, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+ "/" +subjectId);
        String fileUrl = specialty+"/"+ semester + "/" + subjectId;

        uploadFileHandler(request,fileUrl,file,dirName);

        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Перподаватель");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/{professor}/",method = RequestMethod.GET)
    public String processForm(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId,@PathVariable String professor) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+"/"+subjectId + "/" + professor);

        /*test for none folder*/
        String fileUrl = specialty+"/"+ semester+"/"+subjectId + "/" + professor;
        downloadFileIfNotFolder(response,fileUrl,pathString);
        /*if not file continue*/

        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("professor",professor);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Номер работы");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/{professor}/",method = RequestMethod.POST)
    public String processForm(@RequestParam("file") MultipartFile file,@RequestParam("dirName") String dirName, Model model, HttpServletRequest request, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId,@PathVariable String professor) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+"/"+subjectId + "/" + professor);
        String fileUrl = specialty+"/"+ semester+"/"+subjectId + "/" + professor;

        uploadFileHandler(request,fileUrl,file,dirName);

        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("professor",professor);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Номер работы");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/{professor}/{lrNum}/",method = RequestMethod.GET)
    public String processForm(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId,@PathVariable String professor,@PathVariable String lrNum) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+"/"+subjectId + "/" + professor + "/" + lrNum);

        /*test for none folder*/
        String fileUrl = specialty+"/"+ semester+"/"+subjectId + "/" + professor+ "/" + lrNum;
        downloadFileIfNotFolder(response,fileUrl,pathString);
        /*if not file continue*/

        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("professor",professor);
        model.addAttribute("lrNum",lrNum);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Номер Варианта");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/{professor}/{lrNum}/",method = RequestMethod.POST)
    public String processForm(@RequestParam("file") MultipartFile file,@RequestParam("dirName") String dirName, Model model, HttpServletRequest request, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId,@PathVariable String professor,@PathVariable String lrNum) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+"/"+subjectId + "/" + professor + "/" + lrNum);
        String fileUrl = specialty+"/"+ semester+"/"+subjectId + "/" + professor+ "/" + lrNum;

        uploadFileHandler(request,fileUrl,file,dirName);


        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("professor",professor);
        model.addAttribute("lrNum",lrNum);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Номер Варианта");

        return "dataView/dataList";
    }


    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/{professor}/{lrNum}/{variant}/",method = RequestMethod.GET)
    public String processForm(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId,@PathVariable String professor,@PathVariable String lrNum,@PathVariable String variant) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+"/"+subjectId + "/" + professor + "/" + lrNum + "/" + variant);

        /*test for none folder*/
        String fileUrl = specialty+"/"+ semester+"/"+subjectId + "/" + professor+ "/" + lrNum+ "/" + variant;
        downloadFileIfNotFolder(response,fileUrl,pathString);
        /*if not file continue*/

        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("professor",professor);
        model.addAttribute("lrNum",lrNum);
        model.addAttribute("variant",variant);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Решение");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/{professor}/{lrNum}/{variant}/",method = RequestMethod.POST)
    public String processForm(@RequestParam("file") MultipartFile file,@RequestParam("dirName") String dirName, Model model, HttpServletRequest request, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId,@PathVariable String professor,@PathVariable String lrNum,@PathVariable String variant) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+"/"+subjectId + "/" + professor + "/" + lrNum + "/" + variant);
        String fileUrl = specialty+"/"+ semester+"/"+subjectId + "/" + professor+ "/" + lrNum+ "/" + variant;

        uploadFileHandler(request,fileUrl,file,"");

        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("professor",professor);
        model.addAttribute("lrNum",lrNum);
        model.addAttribute("variant",variant);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Решение");

        return "dataView/dataList";
    }

    @RequestMapping(value ="/{specialty}/{semester}/{subjectId}/{professor}/{lrNum}/{variant}/{filename}/",method = RequestMethod.GET)
    public String processForm(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable String specialty,@PathVariable String semester,@PathVariable String subjectId,@PathVariable String professor,@PathVariable String lrNum,@PathVariable String variant,@PathVariable String filename) {

        String pathString = new String(MainController.FOLDER_NAME +specialty+"/"+semester+"/"+subjectId + "/" + professor + "/" + lrNum + "/" + variant + "/" + filename);

        /*test for none folder*/
        String fileUrl = specialty+"/"+ semester+"/"+subjectId + "/" + professor+ "/" + lrNum+ "/" + variant + "/" + filename;
        downloadFileIfNotFolder(response,fileUrl,pathString);
        /*if not file continue*/

        ArrayList<FileObjects> folderArray = new ArrayList<>();

        getFoldersArray(request,folderArray,pathString);

        model.addAttribute("speciality",specialty);
        model.addAttribute("semester",semester);
        model.addAttribute("subjectId",subjectId);
        model.addAttribute("professor",professor);
        model.addAttribute("lrNum",lrNum);
        model.addAttribute("variant",variant);
        model.addAttribute("fileList",folderArray);
        model.addAttribute("nowTimeFolder","Решение");

        return "dataView/dataList";
    }


    private void getFoldersArray(final HttpServletRequest request,ArrayList<FileObjects> folderArray,final String pathString) {

        ServletContext context = request.getServletContext(); // get Cntext to save path
        File userNameFolder = new File(context.getRealPath("/") + pathString); // saving full path of folder

        int userId = toIntExact(userService.findByUsername(request.getUserPrincipal().getName().toString()).getId()) ;

        if(userNameFolder.exists()){

            File[] files = userNameFolder.listFiles();
            //Arrays.sort(files);
            for (File file:files) {

                try{
                    folderArray.add(new FileObjects(file.getName(), FilenameUtils.getExtension(file.getName()) ) ) ;
                    int fileOwnerId = fileService.getFileByPath(file.getPath()).getFile_owner();
                    if(fileOwnerId==userId) folderArray.get(folderArray.size()-1).setFileOwner(true);
                    if(file.isDirectory()==false) folderArray.get(folderArray.size()-1).setFile(true);
                    folderArray.get(folderArray.size()-1).id = fileService.getFileByPath(file.getPath()).getId();

                }
                catch (Throwable t){
                    System.out.println("error to find file in DB" + file.getPath());
                }



            }

        }
        else{
            folderArray.add(new FileObjects("Директория не существует","" ));
        }

    }

    private void createFolders(final HttpServletRequest request,final String pathString) {

        ServletContext context = request.getServletContext();
        File userNameFolder = new File(context.getRealPath("/") + pathString);

        if(userNameFolder.exists() == false)
        {
            userNameFolder.mkdirs();
        }

    }

    private void downloadFileIfNotFolder(HttpServletResponse response, String fileUrl,String pathString){
        /*   contextTestForFile.getRealPath("/") +  pathString*/

        String downloadFolder = context.getRealPath(MainController.FOLDER_NAME);
        File file = new File(downloadFolder + "/" + fileUrl);

        if(!file.exists() || file.isDirectory()) {
            return;
        }

        try {

            if (file.exists()) {
                String mimeType = context.getMimeType(file.getPath());

                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                response.setContentType(mimeType);
                response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
                response.setContentLength((int) file.length());

                OutputStream os = response.getOutputStream();
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                int b = -1;

                while ((b = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, b);
                }

                fis.close();
                os.close();
            } else {
                System.out.println("Requested " + pathString + " file not found");
            }
        } catch (IOException e) {
            System.out.println("Error:- " + e.getMessage());
        }
    }

    String uploadFileHandler(HttpServletRequest request,String pathFromData,MultipartFile file,String dirName) {

        String downloadFolder = context.getRealPath(MainController.FOLDER_NAME);
        File dir;
        dirName = dirName.replaceAll("[^A-Za-zА-Яа-я0-9]", "");
        dirName = Translit.toTranslit(dirName);


        if (!dirName.isEmpty()){

            dir = new File(downloadFolder + "/" + pathFromData+"/"+dirName); // path to save file
            if(dir.exists() == false)
            {
                dir.mkdirs();
            }
        }
        else{
            dir = new File(downloadFolder + "/" + pathFromData); // path to save file
        }

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getPath() + File.separator + Translit.toTranslit(file.getOriginalFilename()) );
                String dbfilepath = new String(dir.getPath() + File.separator + Translit.toTranslit(file.getOriginalFilename()));
                if(serverFile.exists()){
                    int i = 1;
                    while(serverFile.exists()) {
                        serverFile = new File(dir.getPath() + File.separator +"("+i+") -" + Translit.toTranslit(file.getOriginalFilename()) );
                        dbfilepath = dir.getPath() + File.separator +"("+i+") -" + Translit.toTranslit(file.getOriginalFilename());
                        i++;
                    }
                }

                //////////
                int userId = toIntExact(userService.findByUsername(request.getUserPrincipal().getName().toString()).getId()) ;
                com.filesharing.account.model.File filedb = new com.filesharing.account.model.File(userId,dbfilepath,serverFile.getName());
                filedb.setFile_url(pathFromData + "/" +  serverFile.getName() );
                fileService.addFile(filedb);
                /////////


                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                return "You successfully uploaded file=" + file.getName();
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getName()
                    + " because the file was empty.";
        }
    }

}




