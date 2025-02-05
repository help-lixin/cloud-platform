package help.lixin.starlink.plugin.jenkins.dto.job;

import help.lixin.starlink.plugin.jenkins.domain.ProjectType;
import help.lixin.starlink.plugin.jenkins.domain.ScmType;
import help.lixin.starlink.plugin.jenkins.domain.ToolsType;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;
import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupCommonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/13 9:48 上午
 * @Description
 */
public class JenkinsJobFormDTO {

    // jenkins支持(  FreeStyle Project / Maven Project / Pipeline Project )
    private ProjectType projectType = ProjectType.FREE_STYLE_PROJECT;

    /**
     * 仓库类型(svn/git),主要是方便前端进行切换
     */
    private ScmType scmType;

    /**
     * 工具类型(maven/ant/gradle/python/go...)
     */
    private ToolsType toolsType;

    /**
     * 对应(jenkins_system_config)表里的id
     */
    private String jdkId;

    /**
     * 仓库详细信息配置
     */
    private JenkinsPipelineScmDTO scm;

    private List<JenkinsParamDTO> params = new ArrayList<>(0);

    // 对应的是构建依赖表里的id
    private List<String> buildDependencys = new ArrayList<>();

    private List<JenkinsPipelineSetupCommonDTO> setups;

    private Long id;

    private String instanceCode;

    /**
     * 任务名
     */
    private String jobName;

    /**
     * 备注
     */
    private String remark;


    /**
     * 状态值
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * xml内容
     */
    private String xmlContent;

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public ScmType getScmType() {
        return scmType;
    }

    public void setScmType(ScmType scmType) {
        this.scmType = scmType;
    }

    public ToolsType getToolsType() {
        return toolsType;
    }

    public void setToolsType(ToolsType toolsType) {
        this.toolsType = toolsType;
    }

    public String getJdkId() {
        return jdkId;
    }

    public void setJdkId(String jdkId) {
        this.jdkId = jdkId;
    }

    public JenkinsPipelineScmDTO getScm() {
        return scm;
    }

    public void setScm(JenkinsPipelineScmDTO scm) {
        this.scm = scm;
    }

    public List<JenkinsParamDTO> getParams() {
        return params;
    }

    public void setParams(List<JenkinsParamDTO> params) {
        this.params = params;
    }

    public List<String> getBuildDependencys() {
        return buildDependencys;
    }

    public void setBuildDependencys(List<String> buildDependencys) {
        this.buildDependencys = buildDependencys;
    }

    public List<JenkinsPipelineSetupCommonDTO> getSetups() {
        return setups;
    }

    public void setSetups(List<JenkinsPipelineSetupCommonDTO> setups) {
        this.setups = setups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getXmlContent() {
        return xmlContent;
    }

    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent;
    }

}
