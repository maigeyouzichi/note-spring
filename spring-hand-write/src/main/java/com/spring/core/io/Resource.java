package com.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 职责: 代表着资源,声明获取流的能力
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}