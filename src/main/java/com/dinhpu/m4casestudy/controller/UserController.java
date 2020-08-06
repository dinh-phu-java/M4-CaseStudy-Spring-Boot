package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.dto.user.CrmChangePasswordUser;
import com.dinhpu.m4casestudy.dto.user.CrmUpdateUser;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.S3Services;
import com.dinhpu.m4casestudy.services.user.IUserServices;
import com.dinhpu.m4casestudy.utils.ImageUtils;
import com.dinhpu.m4casestudy.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Value("${jsa.s3.bucket.url}")
    private String s3BucketUrl;

    @Autowired
    S3Services s3Services;

    @Autowired
    private IUserServices userServices;

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${my.resource.path}")
    private String resourcePath;

    @Value("${delete.path}")
    private String envDeletePath;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/userProfile")
    public String showAdminPage(Model theModel, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        User realLoginUser = userServices.findUserByEmail(loginUser.getEmail());

        CrmUpdateUser theCrmUSer = UserUtils.userToCrmUpdateUser(realLoginUser);


        theModel.addAttribute("crmUser", theCrmUSer);

        return "new-profile-page";
    }

    @PostMapping(value = "/updateUser", consumes = {"multipart/form-data"},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateUser(@Valid @ModelAttribute CrmUpdateUser crmUser, BindingResult theBinding, Model theModel, HttpSession session) {

        if (theBinding.hasErrors()) {

            return "new-profile-page";

        }

//        MultipartFile file = crmUser.getLogoFile();
//
//        String fileName = ImageUtils.hashFileName(file.getOriginalFilename());
//
//        String uploadDir = uploadPath + crmUser.getId();
//
//        Path uploadPath = Paths.get(uploadDir);

        String logoUrl = "";

        User loginUser = (User) session.getAttribute("loginUser");

        if (crmUser.getLogoFile().getOriginalFilename().equals("")) {

            System.out.println("file null");
//                logoUrl="/dist/img/user-default.png";
            logoUrl = loginUser.getLogoUrl();

        } else {

            try {

                MultipartFile file = crmUser.getLogoFile();

                String fileName = ImageUtils.hashFileName(file.getOriginalFilename());

                String uploadDir = uploadPath + crmUser.getId();

                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {

                    Files.createDirectories(uploadPath);

                }



                InputStream inputStream = file.getInputStream();

                Path filePath = uploadPath.resolve(fileName);

                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

                String keyImage=loginUser.getLogoUrl();
                keyImage=keyImage.substring(keyImage.lastIndexOf('/')+1);

                Path deletePath = Paths.get(envDeletePath +loginUser.getId()+"/"+ keyImage);
                if (Files.exists(deletePath)){

                    Files.delete(deletePath);

                }

                s3Services.deleteFile(keyImage);

                s3Services.uploadFile(fileName,filePath.toString());
                logoUrl=s3BucketUrl+fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //convert crmUser to User
        User updateUser = UserUtils.crmUserToUser(crmUser, logoUrl);
        System.out.println("update user is: " + updateUser);

        //get current password
        updateUser.setPassword(loginUser.getPassword());
        System.out.println("update user !!!");
        userServices.save(updateUser);

        theModel.addAttribute("crmUser", crmUser);
        theModel.addAttribute("message", "Update Completed!");

        //update sessino loginUSer
        session.setAttribute("loginUser", updateUser);

        return "redirect:/userProfile";
    }

    @GetMapping("/changePassword")
    public String changePasswordForm(HttpSession session, Model theModel) {

        User loginUser = (User) session.getAttribute("loginUser");

        User realLoginUser = userServices.findUserByEmail(loginUser.getEmail());

        CrmChangePasswordUser theCrmUSer = UserUtils.userToCrmChangePasswordUser(realLoginUser);

        theModel.addAttribute("message", null);
        theModel.addAttribute("crmUser", theCrmUSer);

        return "change-password-new";

    }

    @PostMapping("/changePasswordProcess")
    public String changePasswordProcess(Model theModel,@ModelAttribute CrmChangePasswordUser crmUser, @RequestParam String current_password, HttpSession session, BindingResult theBinding) {
        Long userId = crmUser.getId();
        User loginUser = userServices.findById(userId);

        String message = null;
        if (!passwordEncoder.matches(current_password, loginUser.getPassword())) {

            message = "Current password is not correct!";

        } else {

            if ( (!crmUser.getPassword().equals(crmUser.getMatchingPassword())) || crmUser.getPassword().isEmpty() ) {
                message="Password is not matching!";
            }else{
                String newPassword = crmUser.getPassword();
                newPassword = passwordEncoder.encode(newPassword);

                loginUser.setPassword(newPassword);

                userServices.save(loginUser);

                message = "Change password successful";

                session.setAttribute("loginUser", loginUser);
            }

        }

        theModel.addAttribute("message", message);
        CrmChangePasswordUser theCrmUSer = UserUtils.userToCrmChangePasswordUser(loginUser);
        theModel.addAttribute("crmUser", theCrmUSer);
        return "change-password-new";
    }

}
