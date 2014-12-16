package actions.mail;

import javax.mail.*;
import java.util.Properties;

public class CheckEmail extends Thread{

    private String arg1;
    private String arg2;
    private String arg3;
    private static String arg4 = "INBOX";
    private static String arg5 = "mail.store.protocol";
    private static String arg6 = "imaps";


    public CheckEmail(String arg1, String arg2, String arg3)
    {
        this(arg1, arg2, arg3, arg4);
    }

    public CheckEmail(String arg1, String arg2, String arg3, String arg4)
    {
        this(arg1, arg2, arg3, arg4, arg5);
    }

    public CheckEmail(String arg1, String arg2, String arg3, String arg4, String arg5) {
       this(arg1, arg2, arg3, arg4, arg5, arg6);
    }

    public CheckEmail(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.arg4 = arg4;
        this.arg5 = arg5;
        this.arg6 = arg6;
    }

    public void run()
    {
        try {
            Folder folder = connectToAccount();
            folder.open(Folder.READ_WRITE);
            int msgCount = checkNewEmails(folder);

            System.out.println(createAnswer(msgCount));

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Folder connectToAccount() throws MessagingException {
        Properties props = System.getProperties();
        props.setProperty(arg5, arg6);
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore(arg6);
        store.connect(arg1, arg2, arg3);

        Folder folder = store.getFolder(arg4);
        if (folder == null || !folder.exists()) {
            System.out.println("Invalid folder");
            throw new MessagingException();
        }
        return folder;
    }

    private int checkNewEmails(Folder folder) throws MessagingException {
        int totalMsg = folder.getMessageCount();
        int msgCount = 0;
        int lastMsg = totalMsg;

        for(; lastMsg>totalMsg-21; lastMsg--) {
            try {
                if (!folder.getMessage(lastMsg).isSet(Flags.Flag.SEEN)) {
                    msgCount++;
                }
            }catch (IndexOutOfBoundsException e)
            {
                return msgCount;
            }
        }
        return msgCount;
    }

    private String createAnswer(int count)
    {
        switch(count) {
            case 0:
                return arg2 +" has no messege.";
            case 1:
                return arg2 +" has "+count+" messege.";
            default:
                return arg2 +" has "+count+" messeges.";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        CheckEmail checkEmailA = new CheckEmail("imap.gmail.com", "andrej.socha", "m62tkx7j");
        checkEmailA.start();
//        CheckEmail checkEmailJ = new CheckEmail("imap.gmail.com", "JLyc.Development", "044150900");
//        checkEmailJ.start();

//        while (checkEmailJ.isAlive())
//        {}
        long finishTime = System.currentTimeMillis();
        System.out.println("That took: " + (finishTime - startTime) + " ms");
        while (checkEmailA.isAlive())
        {}
        finishTime = System.currentTimeMillis();
        System.out.println("That took: " + (finishTime - startTime) + " ms");
    }

}
