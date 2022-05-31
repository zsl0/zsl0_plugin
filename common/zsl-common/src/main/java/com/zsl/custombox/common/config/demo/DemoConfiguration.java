package com.zsl.custombox.common.config.demo;

import com.zsl.custombox.common.entity.EnableDemoAnnotation;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author zsl
 * @Date 2022/5/20 23:15
 * @Email 249269610@qq.com
 */
public class DemoConfiguration implements ImportAware {
    AnnotationAttributes annotationAttributes;

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        annotationAttributes = AnnotationAttributes.fromMap(importMetadata.getAnnotationAttributes(EnableDemoAnnotation.class.getName(), false));
    }

    public AnnotationAttributes getAnnotationAttributes() {
        return annotationAttributes;
    }
}
