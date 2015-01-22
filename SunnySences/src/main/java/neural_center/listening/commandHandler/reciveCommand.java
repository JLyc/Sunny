package neural_center.listening.commandHandler;

import neural_center.initialization.BasicKnowledge;
import neural_center.initialization.Sunny;
import org.w3c.dom.NodeList;
import uniqe_skills.smart_xpath.SmartXPath;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by JLyc on 22. 1. 2015.
 */
public class reciveCommand {
    private static NodeList wordList1;
    private static NodeList wordList2;

    public static boolean updateKnowingWords() throws Exception{
        wordList1 = new SmartXPath().from(BasicKnowledge.getInstance().get("grammarForListening")).forExpresion("/root/row").asNodeList();
        wordList2 = new SmartXPath().from(BasicKnowledge.getInstance().get("recognizedWords")).forExpresion("/root/modifies").asNodeList();
        return true;
    }

    public reciveCommand(String command) {
        if(command.contains("sunny")) {
            Future<String> isFuture1 = Sunny.getExecutor().submit(new MiniThread(command));
            Future<String> isFuture2 = Sunny.getExecutor().submit(new MiniThread(command));
            try {
                System.out.println(isFuture1.get());
                System.out.println(isFuture2.get());
            } catch (InterruptedException | ExecutionException e) {
                try {
                    TimeUnit.SECONDS.sleep(10);

                System.out.println(isFuture1.get());
                System.out.println(isFuture2.get());
                } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                    e1.printStackTrace();
                }
            }
        }
        else{
            System.out.println("no valid command");
        }
    }

    class MiniThread implements Callable<String>{
        private String command;
        public MiniThread(String command) {
            this.command = command;
        }

        @Override
        public String call() throws Exception {
//            for (int index = 0; index < wordList1.getLength(); index++) {
//                gramFileContent.append(" "+nodeList.item(index).getTextContent());
//                if(index!=nodeList.getLength()-1) {
//                    gramFileContent.append(" | ");
//                }
//            }

            return null;
        }
    }
}