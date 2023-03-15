package cn.lzgabel.camunda.converter.bean.task;

import cn.lzgabel.camunda.converter.bean.BaseDefinition;
import cn.lzgabel.camunda.converter.bean.BpmnElementType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈功能简述〉<br>
 * 〈服务任务定义〉
 *
 * @author lizhi
 * @since 1.0.0
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class ServiceTaskDefinition extends BaseDefinition {

    private String topic;

    private String expression;

    private String resultVariable;

    private String delegateExpression;

    private String javaClass;

    private Map<String, String> inputParameters = new HashMap<>();

    public void addInputParameter(String name, String value) {
        if (null == inputParameters) {
            inputParameters = new HashMap<>();
        }
        this.inputParameters.put(name, value);
    }


    @Override
    public String getNodeType() {
        return BpmnElementType.SERVICE_TASK.getElementTypeName().get();
    }
}
