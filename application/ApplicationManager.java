package application;

import interfaces.I_Manager;
import connection.ConnectionManager;
import phone.Phone;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ApplicationManager implements I_Manager<Application> {
    Phone phone = new Phone();
    Map<String, Application> apps = phone.getApps();
    ConnectionManager connectionManager = new ConnectionManager();

    public ApplicationManager(/*Phone phone*/) {
        //this.phone = phone;
        apps = new HashMap<>();
        defaultApplications();
    }

    public void list() {
        apps.values().forEach(System.out::println);
    }

    private String createAppKey(Application application) {
        return application.getName() + application.getVersion();
    }

    private boolean hasEnoughSpace(Application application) {
        return phone.getEmptySpace() > application.getSize();
    }

    @Override
    public Application add(Application application) {
        if (hasEnoughSpace(application))
            return apps.put(createAppKey(application), application);
        return null; // Exception fırlatacak!!!
    }

    @Override
    public Application remove(Application application) {
        return apps.remove(createAppKey(application));
    }

    @Override
    public void update(String applicationName, Consumer<Application> consumer) {
        consumer.accept(apps.get(applicationName));
    }

    // addPerson() if user is owner switch the users and their files, generate file name using Supplier functions
    public void updateName(Application application, String name) {
        update(application.getName(), application_ -> application_.setName(name));
    }

    private void defaultApplications() {
        Application connections = new Application("Kişilerim", 20, "V1.5.4", ApplicationCategory.OTHER);
        Application adventureGame = new Application("Macera Oyunu", 28.6, "V0.1.2", ApplicationCategory.GAME);
        Application instagram = new Application("Instagram", 15.1, "V.13.4", ApplicationCategory.SOCIAL_MEDIA);
        Application numberPredicateGame = new Application("Sayı Tahmin Oyunu", 15, "V.15.0", ApplicationCategory.GAME);
        Application twitter = new Application("Twitter", 14, "V3.1.2", ApplicationCategory.SOCIAL_MEDIA);
        Application mineSweeper = new Application("Mayın Tarlası", 12, "V1.2", ApplicationCategory.GAME);
        add(connections);
        add(adventureGame);
        add(instagram);
        add(numberPredicateGame);
        add(twitter);
        add(twitter);
        /*apps.put(connections.getName(), connections);
        apps.put(adventureGame.getName(), adventureGame);
        apps.put(instagram.getName(), instagram);
        apps.put(numberPredicateGame.getName(), numberPredicateGame);
        apps.put(twitter.getName(), twitter);
        apps.put(mineSweeper.getName(), mineSweeper);*/
    }
}