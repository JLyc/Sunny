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
        splitedCommand = command.split("\\s");

        for (ArrayList<ArrayList<String>>line : makeParts()) {
            for(int index=0;index<line.size();index++) {
                int match = isSame(line.get(index), index);
                if (match==-1) {
                    break;
                }
                switch (index) {
                    case 0: break;
                    case 1:
                        resolveModifier(splitedCommand[index]);
                        break;
                    case 2:
                        name = line.get(index).get(match);
                        break;
                    default:
                        commandDescription += splitedCommand[index];
                }
            }
        }
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
        ArrayList<ArrayList<String>> modifiers = SunnyInitialization.getBknowledge().get("recognizedWords");
        for(ArrayList<String> list : modifiers)
        {
            if(list.contains(modifierCandidate)) {
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

    private ArrayList<ArrayList<ArrayList<String>>> makeParts() {
        ArrayList<ArrayList<ArrayList<String>>> cube3D = new ArrayList<>();
        int line =0;
        for (ArrayList<String> knowCommands : SunnyInitialization.getBknowledge().get("grammarForListening")) {
            cube3D.add(new ArrayList<ArrayList<String>>());
            int part = 0;
            for (String knowCommandsPart : knowCommands) {
                String[] partsBreak = knowCommandsPart.split("\\s\\|\\s");
                cube3D.get(line).add(new ArrayList<String>());
                for (String items : partsBreak) {

                    cube3D.get(line).get(part).add(items);
                }
                part++;
            }
            line++;
        }
        return cube3D;
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
