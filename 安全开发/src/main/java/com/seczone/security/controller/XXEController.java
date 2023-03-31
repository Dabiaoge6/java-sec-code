package com.seczone.security.controller;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Controller
@RequestMapping("/xxe")
public class XXEController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/upload")
    public String index() {
        return "xxe_upload"; // return xxe_upload.html page
    }

    /**漏洞范围：poi-oomxml-3.10-FINAL.jar及以下版本
     * 解析接受的excel表
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("readexcel")
    public String readExcel(MultipartFile file) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream()); // xxe vuln   读取工作簿

        XSSFSheet sheet = wb.getSheetAt(0);//读取excel工作表
        XSSFRow row;        // 行
        XSSFCell cell;      //单元

        Iterator rows = sheet.rowIterator();
        String result = "";

        while (rows.hasNext())
        {
            row=(XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            while (cells.hasNext())
            {
                cell=(XSSFCell) cells.next();

                if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                    result += cell.getStringCellValue()+ " ";
                } else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    result += cell.getNumericCellValue()+ " ";
                } else {
                    logger.info("errors");

                }
            }
        }
        if ( isBlank(result) ){
            result = "XXE测试失败";
        }

        return result;
    }



}
