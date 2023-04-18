package com.welcome.aemworldreact.core.service.impl;


import com.welcome.aemworldreact.core.service.CreateResumeService;
import org.osgi.service.component.annotations.Component;

@Component(service = CreateResumeService.class, immediate = true)
public class CreateResumeServiceImpl implements CreateResumeService{

    public String readJsonFile(){
        String jsonData = "{\n" +
                "\t\"name\": \"Suresh Raina\",\n" +
                "\t\"designation\": \"Senior Aem Developer\",\n" +
                "\t\"email\": \"sureshRaina798@gmail.com\",\n" +
                "\t\"phone\": \"8093888668\",\n" +
                "\t\"summary\": {\n" +
                "\t\t\"summaryHeading\": \"Summary\",\n" +
                "\t\t\"summaryPoints\": [\n" +
                "\t\t\t\"Have Built CMS solutions on top of the Adobe CQ/AEM, WCM, and DAM and was involved in designing and implementing custom components in Adobe CQ.\",\n" +
                "\t\t\t\"point2\",\n" +
                "\t\t\t\"Having good practical knowledge on AEM (CQ) 6.3, 6.4 and 6.5 version, WCM, Java, HTL and JavaScript\"\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t\"technicalSkills\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"technicalSkillName\": \"Languages and CMS\",\n" +
                "\t\t\t\"technicalSkillValue\": \"AEM (CQ) 6.3,6.4,6.5,Cloud (Sites,Assets,Forms), Java (J2EE),React\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"technicalSkillName\": \"Technologies\",\n" +
                "\t\t\t\"technicalSkillValue\": \"HTL (Sightly), Bootstrap, HTML 5, CSS, JavaScript, Jquery,Ajax \"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"technicalSkillName\": \"IDE\",\n" +
                "\t\t\t\"technicalSkillValue\": \"CRX-De, Eclipse, Intellij Idea\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"technicalSkillName\": \"Framework\",\n" +
                "\t\t\t\"technicalSkillValue\": \"Apache Sling, OSGi, Hibernate, Spring MVC\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"technicalSkillName\": \"Methodologies\",\n" +
                "\t\t\t\"technicalSkillValue\": \"Agile, Waterfall\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"technicalSkillName\": \"Tools\",\n" +
                "\t\t\t\"technicalSkillValue\": \"GIT,SVN, Jira, SONAR, RADAR, Maven, Adobe Analytics Tool\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"professionalExperience\": {\n" +
                "\t\t\"professionalExperienceHeading\": \"I have 5.1 years of experience as AEM Sites/Assets Developer in web applications development\",\n" +
                "\t\t\"professionalExperiencePoints\": [\n" +
                "\t\t\t\"Currently working in TA Digital, Hyderabad as Senior AEM Developer. (23rd March 2022 to present)\",\n" +
                "\t\t\t\"Previously worked in IBM, Bangalore as a Senior AEM Developer. (15th April-2021 to 22nd march 2022)\",\n" +
                "\t\t\t\"Previously worked at Mindtree, Bangalore as Senior AEM Developer. (22nd July 2017 to 09th Apr 2021)\"\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t\"professionalProject\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"project\": \" Hyundai.com Development\",\n" +
                "\t\t\t\"tenure\": \"1st April till Now\",\n" +
                "\t\t\t\"projectdescription\": \"Hyundai AutoEver, a“mobility software provider” that reliably, efficiently, and innovatively\",\n" +
                "\t\t\t\"developmentEnvironment\": \"AEM 6.5(CQ) DAM, Functionality development, Servlets, Schedulers, UserAdminModule, JCR, Apache Sling, Bitbucket\",\n" +
                "\t\t\t\"contrbutionTo Project\": [\n" +
                "\t\t\t\t\"I worked on useradmin module\",\n" +
                "\t\t\t\t\"I wrote servlet, services to meet business need.\"\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"trainingUndergone\": \"I have successfully completed my JAVA(Advanced) training with Mindtree Kalinga, Bhubaneshwar (July’17 to October’17)\",\n" +
                "\t\"jobResponsibilities\": [\"Working on complex stories\",\"Involved in Bug fixing\"]\n" +
                "}";

        return jsonData;
    }
}
