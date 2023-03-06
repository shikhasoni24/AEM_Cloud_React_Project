package com.welcome.aemworldreact.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.welcome.aemworldreact.core.models.FirstReactModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = { FirstReactModel.class, ComponentExporter.class },
        resourceType = FirstReactModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter( //Exporter annotation that serializes the model as JSON
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class FirstReactModelImpl implements FirstReactModel {

    // points to AEM component definition in ui.apps
    static final String RESOURCE_TYPE = "aemworldreact/components/first-react";

    @ValueMapValue
    private String firstName;

    //maps variable to jcr property named "firstName" persisted by Dialog

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getExportedType() {
        return FirstReactModelImpl.RESOURCE_TYPE;
    }
}
