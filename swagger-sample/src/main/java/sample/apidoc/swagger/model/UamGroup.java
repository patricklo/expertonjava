package sample.apidoc.swagger.model;

/**
 * Created by lopatrip on 6/23/2017.
 */

import io.swagger.annotations.*;

/**
 * 群组
 */
@ApiModel
public class UamGroup {
    /**
     * 编号
     */
    @ApiModelProperty(value = "群组的Id", required = true)
    private String groupId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "群组的名称", required = true)
    private String name;
    /**
     * 群组图标
     */
    @ApiModelProperty(value = "群组的头像", required = false)
    private String icon;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}