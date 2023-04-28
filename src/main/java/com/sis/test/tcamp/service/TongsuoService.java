package com.sis.test.tcamp.service;

import com.sis.test.tcamp.common.CommonImportDto;
import com.sis.test.tcamp.common.Result;

public interface TongsuoService {

    Result sm4CbcEncrypt(CommonImportDto body);

    Result sm4CbcDecode(CommonImportDto body);

    Result sm3Dgst(CommonImportDto body);

    Result sm2Verify(CommonImportDto body);

    Result sm2Sign(CommonImportDto body);
}
