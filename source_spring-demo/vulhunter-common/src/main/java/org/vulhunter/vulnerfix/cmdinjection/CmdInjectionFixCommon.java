package org.vulhunter.vulnerfix.cmdinjection;

import org.apache.log4j.Logger;

public class CmdInjectionFixCommon {
    private static String[] blackList = {"calc","cmd.exe","/c","dir","mspaint"};
    private Logger logger = Logger.getLogger(CmdInjectionFixCommon.class);

    /**
     * 黑名单
     * @param param 校验参数
     * @return true表示合法参数，false表示污点参数
     */
    public static boolean isValid(String param){
        boolean isValid = false;
        for(int i=0;i<blackList.length;i++){
            if(!param.equals(blackList[i])){
                isValid = true;
            }
        }
        return isValid;
    }

}
