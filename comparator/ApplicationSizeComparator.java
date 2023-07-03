package comparator;

import application.Application;

import java.util.Comparator;

public class ApplicationSizeComparator implements Comparator<Application> {

    @Override
    public int compare(Application o1, Application o2) {
        return (int)(o1.getSize() - o2.getSize());
    }
}
