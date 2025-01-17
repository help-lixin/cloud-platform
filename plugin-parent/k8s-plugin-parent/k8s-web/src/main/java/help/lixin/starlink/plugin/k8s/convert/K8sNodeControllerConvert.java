package help.lixin.starlink.plugin.k8s.convert;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.request.cronjob.CreateCronJobVO;
import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.request.base.BasePageListVO;

@Mapper
public interface K8sNodeControllerConvert {

    BasePageListDTO convert(BasePageListVO basePageListVO);

    K8sAppDTO convert(CreateCronJobVO createCronJobVO);
}
