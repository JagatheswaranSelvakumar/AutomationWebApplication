package com.manulife.webautomation.pageobject;

public class HomePage {

    public static String signIn_BTN = "sign-in-btn~ID";
    public static String signIn_Mobile_BTN = "//button[@class='sign-in navbar-toggle collapsed']~XPATH";
    public static String menu_Mobile_BTN = "//button[@class='hamburger navbar-toggle collapsed']~XPATH";
    public static String groupPlans_LNK = "//*[@id='mega-menu-updated']//a[contains(text(), 'Group plans')]~XPATH";
    public static String groupPlans_Mobile_LNK = "//div[@id='for-you-heading-mobile-5']//a~XPATH";
    public static String groupBenefits_LNK = "//div[@id='mega-menu-updated']/ul/li[5]/div/div/div/div[2]/div/div/div/a/span[2]~XPATH";
    public static String groupBenefits_Mobile_LNK = "//div[@id='for-you-sub-nav-collapse-mobile-5']//a[contains(text(),'Group benefits')]~XPATH";
    public static String submitCliam_BTN = "//a/span[@class='ml-icons healthInsurance']~XPATH";
    public static String signin_BTN = "//a[@title='Sign in']/button[@class='btn btn-lg btn-primary opens-in-new-tab']~XPATH";
    public static String planContractNumber_TXT = "GBLoginModel_PlanContractNumber~ID";
    public static String memberCertificateNumber_TXT = "GBLoginModel_MemberCertificateNumber~ID";
    public static String password = "GBLoginModel_GBPassword~ID";
    public static String GB_SignIn_BTN = "GB_submitButton~ID";
    public static String submitACaim_LBL = "//label[contains(text(),'Submit a claim')]~XPATH";
    

}
