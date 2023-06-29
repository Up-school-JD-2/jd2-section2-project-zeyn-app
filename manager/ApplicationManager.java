package manager;

import application.Application;
import interfaces.I_Manager;
import phone.Phone;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ApplicationManager implements I_Manager<Application> {
    Phone phone = new Phone();
    Map<String, Application> apps;
    public ApplicationManager(/*Phone phone*/) {
        //this.phone = phone;
        apps = new HashMap<>();
    }
    public void list(){
        apps.values().forEach(System.out::println);
    }
    private String createAppKey(Application application){
        return application.getName() + application.getVersion();
    }
    private boolean hasEnoughSpace(Application application) {
        return phone.getEmptySpace() > application.getSize();
    }
    @Override
    public Application add(Application application) {
        return apps.put(createAppKey(application), application);
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
    public void updateName(Application application, String name){
        update(application.getName(), application_ -> application_.setName(name));
    }

}

