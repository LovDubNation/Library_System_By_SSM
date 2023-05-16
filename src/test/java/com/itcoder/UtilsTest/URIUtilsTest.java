package com.itcoder.UtilsTest;

import com.itcoder.utils.URIUtils;
import org.junit.Test;

public class URIUtilsTest {
    @Test
    public void testGetLastUriName(){
        System.out.println(URIUtils.getLastURI("/test/index.jsp"));
    }

    @Test
    public void testGetUriName(){
        System.out.println(URIUtils.getURIName(URIUtils.getLastURI("/test/index.jsp")));
    }
}
