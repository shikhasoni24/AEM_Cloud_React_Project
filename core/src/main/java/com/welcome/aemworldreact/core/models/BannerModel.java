package com.welcome.aemworldreact.core.models;


import com.adobe.cq.wcm.core.components.models.Image;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface BannerModel extends Image {

    public String getBannerText();

}

