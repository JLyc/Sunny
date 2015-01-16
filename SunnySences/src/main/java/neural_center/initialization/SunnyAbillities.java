package neural_center.initialization;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

/**
 * Created by socha on 17.10.2014.
 */
public class SunnyAbillities {
    private static final Map<String, AbilitiesRegistrar> abillities = Maps.newHashMap();

    public static boolean registerAbillities(AbilitiesRegistrar abillitie)
    {
        AbilitiesRegistrar result  = abillities.put(abillitie.getAbilitieName(),abillitie);

        if(result == null || result.equals(abillitie))
        {
            return true;
        }else
        {
            abillities.put(abillitie.getAbilitieName(),abillitie);
            System.err.println("you try overwrite existing abilitie not permited old one keeped");
            return false;
        }
    }

    public static void execute(String abillitieName) {
        abillities.get(abillitieName).execute();
    }

    public static Set<String> get()
    {
        return abillities.keySet();
    }


}
