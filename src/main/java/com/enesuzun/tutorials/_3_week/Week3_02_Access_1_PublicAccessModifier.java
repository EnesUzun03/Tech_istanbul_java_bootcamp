package com.enesuzun.tutorials._3_week;

public class Week3_02_Access_1_PublicAccessModifier {
    public String publicData="public data";
    private String privateData="private data";
    protected String protectedData="protected data";
    String defaultData="default data";

    public static void main(String[] args) {
        Week3_02_Access_1_PublicAccessModifier accessModifier=new Week3_02_Access_1_PublicAccessModifier();
        System.out.println(accessModifier.publicData);
        System.out.println(accessModifier.privateData);
        System.out.println(accessModifier.protectedData);
        System.out.println(accessModifier.defaultData);
    }
}
