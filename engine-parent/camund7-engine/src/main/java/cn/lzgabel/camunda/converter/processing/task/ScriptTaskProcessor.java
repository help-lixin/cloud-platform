package cn.lzgabel.camunda.converter.processing.task;

import cn.lzgabel.camunda.converter.bean.BaseDefinition;
import cn.lzgabel.camunda.converter.bean.task.ScriptTaskDefinition;
import cn.lzgabel.camunda.converter.processing.BpmnElementProcessor;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.camunda.bpm.model.bpmn.builder.AbstractFlowNodeBuilder;
import org.camunda.bpm.model.bpmn.builder.ScriptTaskBuilder;
import org.camunda.bpm.model.bpmn.instance.ScriptTask;

/**
 * 〈功能简述〉<br>
 * 〈ScriptTask节点类型详情设置〉
 *
 * @author lizhi
 * @since 1.0.0
 */
public class ScriptTaskProcessor
        implements BpmnElementProcessor<ScriptTaskDefinition, AbstractFlowNodeBuilder> {

    @Override
    public String onComplete(AbstractFlowNodeBuilder flowNodeBuilder, ScriptTaskDefinition flowNode)
            throws InvocationTargetException, IllegalAccessException {
        String nodeType = flowNode.getNodeType();
        String nodeName = flowNode.getNodeName();
        String scriptFormat = flowNode.getScriptFormat();
        String scriptText = flowNode.getScriptText();
        String resultVariable = flowNode.getResultVariable();

        // 创建 ReceiveTask
        ScriptTask scriptTask = (ScriptTask) createInstance(flowNodeBuilder, nodeType);
        ScriptTaskBuilder scriptTaskBuilder = scriptTask.builder();

        scriptTaskBuilder
                .name(nodeName)
                .scriptFormat(scriptFormat)
                .camundaResultVariable(resultVariable)
                .scriptText(scriptText);
        String id = scriptTask.getId();

        // create execution listener
        createExecutionListener(scriptTaskBuilder, flowNode);

        // 如果当前任务还有后续任务，则遍历创建后续任务
        BaseDefinition nextNode = flowNode.getNextNode();
        if (Objects.nonNull(nextNode)) {
            return onCreate(moveToNode(flowNodeBuilder, id), nextNode);
        } else {
            return id;
        }
    }
}
