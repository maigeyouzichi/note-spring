package com.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 职责: 声明获取流的能力
 */
@SuppressWarnings("all")
public interface Resource {

    InputStream getInputStream() throws IOException;

}