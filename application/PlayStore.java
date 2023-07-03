package application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayStore {
    Map<String, Application> apps;

    public PlayStore(){
        apps = new HashMap<>();
        Application adventureGame = new Application("Macera Oyunu", 28.6, "V0.1.2", ApplicationCategory.GAME);
        Application instagram = new Application("Instagram", 15.1, "V.13.4", ApplicationCategory.SOCIAL_MEDIA);
        Application numberPredicateGame = new Application("Sayı Tahmin Oyunu", 15, "V.15.0", ApplicationCategory.GAME);
        Application twitter = new Application("Twitter", 14, "V3.1.2", ApplicationCategory.SOCIAL_MEDIA);
        Application mineSweeper = new Application("Mayın Tarlası", 12, "V1.2", ApplicationCategory.GAME);

        apps.put(adventureGame.getName(), adventureGame);
        apps.put(instagram.getName(), instagram);
        apps.put(numberPredicateGame.getName(), numberPredicateGame);
        apps.put(twitter.getName(), twitter);
        apps.put(mineSweeper.getName(), mineSweeper);
    }
    public List<Application> getApps(){
        return apps.values().stream().toList();
    }

    public void list() {
        apps.values().forEach(System.out::println);
    }
}
