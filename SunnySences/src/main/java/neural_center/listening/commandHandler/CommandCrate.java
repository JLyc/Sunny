package neural_center.listening.commandHandler;

import neural_center.initialization.SunnyInitialization;

import java.util.ArrayList;

public class CommandCrate
{
    //program name
    private String name;
    //modifier run close
    private String modifier;
    private int modifierNumber;
    private String commandDescription;
    private String[] splitedCommand;

    public CommandCrate(String command)
    {
//        String xpath = ;
//        List<String> shiteSpaces = new SmartXPath().from().forExpresion("/root/modifieres/modifier/@whiteSpace").asList();
//        List<String> shiteSpaces = new SmartXPath().from().forExpresion("/root/commands/command[@id='"+ name +"' and @modifier='"+ modifier+"']").asList();
//        String xpath1 = ;
    }

    private int isSame(ArrayList<String> list, int position) {
        if (list.contains(splitedCommand[position])) {
            return position;
        } else {
            for (int index = 0; index < list.size(); index++) {
                if (list.get(index).matches(splitedCommand[position] + ".+") && list.get(index).matches(".+" + splitedCommand[position + 1])) {
                    return index;
                }
            }
        }
        return -1;
    }


    private void resolveModifier(String modifierCandidate) {
        ArrayList<ArrayList<String>> modifiers = new ArrayList<>();
        for (ArrayList<String> list : modifiers) {
            if (list.contains(modifierCandidate)) {
                modifier = list.get(list.indexOf(modifierCandidate));
                modifierNumber = Integer.parseInt(list.get(modifiers.indexOf(list)));
                break;
            }
        }
    }

    public String getName()
    {
        return name;
    }

    public String getModifier()
    {
        return modifier;
    }

    public int getModifierNumber()
    {
        return modifierNumber;
    }

    public String getCommandDescription()
    {
        return commandDescription;
    }

    @Deprecated
    public static void main(String[] args)
    {
        SunnyInitialization.main(null);
        String command = "Sunny Run Fire Fox";
        CommandCrate feedback = new CommandCrate(command);
        System.out.println(feedback.getCommandDescription());
        System.out.println(feedback.getModifier());
        System.out.println(feedback.getModifierNumber());
        System.out.println(feedback.getName());
    }
}
