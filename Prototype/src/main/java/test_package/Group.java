/*
 * Copyright (c) 2015. All rights reserved for autor and owner of email domain JLyc.Development@gmial.com.
 */

package test_package;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created 22. 4. 2015.
 *
 * @author JLyc
 */
public class Group {

    public static void main(String[] args) {
        String mydata = "\n" +
                "\n" +
                "   0 2015-04-22 14:54:36\n" +
                "M  CTAG COMPLD\n" +
                "   EN=0   ENDESC=Succeeded.\n" +
                ";\n" +
                "\n" +
                "   0 2015-04-22 14:54:36\n" +
                "M  1 COMPLD\n" +
                "   EN=0   ENDESC=Succeeded.\n" +
                "   blktag=1\n" +
                "   blkcount=1\n" +
                "   blktotal=1\n" +
                "\n" +
                "Information of EMS system\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "ID     NAME   TYPE   MANUFATCTURE IP     INTERFACE    MANUADDRESS  OPERATESTATE SYSWARNSTATE MAXNECOUNT       NECOUNT      CREATOR      CREATEDATE   HARDWAREINFO SOFTWAREINFO CREATORADDRESS      CREATORTEL       REMARK VERSION\n" +
                "1      HUAWEIEMS    EMS    HUAWEI 192.168.1.10:9819   U2000V200R014C50    shenzhen     normal none   100000       5.05   huawei --     SUN    SOLARIS      shenzhen     400-830-2118 remark U2000V200R014C50SPC200\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "\n" +
                "\n" +
                ";\n"+
                "\n" +
                "\n" +
                "   0 2015-04-22 14:54:36\n" +
                "M  CTAG COMPLD\n" +
                "   EN=0   ENDESC=Succeeded.\n" +
                ";\n" +
                "\n" +
                "   0 2015-04-22 14:54:36\n" +
                "M  1 COMPLD\n" +
                "   EN=0   ENDESC=Succeeded.\n" +
                "   blktag=1\n" +
                "   blkcount=1\n" +
                "   blktotal=1\n" +
                "\n" +
                "Information of EMS system\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "ID     NAME   TYPE   MANUFATCTURE IP     INTERFACE    MANUADDRESS  OPERATESTATE SYSWARNSTATE MAXNECOUNT       NECOUNT      CREATOR      CREATEDATE   HARDWAREINFO SOFTWAREINFO CREATORADDRESS      CREATORTEL       REMARK VERSION\n" +
                "1      HUAWEIEMS    EMS    HUAWEI myfuckingip   U2000V200R014C50    shenzhen     normal none   100000       5.05   huawei --     SUN    SOLARIS      shenzhen     400-830-2118 remark U2000V200R014C50SPC200\n" +
                "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "\n" +
                "\n" +
                ";\n"

                ;
        mydata = mydata.subSequence(mydata.lastIndexOf("Succeeded"), mydata.length()).toString();
        Pattern pattern = Pattern.compile("-{5,}([\\s\\S]*?)\\n-{5,}");
        Matcher matcher = pattern.matcher(mydata);
        String output = "";
        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            output = matcher.group(1);
        }
        output = output.trim().replaceAll("\\s+", " ");
        String[] fild = output.split("\\s");
        int half = fild.length/2;
        for(int index=0;index<half;index++){
            System.out.println(fild[index]+" : "+fild[index+half]);
        }

    }
}
