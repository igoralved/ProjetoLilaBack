package com.db.jogo.controller;

import com.db.jogo.model.Admin;
import com.db.jogo.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (admin.getSenha() == null)){
            return new ResponseEntity<Admin>(admin, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Admin>(adminService.saveAdmin(admin), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Iterable<Admin>> findAdmin() {
        return new ResponseEntity<Iterable<Admin>>(adminService.findAll(), HttpStatus.OK);
    }
}
