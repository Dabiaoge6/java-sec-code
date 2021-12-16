package org.vulhunter.vulnerfix.pathtraversal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathTraversalFixCommon {

    /**
     * 输入校验
     * @param param 校验对象
     * @return true表示匹配，false表示不匹配
     */
    public static boolean mathch(String param){
        String regex = "[\\\\/:*?\"<>|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(param);
        return matcher.matches();
    }

    /**
     * 限制上传文件类型
     * @param fileName
     * @return true表示允许，false表示不允许
     */
    public static  boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "jpg,gif,png,ico,bmp,jpeg";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * 截取文件名后缀做匹配
     * @param fileName
     * @return true表示合法文件，false表示不合法文件
     */
    public static  boolean isValidFile(String fileName) {
        //设置允许上传文件类型
        String regex = ".*\\\\.(?i)jsp";
        // 获取文件后缀
        CharSequence suffix = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length());
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(suffix);
        boolean isValid = matcher.matches();
        if(isValid){
            return false;
        }
        return true;
    }

    /**
     * 整个文件名进行正则匹配
     * @param fileName
     * @return true 表示合法文件，false表示非法文件
     */
    public static boolean isValidFileName(String fileName){
        String regex = "^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\\\.[x|X][m|M][l|L]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);
        return matcher.matches();
    }

}
