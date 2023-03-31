package com.seczone.security.controller;

import com.seczone.security.dao.VulType;
import com.seczone.security.mapper.VulTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    VulTypeMapper vulTypeMapper;


    @ResponseBody
    @GetMapping("/type")
    public List<VulType> GetVulType(){
        final List<VulType> vulTypes = vulTypeMapper.SearchVulType();
        return vulTypes;
    }


}
