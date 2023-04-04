package com.welcome.aemworldreact.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.welcome.aemworldreact.core.models.BoardModel;
import com.welcome.aemworldreact.core.models.ResumeModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = { BoardModel.class, ComponentExporter.class },
        resourceType = BoardModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class BoardModelImpl implements BoardModel {

    static final String RESOURCE_TYPE = "aemworldreact/components/board";


    @ValueMapValue
    private String heading;

    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public String getExportedType() {
        return  BoardModelImpl.RESOURCE_TYPE;
    }
}
