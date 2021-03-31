package com.bewind.evil.pojo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 通知公告对象 sys1_notice
 * 
 * @author 港
 * @date 2021-03-26
 */
public class SysNotice implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 标题  */
    private String title;
    /** 类型(1通知 2公告) */
    private String type;
    /** 公告内容 */
    private String content;
    /** 状态(0 正常 1关闭) */
    private String status;
    /** 创建者 */
    private String createduser;
    /** 更新者 */
    private String modifieduser;
    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedtime;
    private Date createtime;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getCreatetime() {
        return createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCreateduser(String createduser) 
    {
        this.createduser = createduser;
    }

    public String getCreateduser() 
    {
        return createduser;
    }
    public void setModifieduser(String modifieduser) 
    {
        this.modifieduser = modifieduser;
    }

    public String getModifieduser() 
    {
        return modifieduser;
    }
    public void setModifiedtime(Date modifiedtime) 
    {
        this.modifiedtime = modifiedtime;
    }

    public Date getModifiedtime() 
    {
        return modifiedtime;
    }

    @Override
    public String toString() {
        return "SysNotice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", createduser='" + createduser + '\'' +
                ", modifieduser='" + modifieduser + '\'' +
                ", modifiedtime=" + modifiedtime +
                '}';
    }
}
