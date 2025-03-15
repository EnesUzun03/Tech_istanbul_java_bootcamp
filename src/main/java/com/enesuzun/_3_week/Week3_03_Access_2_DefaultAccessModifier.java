package com.enesuzun._3_week;

public class Week3_03_Access_2_DefaultAccessModifier {
    public static void main(String[] args) {
        Week3_02_Access_1_PublicAccessModifier accessModifier=new Week3_02_Access_1_PublicAccessModifier();
        System.out.println(accessModifier.publicData);
        //System.out.println(accessModifier.privateData);
        System.out.println(accessModifier.protectedData);
        System.out.println(accessModifier.defaultData);
    }
}
