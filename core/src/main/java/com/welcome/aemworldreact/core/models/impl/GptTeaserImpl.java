package com.welcome.aemworldreact.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.welcome.aemworldreact.core.models.GptTeaserInterface;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;


@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = { GptTeaserInterface.class, ComponentExporter.class },
        resourceType = GptTeaserImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter( //Exporter annotation that serializes the model as JSON
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class GptTeaserImpl implements GptTeaserInterface {

    static final String RESOURCE_TYPE = "aemworldreact/components/gptTeaser";
    @Override
    public String getData() {
        return "GptTeaserComponent";
    }

    @Override
    public String getExportedType() {
        return GptTeaserImpl.RESOURCE_TYPE;
    }
}
