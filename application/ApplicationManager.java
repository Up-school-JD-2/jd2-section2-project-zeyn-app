package application;

import backupAndRestore.BackUpApplication;
import exceptions.LoadingExistException;
import exceptions.NoEnoughEmptySpaceException;
import exceptions.NotFoundApplicationException;
import interfaces.I_GroupAndFilter;
import interfaces.I_Manager;
import interfaces.I_Sort;
import phone.Phone;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ApplicationManager implements I_Manager<Application>, I_Sort<Application>, I_GroupAndFilter<Application> {
    PlayStore playStore;
    Phone phone;
    Map<String, Application> apps = new HashMap<>();
    BackUpApplication backUpApplication = new BackUpApplication();

    public Map<String, Application> getApps() {
        return apps;
    }

    public void setApps(Map<String, Application> apps) {
        this.apps = apps;
    }

    public ApplicationManager(Phone phone) {
        this.phone = phone;
        playStore = new PlayStore();
        defaultApplications();
    }

    public List<Application> getPlayStore() {
        return playStore.getApps();
    }

    public void list() {
        System.out.println("\n\t-----------------------------------------------");
        System.out.println("\tTelefonumda Yüklü Olan Uygulamalar");
        apps.values().forEach(application -> System.out.println("\t" + application));
    }

    private String createAppKey(Application application) {
        return application.getName() + application.getVersion();
    }

    private boolean hasEnoughSpace(Application application) {
        return phone.getEmptySpace() > application.getSize();
    }

    @Override
    public Application add(Application application) throws NoEnoughEmptySpaceException, LoadingExistException {
        if (hasEnoughSpace(application)) {
            if (!apps.containsKey(createAppKey(application))) {
                phone.setOccupancySpace(application.getSize());
                backUpApplication.backUp(createAppKey(application), application);
                return apps.put(createAppKey(application), application);
            }
            throw new LoadingExistException("Telefonuzda bu uygulama zaten bulunmaktadır.");
        }

        throw new NoEnoughEmptySpaceException("Telefonda yeterli boş alan bulunmamaktadır.");
    }

    @Override
    public Application remove(Application application) throws NotFoundApplicationException {
        if (!apps.containsKey(createAppKey(application)))
            throw new NotFoundApplicationException("Böyle bir kayıt bulunamadı");
        phone.setOccupancySpace(-application.getSize());
        return apps.remove(createAppKey(application));
    }

    @Override
    public void update(String applicationName, Consumer<Application> consumer) {
        consumer.accept(apps.get(applicationName));
    }

    // addPerson() if user is owner switch the users and their files, generate file name using Supplier functions
    public void updateVersion(Application application, String version) {
        update(application.getName(), application_ -> application_.setVersion(version));
    }

    private void defaultApplications() {
        Application connections = new Application("Kişilerim", 150, "V1.5.4", ApplicationCategory.OTHER);
        Application calls = new Application("Aramalarım", 50, "V1.5.4", ApplicationCategory.OTHER);
        Application messages = new Application("Mesajlarım", 200, "V1.2", ApplicationCategory.OTHER);

        try {
            add(connections);
            add(calls);
            add(messages);
        } catch (NoEnoughEmptySpaceException e) {
            System.out.println(e.getMessage());
        } catch (LoadingExistException e) {
            System.out.println(e.getMessage());
        }

    }

    public void getStorageInfo() {
        System.out.println("\t-----------------------------------------------");
        System.out.println("\tTelefon Hafıza Durumu");
        System.out.println("\tToplam Alan: " + phone.getStorageSpace() + "\n\tDolu Alan: " + phone.getOccupancySpace() + "\n\tBoş Alan: " + phone.getEmptySpace());
        System.out.println("\t-----------------------------------------------");
    }

    @Override
    public void sort(Map<String, Application> connections, Comparator<Application> comparator) {
        if (!connections.isEmpty())
            apps.values().stream().sorted(comparator).toList().forEach(application -> System.out.println("\t\t" + application));
    }

    @Override
    public List<Application> filter(Predicate<Application> filteredCondition) {
        return apps.values().stream().filter(filteredCondition).toList();
    }

    @Override
    public Map<Enum, List<Application>> groupByCategory() {
        return apps.values().stream().collect(Collectors.groupingBy(Application::getCategory, Collectors.toList()));
    }

    public List<Application> filterByCategory(String specifiedCategory) {
        return filter(application -> application.getCategory().name().equalsIgnoreCase(specifiedCategory));
    }
}