package com.welcome.aemworldreact.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.welcome.aemworldreact.core.models.ResumeModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = { ResumeModel.class, ComponentExporter.class },
        resourceType = ResumeModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter( //Exporter annotation that serializes the model as JSON
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class ResumeModelImpl implements ResumeModel {

    static final String RESOURCE_TYPE = "aemworldreact/components/resume";


    @Override
    public String getData() {
        return "data";
    }

    @Override
    public String getExportedType() {
        return ResumeModelImpl.RESOURCE_TYPE;
    }
}
