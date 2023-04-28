package com.sis.test.tcamp.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chatty
 * @create 2023-04-28 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonImportDto {


    public  String plain;

    public  String key;

    public  String sigFile;

    public  String filePath;
}
