package com.hyn.common.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author wuzg
 * @version 1.0.0
 * @description 基础入参数据模型
 * @date 2022/2/9
 */
@Data
@ApiModel(value = "基础入参对象")
public class BaseReqDto {
    /**当前分页页码，默认第一页**/
    @ApiModelProperty(value = "当前分页页码，默认第一页")
    private long pageNo = 1;
    /**每页记录数量，**/
    @ApiModelProperty(value = "每页记录数量")
    private long pageSize = 10;
    /**每页最大记录数量，**/
    @ApiModelProperty(value = "每页最大记录数量", hidden = true)
    private long maxPageSize = 100;
    /**是否分页【-1不分页，1分页】**/
    @ApiModelProperty(value = "是否分页【-1不分页，1分页】，默认值1")
    private long isPage = 1;

    public long getPageNo() {
        return pageNo < 1 ? 1 : pageNo > 500 ? 1 : pageNo;
    }

    public long getPageSize() {
        return pageSize > 100 ? maxPageSize : pageSize < 1 ? 10 : pageSize;
    }

    public long getIsPage() {
        return isPage != 1 && isPage != -1 ? 1 : isPage;
    }
}
