package org.joychou.controller.othervulns;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Desc:   poi-ooxml xxe vuln code  影响范围 poi-ooxml-3.10-FINAL.jar及以下版本
 * Usage:  [Content_Type].xml  http://localhost:8080/ooxml/upload
 * Ref:    https://www.itread01.com/hkpcyyp.html
 * Fix:    Update poi-ooxml to 3.15 or above.
 * Vuln:   3.10 or below exist xxe vuln. 3.14 or below exist dos vuln. So 3.15 or above is safe version.
 *
 * @author JoyChou @2019-09-05
 */
@Controller
@RequestMapping("ooxml")
public class ooxmlXXE {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/upload")
    public String index() {
        return "xxe_upload"; // return xxe_upload.html page
    }


    @PostMapping("/readxlsx")
    @ResponseBody
    public String ooxml_xxe(MultipartFile file)throws IOException {
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
            result = "xxe test";
        }

        return result;
    }
}
