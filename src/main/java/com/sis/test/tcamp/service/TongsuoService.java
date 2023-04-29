package com.sis.test.tcamp.service;

import com.sis.test.tcamp.common.CommonImportDto;
import com.sis.test.tcamp.common.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface TongsuoService {

    Result sm4CbcEncrypt(CommonImportDto body);

    Result sm4CbcDecode(CommonImportDto body);

    Result sm3Dgst(CommonImportDto body);

    Result sm2Verify(CommonImportDto body) throws IOException;

    void sm2Sign(CommonImportDto body, HttpServletResponse response) throws IOException;
}
