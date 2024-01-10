package com.dpb.nsepback.controller.BUGcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/enc1")
public class fileEnc1Controller {

    @RequestMapping("/submit")
    public void check(@RequestParam(value = "flag",required = false)String flag) throws IOException
    {
        String root =System.getProperty("user.dir");
        String fileName="cipherText.txt";
        String path="/test";
        FileReader fr=new FileReader(path);
        StringBuffer buffer=new StringBuffer();
        BufferedReader br=new BufferedReader(fr);
        String s=null;
        while ((s=br.readLine())!=null)
        {
            buffer.append(s.trim());
        }
        String trueFlag= buffer.toString();

        try{
            if(flag=="flag{Congratulations! You've decrypt this!}")
            {
                System.out.println("success!");
            }
        }catch (Exception e)
        {
            System.out.println("failed");
        }
    }
}
