package com.xld.common.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传返回结果
 * @Author xld
 * @Date 2019-06-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResultDTO {

    /** 文件上传的相对路径 */
    private String urlRelative;
    /** 文件hash */
    private String hash;
    /** 文件上传的绝对路径 */
    private String urlAbsolutely;
}
