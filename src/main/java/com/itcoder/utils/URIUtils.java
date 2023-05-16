package com.itcoder.utils;

public class URIUtils {
    /**
     * 获取URI的最后一个单元
     * @param uri
     * @return
     */
    public static String getLastURI(String uri){
        String[] strings = uri.split("/");
        return strings[strings.length-1];
    }

    /**
     * 获取最后uri的uri名，不带后缀
     * @param uri
     * @return
     */
    public static String getURIName(String uri){
        String[] split = uri.split("\\.");
        return split[0];
    }
}
